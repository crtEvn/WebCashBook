package com.portfolio.cashbook.sample.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.cashbook.ledger.dto.LedgerDTO;
import com.portfolio.cashbook.ledger.service.LedgerService;
import com.portfolio.cashbook.sample.service.SampleService;
import com.portfolio.cashbook.user.dao.UserDAO;
import com.portfolio.cashbook.user.dto.SignDTO;
import com.portfolio.cashbook.user.service.CheckUserService;
import com.portfolio.cashbook.user.service.SignInService;
import com.portfolio.cashbook.user.service.SignUpService;
import com.portfolio.cashbook.user.vo.UserVO;

@Controller
public class SampleController {
	
	protected Log log = LogFactory.getLog(SampleController.class);
	
	@Autowired
	BasicDataSource dataSource;
	
	@Resource(name="sampleService")
    private SampleService sampleService;
	
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="checkUserService")
    private CheckUserService checkUserService;
	
	@Resource(name="ledgerService")
    private LedgerService ledgerService;
	
	// LoggerInterceptor가 동작하는지 확인하기 위함
	@RequestMapping(value="/testInterceptor.do")
    public ModelAndView testInterceptor() throws Exception{
        ModelAndView mv = new ModelAndView("");
        log.debug("인터셉터 테스트");
         
        return mv;
    }
	
	// MySQL DB 연결 확인
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
	
	// MyBatis 연결 확인
	@RequestMapping(value="/testMyBatis.do")
	public String testMyBatis(Model model) throws Exception {
		
		model.addAttribute("servertime", sampleService.getTime());
		log.debug("servertime: "+sampleService.getTime());
		return "sample/test";
	}
	
	// 회원가입 테스트
	@RequestMapping(value="/insertUser.do")
	public String insertUser(SignDTO signDTO) throws Exception {
		
		signDTO.setUser_id("user01");
		signDTO.setUser_pw("123!");
		signDTO.setUser_email("jjsshhaw@naver.com");
		
		// user_pw 암호화 
		String hashedPW = BCrypt.hashpw(signDTO.getUser_pw(),BCrypt.gensalt());
		signDTO.setUser_pw(hashedPW);
		
		userDAO.insertUser(signDTO);
		log.debug("admin 회원가입");
		
		return "sample/test";
	}
	
	// 자동 로그인
	@RequestMapping(value="/autoSignIn.do")
	public String autoSignIn(SignDTO signDTO, HttpSession session) throws Exception {
		
		signDTO.setUser_id("admin");
		
		UserVO userVO = checkUserService.selectUser(signDTO);
		
		session.setAttribute("userSession", userVO);
		
		return "redirect:/ledger/main.do";
	}
	
	// 자동 로그인
	@RequestMapping(value="/testCalendar.do")
	public String testCal(Model model, LedgerDTO dto, HttpSession session) throws Exception {
		
		dto.setUser_idx("1");
		
		List<Map<String,Object>> calDateGroup = ledgerService.getCalendarDateGroup(dto);
		
		log.debug("calDateGroup.size(): "+calDateGroup.size());
		for(int a = 0; a < calDateGroup.size(); a++ ) {
			log.debug(a+": "+calDateGroup.get(a));
		}
		
		// Attribute 저장
		model.addAttribute("page","calendar");
		model.addAttribute("calDateGroup", calDateGroup);
		
		return "ledger/ledger_main";
	}

}
