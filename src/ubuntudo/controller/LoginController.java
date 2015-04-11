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

	String loginSuccessedViewUri = "jsp/personal.jsp";
	String loginFailedViewUri = "jsp/loginFail.jsp";

	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("-->Controller-->Login");
		
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
		ModelAndView mav = jsonView();

		if (currentUser != null) {
			// member login success
			session.setAttribute("user", currentUser);
			logger.info((session.getAttribute("user").toString()));
			mav.addObject("status", "success");
			mav.addObject("uri", loginSuccessedViewUri);
		} else {
			mav.addObject("status", "fail");
			mav.addObject("uri", loginFailedViewUri);
		}
		logger.info("<--Controller-->Login");
		return mav;
	}

}
