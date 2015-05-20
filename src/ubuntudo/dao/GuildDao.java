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
import support.QrysU;
import ubuntudo.model.GuildEntity;
import ubuntudo.model.UserEntity;

@Repository
public class GuildDao {
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
		return jdbcTemplate.update(QrysG.INSERT_GUILD, guild.getGuildName(), guild.getLeaderId());
	}

	public long getLastGuildId() {
		RowMapper<Long> rowMapper = new RowMapper<Long>() {

			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getLong("last_id");
			}
		};
		return jdbcTemplate.queryForObject(Qrys.GET_LAST_ID, rowMapper);
	}

	public int insertUserToGuildDao(long guildId, long userId) {
		logger.debug("inserting user to guild... guildId: " + guildId + ", userId: " + userId);
		return jdbcTemplate.update(QrysU.INSERT_USER_TO_GUILD, userId, guildId);
	}

	public List<GuildEntity> retrieveGuildListSearchDao(String guildName) {
		logger.debug("searching guild by guild name... guildName: " + guildName);
		RowMapper<GuildEntity> rowMapper = new RowMapper<GuildEntity>() {
			public GuildEntity mapRow(ResultSet rs, int rowNum) {
				try {
					return new GuildEntity(rs.getLong("gid"), rs.getLong("leader_id"), rs.getString("g_name"), rs.getString("deleted"));
				} catch (SQLException e) {
					throw new BeanInstantiationException(UserEntity.class, e.getMessage(), e);
				}
			}
		};
		return jdbcTemplate.query(QrysG.RETRIEVE_GUILD_LIST_SEARCH, rowMapper, Qrys.makeLikeParam(guildName));
	}

	public int updateGuildDao(GuildEntity guild) {
		logger.debug("updating current guild to: " + guild);
		return jdbcTemplate.update(QrysG.UPDATE_GUILD, guild.getLeaderId(), guild.getGuildName(), guild.getStatus(), guild.getGid());
	}


	public List<Map<String, Object>> retrieveMyGuildListDao(long uid) {
		logger.debug("retrieving my guilds... user id: " + uid);
		return jdbcTemplate.queryForList(QrysG.RETRIEVE_MY_GUILD_LIST, uid);
	}

	public List<Map<String, Object>> retrieveGuildDetail(long gid) {
		logger.debug("retrieving detail info of a guild... guild id: " + gid);
		return jdbcTemplate.queryForList(QrysG.RETRIEVE_GUILD_DETAIL, gid, gid, gid);
	}
}