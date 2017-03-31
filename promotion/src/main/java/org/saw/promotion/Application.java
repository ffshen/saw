package org.saw.promotion;
  
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner; 
import org.springframework.boot.autoconfigure.SpringBootApplication;  
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class Application   implements EmbeddedServletContainerCustomizer,CommandLineRunner {
 
    @Value("${server.port}")
    private Integer port;
    
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort( getPort() );   
	}
 
 

}
