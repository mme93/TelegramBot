package telegramm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.ApiContext;

@SpringBootApplication
public class TelegramBotSpringApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TelegramBotSpringApplication.class, args);
	}
	

}
