package ubuntudo.dao;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

import support.QueryCollection;
import ubuntudo.model.GuildEntity;

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
	public int insertGuildDao(GuildEntity guild) {
		logger.debug("inserting: " + guild.toString());
		return jdbcTemplate.update(INSERT_GUILD, guild.getGuildName(), guild.getLeaderId());
	}
}