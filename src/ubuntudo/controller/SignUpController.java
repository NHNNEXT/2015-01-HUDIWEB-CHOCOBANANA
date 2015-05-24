package ubuntudo.controller;

import java.security.PrivateKey;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value = "/signup")
public class SignUpController {
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	UserDao userdao;


}
