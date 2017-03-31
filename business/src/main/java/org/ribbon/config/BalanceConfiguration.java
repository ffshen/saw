package org.ribbon.config;
  

import org.springframework.cloud.netflix.hystrix.stream.HystrixStreamProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

//import feign.RequestInterceptor;
//import feign.RequestTemplate;

@Configuration
public class BalanceConfiguration {
	 
	@Bean
	public IClientConfig ClientConfig() {
		DefaultClientConfigImpl config = new DefaultClientConfigImpl();
		config.set(CommonClientConfigKey.ConnectTimeout, 1000);
		config.set(CommonClientConfigKey.ReadTimeout, 500);
		
		
		
		return config;
	}
	
	@Bean
	public IRule balanceRule(IClientConfig config){
		return new RandomRule() ;
	}

}
