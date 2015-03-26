package ubuntudo.controller;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class StartController extends AbstractController {
	//private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인이 안되었을 경우 
			//uri가 root이면, 첫페이지로 리다이렉트 - 이경우만 구현하겠음
			//uri가 /login.do이면, 로그인 페이지로 리다이렉트 
		//로그인 되었을 경우
			//개인별페이지로 리다이렉트
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair keyPair = generator.genKeyPair();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
 
        HttpSession session = request.getSession();
        session.setAttribute("RSAWebKey", privateKey);//세션에 RSA 개인키를 세션에 저장한다. 
        
        RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        String publicKeyModulus = publicSpec.getModulus().toString(16);
        String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
         
        ModelAndView mav = jstlView("jsp/start/start.jsp");
		mav.addObject("RSAModulus", publicKeyModulus);
		mav.addObject("RSAExponent", publicKeyExponent);
		return mav;
	}
}
