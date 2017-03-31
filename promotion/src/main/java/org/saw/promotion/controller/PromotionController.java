package org.saw.promotion.controller;

import org.hammer.mvc.controller.BaseController;
import org.hammer.mvc.controller.DefaultWebApiResult;
import org.saw.promotion.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotion")
public class PromotionController extends BaseController{

    private static Logger log = LoggerFactory.getLogger(PromotionController.class);  
    
    @Autowired
    CalculateService service ;
    
    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    @ResponseBody
    public DefaultWebApiResult calculate() { 
    	log.info("this is PromotionController calculate : success .");
        return DefaultWebApiResult.success(service.calculate()) ;
    }
}
