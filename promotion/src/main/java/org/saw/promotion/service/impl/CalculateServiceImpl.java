package org.saw.promotion.service.impl;

import org.hammer.exception.RetException;
import org.saw.promotion.service.CalculateService;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.*;


@Service
public class CalculateServiceImpl implements CalculateService{

	@Override
	@HystrixCommand(commandKey = "promotion_calculate" , 
			groupKey = "promotion_calculate_service" ,fallbackMethod = "stub"
//			,
//		commandProperties = { 
//				@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
//				@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
//				@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="70")
//    	}, threadPoolProperties = {
//    	        @HystrixProperty(name = "coreSize", value = "30"),
//    	        @HystrixProperty(name = "maxQueueSize", value = "100"),
//    	        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") }
	)
	public Integer calculate() throws RetException {
		return 1000;
	}
	
	public Integer stub(){
		return -1 ;
	}

}
