package ubuntudo.controller;

import java.security.PrivateKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory
			.getLogger(SignUpController.class);

	UserDao userdao = new UserDao();

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody AjaxRedirectResponse execute(HttpServletRequest request,
			ModelMap modelMap, HttpSession session) throws Exception {
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
				logger.debug("decrypted email: {}, decrypted password: {}",
						_email, _password);

				// 복호화된 정보들을 DB에 넣는다.
				userdao.insertUserDao(name, _email, _password); // 여기서 에러 생기면 이미 있는 
																// id와
																// password라고
																// 알려줘야 하는데...

				// RSA키 없애기
				session.removeAttribute("RSAWebKey");

				// 세션에 user 정보 넣어두기
				UserEntity user = userdao.retrieveUserDao(_email, _password);
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
}
