package core.mvc;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ubuntudo.controller.AjaxController;
import ubuntudo.controller.LoginController;
import ubuntudo.controller.SignUpController;
import ubuntudo.controller.StartController;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(RequestMapping.class);
	
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	public void initMapping() {
		
		mappings.put("/signup.do", new SignUpController());
		mappings.put("/login.do", new LoginController());
		mappings.put("/start.do", new StartController());
		mappings.put("/validate.do", new AjaxController());
		//mappings.put("/form.next", new ForwardController("form.jsp")); 참고용으로 남겨둠
		logger.info("Initialized Mapping Completed!");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
