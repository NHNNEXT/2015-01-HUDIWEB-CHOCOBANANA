package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController implements Controller {
	protected ModelAndView jstlView(String forwardUrl) {
		return new ModelAndView(new JstlView(forwardUrl));
	}
	
	protected ModelAndView jsonView() {
		return new ModelAndView(new JsonView());
	}

	
}
