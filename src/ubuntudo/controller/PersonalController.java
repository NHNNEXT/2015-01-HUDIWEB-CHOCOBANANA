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
import ubuntudo.service.PersonalService;
import core.utils.DateUtil;

@Controller @RequestMapping(value = "/personal")
public class PersonalController {
	private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);
	
	/*
	 * 일반적인 웹 개발시 레이어를 크게 세 부분으로 나누어 관리합니다.
	 * 1. controller, 2. service, 3. data access 입니다.
	 * 
	 * 따라서 controller는 service와, service는 data access 레이어와 메시지를 주고 받으며 자료를 처리하게 됩니다.
	 * 하지만 지금 상황은 controller와 data access(dao)를 직접 연결하여 조금 어색한? 상황이 되었습니다.
	 * 
	 * PersonalController와 TodoDao 사이에 PersonalService를 두어 한 번 개선해 보겠습니다.
	 */
	@Autowired
	private PersonalService personalService;
//	@Autowired
//	TodoDao tdao;

	@RequestMapping(method = RequestMethod.GET)
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

	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public @ResponseBody ArrayList<TodoEntity> getPersonalTodos(
			HttpSession session) {
		logger.debug("/personal/todo 요청에 대해 응답");
		UserEntity user = (UserEntity) session.getAttribute("user");
		
		ArrayList<TodoEntity> todoArray = personalService.getPersonalTodos(user.getUid());
		//ArrayList<TodoEntity> todoArray = tdao.getPersonalTodos(user.getUid());
		
		return todoArray;
	}

	@RequestMapping(value = "/todo/complete/{tid}", method = RequestMethod.GET)
	public @ResponseBody ModelMap completePersonalTodo(HttpSession session, @PathVariable("tid") Long tid) throws ServletRequestBindingException {
		logger.debug("/personal/todo/complete/{tid} GET요청에 대해 응답");
		if (session.getAttribute("user") == null) {
			logger.debug("/personal 요청에 대해 응답 - 세션이 정상적이지 않을때");
			// return "redirect:/start"; 로 보내야함.
		}
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long uid = user.getUid();
		logger.debug("param check: uid={}, tid={}", uid, tid);
		TodoUserRelationEntity info = new TodoUserRelationEntity(tid, uid);
		
		boolean result = personalService.complete(info);
//		boolean result = tdao.complete(info);
		
		ModelMap model = new ModelMap();
		model.put("tid", tid);
		model.put("status", (result)?"success":"fail");
//		if (result) {
//			model.put("status", "success");
//		} else {
//			model.put("status", "fail");
//		}
		return model;
	}
}