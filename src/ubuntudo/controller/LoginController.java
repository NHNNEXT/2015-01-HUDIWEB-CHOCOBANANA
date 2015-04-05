package ubuntudo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ubuntudo.dao.UserDao;
import ubuntudo.model.UserEntity;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class LoginController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	String loginSuccessedViewName = "jsp/start/start.jsp";
	String loginFailedViewName = "jsp/loginFail.jsp";

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("-->Controller-->Login");

		ModelAndView mav = jstlView(loginSuccessedViewName);
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");
		logger.debug("Logging in email: {}", email);
		logger.debug("Logging in passwd: {}", passwd);

		UserDao uDao = new UserDao();
		
		UserEntity currentUser = uDao.retrieveUser(email, passwd);

		// if (currentUser != null) {
		if (currentUser != null) {
			// member login success
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", currentUser);
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
