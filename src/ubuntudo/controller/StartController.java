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

	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 되었을 경우 개인별페이지로 리다이렉트
		HttpSession session = request.getSession();    
		if(session.getAttribute("user") != null) {
			//이미 로그인한 상태일 때, redirect  to personal page
	        ModelAndView mav = jstlView("redirect:jsp/personal.jsp");
			return mav;
		}
		
		//로그인이 안되었을 경우 startPage로 리다이렉트
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair keyPair = generator.genKeyPair();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
 
        session.setAttribute("RSAWebKey", privateKey);//세션에 RSA 개인키를 세션에 저장한다. 
        
        RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        String publicKeyModulus = publicSpec.getModulus().toString(16);
        String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
         
        ModelAndView mav = jstlView("jsp/start.jsp");
		mav.addObject("RSAModulus", publicKeyModulus);
		mav.addObject("RSAExponent", publicKeyExponent);
		return mav;
	}
}
