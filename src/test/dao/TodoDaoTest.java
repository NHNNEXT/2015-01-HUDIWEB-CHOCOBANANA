package test.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import support.QrysT;
import ubuntudo.dao.TodoDao;
import ubuntudo.model.TodoEntity;

@RunWith(MockitoJUnitRunner.class)
public class TodoDaoTest {
	@InjectMocks
	private TodoDao todoDao;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	private List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>(); 
	private Map <String, Object> map = new HashMap<String, Object>();

	
	@Test
	public void getPersonalTodosTest () {
		map.put("tid", new Long(1));
		map.put("pid", new Long(1));
		map.put("title", "hello");
		map.put("contents", "contents");
		map.put("duedate", "2015-04-26");
		map.put("status", "todo01");
		map.put("editer_id", new Long(1));
		map.put("p_name", "personal");
		rows.add(map);
       
		Mockito.when(jdbcTemplate.queryForList(QrysT.RETRIEVE_TODO_FOR_PERSONAL_VIEW)).thenReturn(rows);
		ArrayList<TodoEntity> todos = todoDao.getPersonalTodos(new Long(1));
	    
	    assertEquals(new Long(1), todos.get(0).getTid());
	    assertEquals(new Long(1), todos.get(0).getPid());
	    assertEquals("hello", todos.get(0).getTitle());
	    assertEquals("contents", todos.get(0).getContents());
	    assertEquals("2015-04-26", todos.get(0).getDueDate());
	    assertEquals("todo01", todos.get(0).getStatus());
	    assertEquals(new Long(1), todos.get(0).getEditerId());
	    assertEquals("personal", todos.get(0).getPartyName());
	}
}
