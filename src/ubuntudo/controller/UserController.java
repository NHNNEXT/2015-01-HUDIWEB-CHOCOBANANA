package ubuntudo.controller;

import java.security.PrivateKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.dao.UserDao;
import ubuntudo.model.AjaxRedirectResponse;
import ubuntudo.model.UserEntity;
import core.utils.RSAUtils;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao udao;

	String loginSuccessedViewUri = "/personal";
	String loginFailedViewUri = "jsp/loginFail.jsp";

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody AjaxRedirectResponse signupController(HttpServletRequest request, ModelMap modelMap, HttpSession session) throws Exception {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		logger.debug("name: {}, email: {}, password: {}", name, email, password);

		PrivateKey privateKey = (PrivateKey) session.getAttribute("RSAWebKey");

		if (privateKey == null) {
			// "잘못된 경로로 접근하셨습니다" 페이지로 리다이렉팅 해야함
			return new AjaxRedirectResponse("false", null);
		} else {
			try {
				// 암호화처리된 사용자계정정보를 복호화
				String _email = RSAUtils.decryptRsa(privateKey, email);
				String _password = RSAUtils.decryptRsa(privateKey, password);
				logger.debug("decrypted email: {}, decrypted password: {}", _email, _password);

				// 복호화된 정보들을 DB에 넣는다.
				udao.insertUserDao(name, _email, _password); // 여기서 에러 생기면 이미 있는 id와 password라고 알려줘야 하는데...

				// RSA키 없애기
				session.removeAttribute("RSAWebKey");

				// 세션에 user 정보 넣어두기
				UserEntity user = udao.retrieveUserDao(_email, _password);
				session.setAttribute("user", user);
			} catch (Exception e) {
				// "잘못된 경로로 접근하셨습니다" 페이지로 리다이렉트 해야 함
				logger.info("DB error");
				logger.info("signup ERROR : " + e.getMessage());
			}
		}

		// redirect to personal page
		return new AjaxRedirectResponse("success", "/personal");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody AjaxRedirectResponse loginController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("-->Controller-->Login");

		String email = request.getParameter("email");
		String passwd = request.getParameter("password");

		HttpSession session = request.getSession();    
		PrivateKey privateKey = (PrivateKey) session.getAttribute("RSAWebKey"); 
	
		String _email = RSAUtils.decryptRsa(privateKey, email);
		String _password = RSAUtils.decryptRsa(privateKey, passwd);
		logger.debug("Logging in email: {}", _email);
		logger.debug("Logging in passwd: {}", _password);

		UserEntity currentUser = udao.retrieveUserDao(_email, _password);

		AjaxRedirectResponse res = new AjaxRedirectResponse();
		if (currentUser != null) {
			// member login success
			session.setAttribute("user", currentUser);
			logger.info((session.getAttribute("user").toString()));
			res.setStatus("success");
			res.setUri(loginSuccessedViewUri);
			
		} else {
			res.setStatus("fail");
			res.setUri(loginFailedViewUri);
		}
		logger.info("<--Controller-->Login");
		return res;
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody ModelMap validateEmailController(String email) throws Exception {
		logger.info("-->Controller-->Validate");
		logger.debug("Logging in email: {}", email);

		String result = udao.validateUser(email);
		logger.info("result: {}", result);
		ModelMap model = new ModelMap();
		if (result != "") {
			model.put("status", "success");
		} else {
			model.put("status", "fail");
		}
		logger.info("<--Controller-->Validate");
		return model;
	}
}