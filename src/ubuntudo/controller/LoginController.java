package ubuntudo.controller;

import java.security.PrivateKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.dao.UserDao;
import ubuntudo.model.AjaxRedirectResponse;
import ubuntudo.model.UserEntity;
import core.utils.RSAUtils;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserDao userDao;
	
	// 되도록 상수화해주세요
	private static final String LOGIN_SUCCESSED_VIEW_URI = "/personal";
	private static final String LOGIN_FAILED_VIEW_URI = "jsp/loginFail.jsp";

	
	/*
	 * 스프링의 경우
	 * 파라미터의 값은 @RequestParam 어노테이션을 사용하여 받아올 수 있습니다.
	 */
	@RequestMapping(method = RequestMethod.POST)
//	public @ResponseBody AjaxRedirectResponse execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	public @ResponseBody AjaxRedirectResponse execute(HttpSession session, @RequestParam String email, @RequestParam("password") String passwd) throws Exception {
		logger.info("-->Controller-->Login");
		
//		String email = request.getParameter("email");
//		String passwd = request.getParameter("password");
//		
//		HttpSession session = request.getSession();    
		PrivateKey privateKey = (PrivateKey) session.getAttribute("RSAWebKey"); 
		
		String _email = RSAUtils.decryptRsa(privateKey, email);
        String _password = RSAUtils.decryptRsa(privateKey, passwd);
		logger.debug("Logging in email: {}", _email);
		logger.debug("Logging in passwd: {}", _password);

		UserEntity currentUser = userDao.retrieveUserDao(_email, _password);

		AjaxRedirectResponse res = new AjaxRedirectResponse();
		if (currentUser != null) {
			// member login success
			session.setAttribute("user", currentUser);
			logger.info((session.getAttribute("user").toString()));
			res.setStatus("success");
			res.setUri(LOGIN_SUCCESSED_VIEW_URI);
			
		} else {
			res.setStatus("fail");
			res.setUri(LOGIN_FAILED_VIEW_URI);
		}
		logger.info("<--Controller-->Login");
		return res;
	}

}
