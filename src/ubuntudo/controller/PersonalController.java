package ubuntudo.controller;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PersonalController {
	private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);
	TodoDao tdao = new TodoDao();
	
	@RequestMapping(value="/personal", method = RequestMethod.GET)
	public String execute (HttpSession session) {
		if(session.getAttribute("user") == null){
			logger.debug("/personal 요청에 대해 응답 - 세션이 정상적이지 않을때");
			return "redirect:/start";
		}
		logger.debug("/personal 요청에 대해 응답");
		return "personal";	
	}
	
	@RequestMapping(value = "/personal/todo", method = RequestMethod.GET)
	public @ResponseBody ArrayList<TodoEntity> getPersonalTodos(HttpSession session) {
		logger.debug("/personal/todo 요청에 대해 응답");
		UserEntity user = (UserEntity) session.getAttribute("user");
		ArrayList<TodoEntity> todoArray = tdao.getPersonalTodos(user.getUid());
		return todoArray;
	}
	
	@RequestMapping(value="/personal", method = RequestMethod.POST)
	public @ResponseBody TodoEntity addPersonalTodo(HttpSession session, HttpServletRequest req) throws ServletRequestBindingException {
		logger.debug("/personal POST요청에 대해 응답");
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long uid = user.getUid();
		Long pid = ServletRequestUtils.getRequiredLongParameter(req, "pid");
		String title = ServletRequestUtils.getRequiredStringParameter(req, "title");
		String contents = ServletRequestUtils.getRequiredStringParameter(req, "contents");
		String duedate = ServletRequestUtils.getRequiredStringParameter(req, "dueDate");
		Date due = Date.valueOf(duedate);
		logger.debug("param check: uid={}, pid={}, title={}, contents={}, due={}", uid, pid, title, contents, due);

		TodoEntity newTodo = new TodoEntity(pid, title, contents, due, uid);
		TodoEntity resultTodo = tdao.addPersonalTodo(newTodo);
		
		return resultTodo;
	}
}
