package ubuntudo.controller;

import java.security.PrivateKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ubuntudo.dao.UserDao;
import ubuntudo.model.UserEntity;
import core.mvc.AbstractController;
import core.mvc.DispatcherServlet;
import core.mvc.ModelAndView;
import core.utils.RSAUtils;

public class SignUpController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	UserDao userdao = new UserDao();
	
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();    
		String name = request.getParameter("name"); 
		String email = request.getParameter("email");
		String password = request.getParameter("password");		
		logger.debug("name: {}, email: {}, password: {}", name, email, password);
		
		PrivateKey privateKey = (PrivateKey) session.getAttribute("RSAWebKey"); 
		if (privateKey == null) 
	    { 
	        //"잘못된 경로로 접근하셨습니다" 페이지로 리다이렉팅 해야함
			//mav.addObject("status", "fail");
	    }
		else
		{
			try
			{
				//암호화처리된 사용자계정정보를 복호화
	            String _email = RSAUtils.decryptRsa(privateKey, email);
	            String _password = RSAUtils.decryptRsa(privateKey, password);
	    		logger.debug("decrypted email: {}, decrypted password: {}", _email, _password);

	    		//복호화된 정보들을 DB에 넣는다.
	    		userdao.insertUser(name, _email, _password); //여기서 에러 생기면 이미 있는 id와 password라고 알려줘야 하는데... 
	    		
	    		//RSA키 없애기
	    		session.removeAttribute("RSAWebKey");
	    		
	    		//세션에 user 정보 넣어두기
	    		UserEntity user = userdao.retrieveUser(_email, _password);
	    		session.setAttribute("user", user);
			}
		    catch(Exception e)
		    {
		        //"잘못된 경로로 접근하셨습니다" 페이지로 리다이렉트 해야 함
		        logger.info("signup ERROR : "+e.getMessage()); 
		    }   
		}
		
		//redirect  to personal page
		ModelAndView mav = jsonView(); 
		mav.addObject("status", "success");
		mav.addObject("uri", "jsp/personal.jsp");
		return mav;
	}
}
