package org.saw.business.controller;
   
import org.hammer.mvc.controller.BaseController;
import org.hammer.mvc.controller.DefaultWebApiResult;
import org.hammer.utils.JsonUtil; 
import org.saw.business.service.PromotionService;
import org.saw.business.vo.SampleVo; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController; 
 
 

/**
 * @author shenx
 * @date 2017年2月6日
 * @see
 * @version
 */

@RestController
@RequestMapping("/business")
public class SampleController extends BaseController{

    private static Logger log = LoggerFactory.getLogger(SampleController.class);  
    
    @Value("${business.memo}")
    private String memo ;
    
    @Autowired
    private PromotionService promotionService ;
    
    @RequestMapping(value = "/sales", method = RequestMethod.POST)
    @ResponseBody
    public DefaultWebApiResult sales(@Validated @RequestBody SampleVo reqVo) {
    	reqVo.setMemo(memo);
    	log.info("SampleController sales : {}",JsonUtil.toJson(reqVo));

    	log.info("SampleController calculate resp : {}",JsonUtil.toJson(promotionService.calculate()));
        return of(()->reqVo) ;
    }
}
