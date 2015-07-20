package ubuntudo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ubuntudo.dao.TodoDao;
import ubuntudo.model.TodoEntity;
import ubuntudo.model.TodoUserRelationEntity;

/*
 * ubuntudo.biz 패키지에 있는 클래스도 결국 service 레이어에 속하게됩니다.
 * 관례적으로도 biz라는 이름보다 service라는 이름을 더 많이 사용하는 것 같아요 :)
 */
@Service
public class PersonalService {
	
	// 되도록 접근자는 private으로 하는 것이 좋습니다.
	@Autowired
	private TodoDao todoDao;

	public ArrayList<TodoEntity> getPersonalTodos(Long uid) {
		
		return todoDao.getPersonalTodos(uid);
	}

	public boolean complete(TodoUserRelationEntity info) {
		
		return todoDao.complete(info);
	}

}
