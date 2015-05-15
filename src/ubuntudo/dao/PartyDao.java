package ubuntudo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

import support.Qrys;
import support.QrysG;
import support.QrysP;
import support.QrysU;
import ubuntudo.model.PartyEntity;

@Repository
public class PartyDao {
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
		return jdbcTemplate.update(QrysP.INSERT_PARTY, party.getGid(), party.getLeaderId(), party.getPartyName());
	}

	public long getLastPartyId() {
		RowMapper<Long> rowMapper = new RowMapper<Long>() {
			
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getLong("last_id");
			}
		}; 
		return jdbcTemplate.queryForObject(Qrys.GET_LAST_ID, rowMapper);
	}
	
	public int insertUserToPartyDao(long partyId, long userId) {
		logger.debug("inserting user to party... partyId: " + partyId + ", userId: " + userId);
		return jdbcTemplate.update(QrysU.INSERT_USER_TO_PARTY, userId, partyId);
	}

	public List<PartyEntity> retrievePartyListSearchDao(String partyName) {
		logger.debug("searching Party by Party name... partyName: " + partyName);
		RowMapper<PartyEntity> rowMapper = new RowMapper<PartyEntity>() {
			public PartyEntity mapRow(ResultSet rs, int rowNum) {
				try {
					return new PartyEntity(rs.getLong("pid"), rs.getLong("gid"), rs.getLong("party_leader_id"), rs.getString("p_name"), rs.getString("deleted"));
				} catch (SQLException e) {
					throw new BeanInstantiationException(PartyEntity.class, e.getMessage(), e);
				}
			}
		};
		return jdbcTemplate.query(QrysP.RETRIEVE_PARTY_LIST, rowMapper, Qrys.makeLikeParam(partyName));
	}

	public int updatePartyDao(PartyEntity party) {
		logger.debug("updating current party to: " + party);
		return jdbcTemplate.update(QrysP.UPDATE_PARTY, party.getLeaderId(), party.getPartyName(), party.getStatus(), party.getPid());
	}

	public List<PartyEntity> retrievePartyInGuildListDao(long gid) {
		logger.debug("searching Party in guild... guild id: " + gid);
		RowMapper<PartyEntity> rowMapper = new RowMapper<PartyEntity>() {
			public PartyEntity mapRow(ResultSet rs, int rowNum) {
				try {
					return new PartyEntity(rs.getLong("pid"), rs.getLong("gid"), rs.getLong("party_leader_id"), rs.getString("p_name"), rs.getString("deleted"));
				} catch (SQLException e) {
					throw new BeanInstantiationException(PartyEntity.class, e.getMessage(), e);
				}
			}
		};
		return jdbcTemplate.query(QrysP.RETRIEVE_PARTY_IN_GUILD, rowMapper, gid);
	}
	
	public List<Map<String, Object>> retrieveMyPartyListDao(long uid) {
		logger.debug("retrieving my parties... party id: " + uid);
		return jdbcTemplate.queryForList(QrysP.RETRIEVE_MY_PARTY_LIST, uid);
	}
}