package ubuntudo.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ubuntudo.dao.PartyDao;
import ubuntudo.model.PartyEntity;

public class PartyBiz {

	@Autowired
	private PartyDao pdao;

	public int insertPartyBiz(PartyEntity party) {
		return pdao.insertPartyDao(party);
	}

	public List<PartyEntity> retrievePartyListSearchBiz(String partyName) {
		return pdao.retrievePartyListSearchDao(partyName);
	}

	public int updatePartyBiz(PartyEntity partyEntity) {
		return pdao.updatePartyDao(partyEntity);
	}
}