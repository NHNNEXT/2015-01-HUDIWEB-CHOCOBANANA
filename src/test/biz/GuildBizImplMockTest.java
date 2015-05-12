package test.biz;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ubuntudo.biz.GuildBizImpl;
import ubuntudo.dao.GuildDao;
import ubuntudo.model.GuildEntity;

@RunWith(MockitoJUnitRunner.class)
public class GuildBizImplMockTest {
	
	@InjectMocks
	private GuildBizImpl guildBiz;
	
	@Mock
	private GuildDao guildDao;
	
	static class Fixtures {
		
		static GuildEntity getGuild() {
			GuildEntity guild = new GuildEntity(1, 100, "GUILD_NAME", "GUILD_STATUS");
			
			return guild;
		}
	}

	@Test
	public void createGuild_S() {
		GuildEntity guild = Fixtures.getGuild();
		when(guildDao.insertNewGuildDao(any())).thenReturn(1);
		
		guildBiz.createGuild(guild);
		verify(guildDao, times(1)).insertNewGuildDao(any());
	}
	
	@Test(expected=IllegalStateException.class)
	public void createGuild_F() {
		GuildEntity guild = Fixtures.getGuild();
		when(guildDao.insertNewGuildDao(any())).thenReturn(0);
		
		guildBiz.createGuild(guild);
	}
	
	@Test
	public void joinLeader_S() {
		when(guildDao.insertUserToGuildDao(1, 1)).thenReturn(1);
		
		guildBiz.joinLeader(1, 1);
		verify(guildDao, times(1)).insertUserToGuildDao(1, 1);
	}
	
	@Test(expected=IllegalStateException.class)
	public void joinLeader_F() {
		when(guildDao.insertUserToGuildDao(1, 1)).thenReturn(0);
		
		guildBiz.joinLeader(1, 1);
	}
	
	@Test
	public void lastGuildId_S() {
		long expectedLastGuildId = 10L;
		when(guildDao.getLastGuildId()).thenReturn(expectedLastGuildId);
		
		assertThat(guildBiz.lastGuildId(), equalTo(expectedLastGuildId));
	}
	
	@Test(expected=IllegalStateException.class)
	public void lastGuildId_F() {
		long expectedLastGuildId = 0L;
		when(guildDao.getLastGuildId()).thenReturn(expectedLastGuildId);
		
		guildBiz.lastGuildId();
	}

}
