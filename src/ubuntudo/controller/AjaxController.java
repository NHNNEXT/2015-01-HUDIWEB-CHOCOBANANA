package ubuntudo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.dao.UserDao;
import ubuntudo.model.AjaxRedirectResponse;

@Controller
@RequestMapping(value = "/validate")
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	String loginSuccessedViewName = "jsp/start.jsp";
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody AjaxRedirectResponse execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("-->Controller-->Ajax");

		String email = request.getParameter("email");
		logger.debug("Logging in email: {}", email);

		UserDao uDao = new UserDao();
		
		Boolean isExistingUser = uDao.validateUser(email);
		AjaxRedirectResponse res = new AjaxRedirectResponse(isExistingUser.toString(), null);
		logger.info("<--Controller-->Ajax");
		return res;
	}

}
