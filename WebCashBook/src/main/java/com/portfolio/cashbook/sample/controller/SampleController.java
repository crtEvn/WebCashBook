package com.portfolio.cashbook.sample.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {
	
	protected Log log = LogFactory.getLog(SampleController.class);
	
	// LoggerInterceptor가 동작하는지 확인하기 위함
	@RequestMapping(value="/testInterceptor.do")
    public ModelAndView testInterceptor() throws Exception{
        ModelAndView mv = new ModelAndView("");
        log.debug("인터셉터 테스트");
         
        return mv;
    }

}
