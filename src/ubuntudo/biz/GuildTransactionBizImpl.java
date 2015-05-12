package ubuntudo.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ubuntudo.model.GuildEntity;

@Service
public class GuildTransactionBizImpl implements GuildTransactionBiz {
	
	@Autowired
	private GuildBiz guildBiz;
	
	public GuildTransactionBizImpl() { }

	/*
	 * @transaction의 경우 jdk proxy로 동작하므로 
	 * interface로 구성되어야만 합니다.
	 * 
	 * 물론 interface를 적용하지 않을 수도 있는데 이따는
	 * 바이트코드를 직접수정할 수 있는 aspectj가 필요합니다.
	 * 
	 * db에 종속적이라 롤백테스트는 진행하지 못했습니다. ㅠ
	 * 
	 * 만약 하위에 있는 요청 메소드 중 하나라도 IllegalStateException 발생시 롤백되어지는 구조입니다.
	 */
	// (propagation=Propagation.REQUIRED) transaction의 기본속성 
	@Override
	@Transactional(rollbackFor={IllegalStateException.class})
	public long createGuild(GuildEntity guild) {
		
		long guildId;
		
		// check for args
		if (guild.getGid() == 0 || guild.getLeaderId() == 0 || "".equals(guild.getGuildName()) || "".equals(guild.getStatus())) {
			throw new IllegalArgumentException("저장 가능한 guild 상태가 아님");
		}
		
		guildBiz.createGuild(guild);
		guildId = guildBiz.lastGuildId();
		guildBiz.joinLeader(guildId, guild.getLeaderId());

		return guildId;
	}
	
}