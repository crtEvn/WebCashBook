package com.portfolio.cashbook.sample.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.cashbook.sample.service.SampleService;

@Controller
public class SampleController {
	
	protected Log log = LogFactory.getLog(SampleController.class);
	
	@Autowired
	BasicDataSource dataSource;
	
	@Resource(name="sampleService")
    private SampleService sampleService;
	
	// LoggerInterceptor가 동작하는지 확인하기 위함
	@RequestMapping(value="/testInterceptor.do")
    public ModelAndView testInterceptor() throws Exception{
        ModelAndView mv = new ModelAndView("");
        log.debug("인터셉터 테스트");
         
        return mv;
    }
	
	@RequestMapping(value="/testDB.do")
	public String testDB(Model model) {
		
		log.debug("MySQL 연결 테스트");
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT NOW() AS NOW;");

			while(rs.next()) {
				model.addAttribute("servertime",rs.getString("now"));
				log.debug("servertime: "+rs.getString("now"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close(); 
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try { 
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "sample/test";
	}
	
	@RequestMapping(value="/testMyBatis.do")
	public String testMyBatis(Model model) throws Exception {
		
		model.addAttribute("servertime", sampleService.getTime());
		log.debug("servertime: "+sampleService.getTime());
		return "sample/test";
	}

}
