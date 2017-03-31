package org.saw.business.service.impl;

import org.hammer.mvc.controller.DefaultWebApiResult;
import org.saw.business.service.PromotionService;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl implements PromotionService{

	@Override
	public DefaultWebApiResult calculate() {
		return DefaultWebApiResult.failure("-1", "PromotionService calculate error." ) ;
	}

}
