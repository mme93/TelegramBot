package hrw.verteiltesysteme.telegramm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
/**
 *
 * @author Mithulan Satheskumar und Simone Lohmann
 *
 */
@SpringBootApplication
public class TelegramBotSpringApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(TelegramBotSpringApplication.class, args);
	}
}
