package test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ubuntudo.controller.TodoController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ubuntudo-servlet.xml")
public class TodoControllerTest {
	
	@Autowired
	private TodoController todoController;

	@Test
	public void updateTodoControllerTest() {
//		todoController.updateTodoController(100l, 8l, -1l, "new note2");
	}
}