package ubuntudo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@Controller
@RequestMapping(value = "/validate")
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	/*
	 * 여기는 빼도 될것 같아요.
	String loginSuccessedViewName = "jsp/start.jsp";
	 */
	
	@Autowired
	private UserDao uDao;
	
	@RequestMapping(method = RequestMethod.POST)
	/*
	 * 
	 * 
	public @ResponseBody AjaxRedirectResponse execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 */
	public @ResponseBody AjaxRedirectResponse execute(@RequestParam("email") String email) {
		logger.info("-->Controller-->Ajax");

		// 이 방식을 사용하기보다는 @RequestParam을 사용해서 주입받는게 훨씬 좋은 방법입니다. :)
		//String email = request.getParameter("email");
		logger.debug("Logging in email: {}", email);

		/*
		 * Dao의 경우 상태를 기록하는 객체가 아닙니다.
		 * 따라서 Bean으로 등록 후 Autowired(DI) 받아 사용하시면 됩니다.
		 * 
		 * 지금 상황이라면 아래와 같은 방법으로 수정하셔야 Bean으로 사용할 수 있습니다.
		 * 
		 * 1. ubuntudo-servlet.xml에서 component-scan 레벨을 ubuntudo.controller --> ubuntudo 로 변경
		 * 2. UserDao를 @Repository 어노테이션을 사용하여 Bean 등록
		 * 3. 사용할 객체 (AjaxController.java)의 필드에 @Autowired private UserDao uDao 를 등록
		 * 
 		UserDao uDao = new UserDao();
		 */
		
		Boolean isExistingUser = uDao.validateUser(email);
		AjaxRedirectResponse res = new AjaxRedirectResponse(isExistingUser.toString(), null);
		logger.info("<--Controller-->Ajax");
		return res;
	}

}
