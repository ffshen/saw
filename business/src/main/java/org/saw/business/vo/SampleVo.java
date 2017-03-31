package org.saw.business.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author shenx
 * @date 2017年2月6日
 * @see
 * @version
 */

@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class SampleVo implements Serializable{
    
    private static final long serialVersionUID = 4678058598675894031L;
    
    @NotNull
    private Integer orderId ;
    
    private String memo ;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
    
    

}
