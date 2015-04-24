package ubuntudo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

import support.QueryCollection;
import ubuntudo.model.PartyEntity;

@Repository("PartyDao")
public class PartyDao extends QueryCollection {
	private static final Logger logger = LoggerFactory.getLogger(PartyDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}

	public int insertPartyDao(PartyEntity party) {
		logger.debug("inserting: " + party.toString());
		return jdbcTemplate.update(INSERT_PARTY, party.getGid(), party.getleaderId(), party.getPartyName());
	}

	public List<PartyEntity> retrievePartySearchDao(String partyName) {
		logger.debug("searching Party by Party name... partyName: " + partyName);
		RowMapper<PartyEntity> rowMapper = new RowMapper<PartyEntity>() {
			public PartyEntity mapRow(ResultSet rs, int rowNum) {
				try {
					return new PartyEntity(rs.getLong("gid"), rs.getLong("leader_id"), rs.getString("party_name"), rs.getString("status"));
				} catch (SQLException e) {
					throw new BeanInstantiationException(PartyEntity.class, e.getMessage(), e);
				}
			}
		};
		return jdbcTemplate.query(RETRIEVE_PARTY_LIST, rowMapper, makeLikeParam(partyName));
	}

	public int updatePartyDao(PartyEntity party) {
		logger.debug("updating current party to: " + party);
		return jdbcTemplate.update(UPDATE_PARTY, party.getleaderId(), party.getPartyName(), party.getStatus(), party.getPid());
	}
}