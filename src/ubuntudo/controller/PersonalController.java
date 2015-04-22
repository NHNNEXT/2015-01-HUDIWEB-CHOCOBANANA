package ubuntudo.controller;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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
	public @ResponseBody ArrayList<TodoEntity> getPersonalTodos(HttpSession session) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		ArrayList<TodoEntity> todoArray = tdao.getPersonalTodos(user.getUid());
		return todoArray;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ArrayList<TodoEntity> addPersonalTodo(HttpSession session, HttpServletRequest req) throws ServletRequestBindingException {
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long uid = user.getUid();
		Long pid = ServletRequestUtils.getRequiredLongParameter(req, "pid");
		String title = ServletRequestUtils.getRequiredStringParameter(req, "title");
		String contents = ServletRequestUtils.getRequiredStringParameter(req, "contents");
		String duedate = ServletRequestUtils.getRequiredStringParameter(req, "dueDate");
		Date due = Date.valueOf(duedate);
		ArrayList<TodoEntity> todoArray = null;
		
		TodoEntity newTodo = new TodoEntity(pid, title, contents, due, uid);
//		boolean result = tdao.addPersonalTodo(newTodo);
//		if(result) {
//			
//			todoArray = tdao.getPersonalTodos(uid);
//		}
		return todoArray;
	}
}
