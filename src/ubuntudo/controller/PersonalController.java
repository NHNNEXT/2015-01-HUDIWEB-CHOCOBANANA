package ubuntudo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.dao.TodoDao;
import ubuntudo.model.TodoEntity;
import ubuntudo.model.TodoUserRelationEntity;
import ubuntudo.model.UserEntity;
import core.utils.DateUtil;

@Controller
public class PersonalController {
	private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);
	
	@Autowired
	TodoDao tdao;

	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public String execute(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			logger.debug("/personal 요청에 대해 응답 - 세션이 정상적이지 않을때");
			return "redirect:/start";
		}
		logger.debug("/personal 요청에 대해 응답");
		
		model.addAttribute("yesterday", DateUtil.getDateString(-1));
		model.addAttribute("today", DateUtil.getDateString(0));
		model.addAttribute("tomorrow", DateUtil.getDateString(1));

		return "personal";
	}

	@RequestMapping(value = "/personal/todo", method = RequestMethod.GET)
	public @ResponseBody ArrayList<TodoEntity> getPersonalTodos(
			HttpSession session) {
		logger.debug("/personal/todo 요청에 대해 응답");
		UserEntity user = (UserEntity) session.getAttribute("user");
		ArrayList<TodoEntity> todoArray = tdao.getPersonalTodos(user.getUid());
		return todoArray;
	}

	@RequestMapping(value = "/personal/todo/complete/{tid}", method = RequestMethod.GET)
	public @ResponseBody ModelMap completePersonalTodo(HttpSession session,
			@PathVariable("tid") Long tid)
			throws ServletRequestBindingException {
		logger.debug("/personal/todo/complete/{tid} GET요청에 대해 응답");
		if (session.getAttribute("user") == null) {
			logger.debug("/personal 요청에 대해 응답 - 세션이 정상적이지 않을때");
			// return "redirect:/start"; 로 보내야함.
		}
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long uid = user.getUid();
		logger.debug("param check: uid={}, tid={}", uid, tid);
		TodoUserRelationEntity info = new TodoUserRelationEntity(tid, uid);
		boolean result = tdao.complete(info);
		ModelMap model = new ModelMap();
		model.put("tid", tid);
		if (result) {
			model.put("status", "success");
		} else {
			model.put("status", "fail");
		}
		return model;
	}
}