package hrw.verteiltesysteme.telegramm.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
/**
 *
 * @author Mithulan Satheskumar und Simone Lohmann
 *
 */
@Configuration
public class TelegrammBotSpringConfig {

	@Bean
	public BotSession botsession() {
		return new DefaultBotSession();
	}
}
