package ubuntudo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ubuntudo.dao.UserDao;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class AjaxController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	String loginSuccessedViewName = "jsp/start/start.jsp";
	
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("-->Controller-->Ajax");

		ModelAndView mav = jsonView();
		String email = request.getParameter("email");
		logger.debug("Logging in email: {}", email);

		UserDao uDao = new UserDao();
		
		Boolean isExistedUser = uDao.validateUser(email);
		
		if (isExistedUser != null) {
			mav.addObject("isExistedUser", isExistedUser);
		} else {
			mav.addObject("isExistedUser", isExistedUser);
		}
		logger.info("<--Controller-->Ajax");
		return mav;
	}

}
