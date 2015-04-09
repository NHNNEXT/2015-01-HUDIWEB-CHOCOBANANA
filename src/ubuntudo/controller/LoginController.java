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
import core.mvc.ModelAndView;
import core.utils.RSAUtils;

public class LoginController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	String loginSuccessedViewName = "jsp/personal.jsp";
	String loginFailedViewName = "jsp/loginFail.jsp";

	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("-->Controller-->Login");

		ModelAndView mav = jstlView(loginSuccessedViewName);
		
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");
		
		HttpSession session = request.getSession();    
		PrivateKey privateKey = (PrivateKey) session.getAttribute("RSAWebKey"); 
		
		String _email = RSAUtils.decryptRsa(privateKey, email);
        String _password = RSAUtils.decryptRsa(privateKey, passwd);
		logger.debug("Logging in email: {}", _email);
		logger.debug("Logging in passwd: {}", _password);

		UserDao uDao = new UserDao();
		
		UserEntity currentUser = uDao.retrieveUser(_email, _password);

		// if (currentUser != null) {
		if (currentUser != null) {
			// member login success
			session.setAttribute("user", currentUser);
			logger.info((session.getAttribute("currentUser").toString()));

			mav = jstlView(loginSuccessedViewName);
			mav.addObject("currentUser", currentUser);
		} else {
			mav = jstlView(loginFailedViewName);
		}
		logger.info("<--Controller-->Login");
		return mav;
	}

}
