package ubuntudo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.biz.TodoBiz;
import ubuntudo.model.TodoEntity;
import ubuntudo.model.UserEntity;

@Controller
@RequestMapping(value = "/todo")
public class TodoController {
	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	TodoBiz tbiz;

	@RequestMapping(value = "/updateTodo", method = RequestMethod.POST)
	public @ResponseBody void updateTodoController(HttpSession session, long tid, String title_edit, String note_edit, String due_date_edit) {
		logger.debug("===>updateTodo");

		UserEntity user = (UserEntity) session.getAttribute("user");
		long uid = user.getUid();

		logger.debug("current user: " + uid);
		logger.debug("current todo: " + tid);
		logger.debug("new title_edit: " + title_edit);
		logger.debug("new note_edit: " + note_edit);
		logger.debug("new due_date_edit: " + due_date_edit);

		tbiz.updateTodoBiz(new TodoEntity(tid, title_edit, note_edit, due_date_edit, uid));
		logger.debug("<===updateTodo");
	}
}