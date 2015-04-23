package ubuntudo.biz;

import ubuntudo.dao.PartyDao;
import ubuntudo.model.PartyEntity;

public class PartyBiz {
	public int insertPartyBiz(PartyEntity partyEntity) {
		PartyDao pdao = new PartyDao();
		return pdao.insertPartyDao(partyEntity);
	}
}