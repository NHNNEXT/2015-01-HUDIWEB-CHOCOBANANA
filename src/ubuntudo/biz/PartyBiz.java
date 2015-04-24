package ubuntudo.biz;

import org.springframework.beans.factory.annotation.Autowired;

import ubuntudo.dao.PartyDao;
import ubuntudo.model.PartyEntity;

public class PartyBiz {
	
	@Autowired
	private PartyDao pdao;
	
	public int insertPartyBiz(PartyEntity party) {
		return pdao.insertPartyDao(party);
	}
}