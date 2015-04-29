package ubuntudo.controller;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.dao.TodoDao;
import ubuntudo.model.AjaxRedirectResponse;
import ubuntudo.model.TodoEntity;
import ubuntudo.model.TodoUserRelationEntity;
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
	
	@RequestMapping(value="/personal/todo", method = RequestMethod.POST)
	public @ResponseBody TodoEntity addPersonalTodo(HttpSession session, HttpServletRequest req) throws ServletRequestBindingException {
		logger.debug("/personal/todo POST요청에 대해 응답");
		if(session.getAttribute("user") == null){
			logger.debug("/personal 요청에 대해 응답 - 세션이 정상적이지 않을때");
			//return "redirect:/start"; string으로 보내야하는데... 어쩐다... ->  나중에 주기적으로 ajax요청 보낼 때 체크해서 redirect해주면 될 듯?!
		}
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long uid = user.getUid();
		Long pid = ServletRequestUtils.getRequiredLongParameter(req, "pid");
		String title = ServletRequestUtils.getRequiredStringParameter(req, "title");
		String contents = ServletRequestUtils.getRequiredStringParameter(req, "contents");
		String duedate = ServletRequestUtils.getRequiredStringParameter(req, "dueDate");
		Date due = Date.valueOf(duedate); // duedate가 null값이면 error난다. "-"가 없어도 에러난다.
		logger.debug("param check: uid={}, pid={}, title={}, contents={}, due={}", uid, pid, title, contents, due);

		TodoEntity newTodo = new TodoEntity(pid, title, contents, due, uid);
		TodoEntity resultTodo = tdao.addPersonalTodo(newTodo);
		
		return resultTodo;
	}
	
	@RequestMapping(value="/personal/todo/complete/{tid}", method = RequestMethod.GET)
	public @ResponseBody ModelMap completePersonalTodo(HttpSession session, @PathVariable("tid") Long tid) throws ServletRequestBindingException {
		logger.debug("/personal/todo/complete/{tid} GET요청에 대해 응답");
		if(session.getAttribute("user") == null){
			logger.debug("/personal 요청에 대해 응답 - 세션이 정상적이지 않을때");
			//return "redirect:/start"; 로 보내야함.
		}
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long uid = user.getUid();
		logger.debug("param check: uid={}, tid={}", uid, tid);
		TodoUserRelationEntity info = new TodoUserRelationEntity(tid, uid);
		//boolean result = tdao.complete(info);
		boolean result = true; //test
		ModelMap model = new ModelMap();
	    model.put("tid", tid);
	    if(result) {
		    model.put("status", "success");
		}
		else {
		    model.put("status", "fail");
		}
		return model;
	}
}
