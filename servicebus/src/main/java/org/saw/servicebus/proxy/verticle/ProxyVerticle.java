package org.saw.servicebus.proxy.verticle;
   
import java.net.URI;

import org.ribbon.config.BalanceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

@Component
@RefreshScope
@RibbonClient(name="app",configuration = BalanceConfiguration.class)
public class ProxyVerticle extends AbstractVerticle {

	  Logger logger = LoggerFactory.getLogger(this.getClass());  
	  
	  @Value("${servicebus.host}")
	  private String host ;
	  
	  @Value("${servicebus.port}")
	  private Integer port ;
	  
	  @Autowired
	  private LoadBalancerClient client;
	  
	  private ServiceInstance getServiceInstance(String domain){
		  return this.client.choose(domain) ;	
	  }
	  
	  @Override
	  public void start() throws Exception {		
		  
	    Router router = Router.router(vertx);

	    router.route(HttpMethod.POST,"/rest/:proxyDomain/:proxyPath/").handler(context->{	
	    	logger.info("-----------------");
	    	HttpServerRequest request = context.request() ;	
	    	HttpServerResponse response = context.response() ;
	    	MultiMap headers = request.headers() ; 
	    	
	    	String proxyDomain = request.getParam("proxyDomain");
	    	String proxyPath = request.getParam("proxyPath");	
	    	
	        if (logger.isInfoEnabled()) {
	            logger.info(" request -> url: {},  method: {} "
	            		, request.uri()  
	            		, request.method() );
	        	headers.forEach(header->{    		
	        		logger.info(" request -> header key:{},value:{}" ,header.getKey(), header.getValue());    		
	        	});      	            
	        }	           	
	        
	        Integer port = getServiceInstance(proxyDomain).getPort() ;
	        String host = getServiceInstance(proxyDomain).getHost() ;
	        URI uri = getServiceInstance(proxyDomain).getUri() ;	        

			logger.info("business host:{},port:{},uri:{}",host,port,uri);

        	HttpClientOptions options = new HttpClientOptions().setKeepAlive(true);
        	HttpClient client = vertx.createHttpClient(options);
	    	HttpClientRequest proxyRequest 
	        	= client.request(request.method(), port ,host,"/"+proxyDomain+"/"+proxyPath+"/" , proxyResponse -> {
	        	  response
				  	.setChunked(true)
	    		  	.headers()
	    		  	.setAll(proxyResponse.headers()) ;
	        	  
	    		  proxyResponse.handler(proxyRespBuffer->{
	    			  try{
	    				  response
		        		  	.setStatusCode(proxyResponse.statusCode())
	    				  	.write(proxyRespBuffer) ;
	    			  }
	    			  catch(Exception e){
	    				  logger.error("proxy write msg error .",e);
	    				  throw e ;
	    			  }
	    		  });
	    		  proxyResponse.endHandler((v) -> response.end());
	    	}) ;	 
	        proxyRequest
	        	.exceptionHandler(ex->{
				  	logger.error("proxyRequest error .",ex);	            	
	        	})
	        	.setChunked(true)
    			.headers().setAll(headers)
			  	; 
		    	
		    //取body
		    request.handler(bodybuffer->{ 
			        if (logger.isInfoEnabled()) {
			            logger.info(" request -> body :{}" ,	bodybuffer);   	
			        }
		        	proxyRequest.write(bodybuffer);
		    }) ;
		    request.endHandler((v) -> proxyRequest.end());
	    	 
	    }); 
	    
	    vertx.createHttpServer()
	    	.requestHandler(router::accept)
	    	.listen(getPort(),getHost(),res->{
	    		if (res.succeeded()) {
	    			logger.info("Server is now listening!port:{}",getPort());
	    		} else {
	    			logger.info("Failed to bind!");
	    		}
	    	});
	  }
	   


	  public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}
	  
	  
}
