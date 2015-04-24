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
import ubuntudo.model.Any;
import ubuntudo.model.GuildEntity;
import ubuntudo.model.UserEntity;

@Repository("guildDao")
public class GuildDao extends QueryCollection {
	private static final Logger logger = LoggerFactory.getLogger(GuildDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}

	public int insertNewGuildDao(GuildEntity guild) {
		logger.debug("inserting new guild..." + guild.toString());
		return jdbcTemplate.update(INSERT_GUILD, guild.getGuildName(), guild.getLeaderId());
	}

	public List<Any> retrieveGuildAndPartyDao(long demanderIdSearch, String guildNameSearch) {
		RowMapper<Any> rowMapper = new RowMapper<Any>() {
			public Any mapRow(ResultSet rs, int rowNum) {
				try {
					Any any = new Any();
					any.setOne(rs.getString(1));
					any.setTwo(rs.getString(2));
					any.setThree(rs.getString(3));
					any.setFour(rs.getString(4));
					any.setFive(rs.getString(5));
					any.setSix(rs.getString(6));
					any.setSeven(rs.getString(7));
					return any;
				} catch (SQLException e) {
					throw new BeanInstantiationException(UserEntity.class, e.getMessage(), e);
				}
			}
		};
		return jdbcTemplate.query("", rowMapper);
	}

	public int insertUserToGuildDao(long guildId, long userId) {
		logger.debug("inserting user to guild... guildId: " + guildId + ", userId: " + userId);
		return jdbcTemplate.update(INSERT_USER_TO_GUILD, guildId, userId);
	}

	public long getLastGuildId() {
		RowMapper<Long> rowMapper = new RowMapper<Long>() {
			
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getLong("last_id");
			}
		}; 
		return jdbcTemplate.queryForObject(GET_LAST_ID, rowMapper);
	}

	public List<GuildEntity> retrieveGuildSearchDao(String guildName) {
		logger.debug("searching guild by guild name... guildName: " + guildName);
		RowMapper<GuildEntity> rowMapper = new RowMapper<GuildEntity>() {
			public GuildEntity mapRow(ResultSet rs, int rowNum) {
				try {
					return new GuildEntity(rs.getLong("gid"), rs.getLong("leader_id"), rs.getString("guild_name"), rs.getString("status"));
				} catch (SQLException e) {
					throw new BeanInstantiationException(UserEntity.class, e.getMessage(), e);
				}
			}
		};
		return jdbcTemplate.query(RETRIEVE_GUILD_LIST, rowMapper, makeLikeParam(guildName));
	}
}