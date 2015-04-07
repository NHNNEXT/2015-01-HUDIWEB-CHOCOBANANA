package ubuntudo.controller;

import java.security.PrivateKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.DispatcherServlet;
import core.mvc.ModelAndView;
import core.utils.RSAUtils;

public class SignUpController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name"); 
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		logger.debug("name: {}", name);
		logger.debug("email: {}", email);
		logger.debug("password: {}", password);
		
		HttpSession session = request.getSession();    
		PrivateKey privateKey = (PrivateKey) session.getAttribute("RSAWebKey"); 
		
		if (privateKey == null) 
	    { 
	        //아이디 패스워드를 확인하라는 페이지로 리다이렉트
			//listObj.put("state", "false");
	    }
		else
		{
			try
			{
				//암호화처리된 사용자계정정보를 복호화 처리한다.
	            String _email = RSAUtils.decryptRsa(privateKey, email);
	            String _password = RSAUtils.decryptRsa(privateKey, password);
	    		logger.debug("decrypted email: {}", _email);
	    		logger.debug("decrypted password: {}", _password);
	    		//복호화된 정보들을 DB에 넣는다.
	           
	            session.removeAttribute("RSAWebKey");
			}
		    catch(Exception e)
		    {
		        //아이디 패스워드를 확인하라는 페이지로 리다이렉트
		        logger.info("signup ERROR : "+e.getMessage()); 
		    }   
		}
		//회원가입이 완료되었으므로 세션에 정보 넣어둬야함.
		ModelAndView mav = jsonView(); 
		mav.addObject("status", "success");
		mav.addObject("uri", "jsp/personal.jsp");
		return mav;
	}
}
