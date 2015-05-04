package ubuntudo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.dao.UserDao;
import ubuntudo.model.UserEntity;

@Controller
@RequestMapping(value = "/validate")
public class ValidateController {
	private static final Logger logger = LoggerFactory
			.getLogger(ValidateController.class);
	
	@Autowired
	private UserDao uDao = new UserDao();

	@RequestMapping(value = "/user", method = RequestMethod.POST)
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
