package org.saw.business.service;

import org.hammer.mvc.controller.DefaultWebApiResult;
import org.ribbon.config.BalanceConfiguration;
import org.saw.business.service.impl.PromotionServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "promotion" ,configuration = BalanceConfiguration.class ,fallback = PromotionServiceImpl.class)
public interface PromotionService {
	
	@RequestMapping(method = RequestMethod.POST, value = "/promotion/calculate")
	DefaultWebApiResult calculate();

}
