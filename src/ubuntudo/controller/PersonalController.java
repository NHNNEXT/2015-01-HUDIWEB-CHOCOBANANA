package ubuntudo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.dao.TodoDao;
import ubuntudo.model.TodoEntity;
import ubuntudo.model.UserEntity;

@Controller
@RequestMapping(value = "/personal")
public class PersonalController {
//	private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);
	TodoDao tdao = new TodoDao();
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ArrayList<TodoEntity> execute(HttpSession session) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		ArrayList<TodoEntity> todoArray = tdao.getPersonalTodos(user.getUid());
		return todoArray;
	}
}
