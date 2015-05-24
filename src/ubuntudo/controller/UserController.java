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
	private UserDao userDao;

	String loginSuccessedViewUri = "/personal";
	String loginFailedViewUri = "jsp/loginFail.jsp";

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody AjaxRedirectResponse execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("-->Controller-->Login");

		String email = request.getParameter("email");
		String passwd = request.getParameter("password");

		HttpSession session = request.getSession();    
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
			res.setUri(loginSuccessedViewUri);
			
		} else {
			res.setStatus("fail");
			res.setUri(loginFailedViewUri);
		}
		logger.info("<--Controller-->Login");
		return res;
	}

	@Autowired
	private UserDao uDao = new UserDao();

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody ModelMap execute(String email) throws Exception {
		logger.info("-->Controller-->Validate");
		logger.debug("Logging in email: {}", email);

		UserEntity result = uDao.validateUser(email);
		logger.info("result: {}", result);
		ModelMap model = new ModelMap();
		if (result != null) {
			model.put("status", "success");
		} else {
			model.put("status", "fail");
		}
		logger.info("<--Controller-->Validate");
		return model;
	}
}