package telegramm.bot;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import telegramm.httpRequest.CovidRequest;

@Component
public class Bot extends TelegramLongPollingBot {

	@PostConstruct
	public void registerBot() {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(this);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUpdateReceived(Update update) {

		long chatId = update.getMessage().getChatId();
		String command = update.getMessage().getText();
		SendMessage message = new SendMessage().setChatId(chatId).setText(this.switchRestToCommand(command, new CovidRequest()));
		try {
			execute(message); // Sending our message object to user
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getBotUsername() {
		return "CoronaDailyUpdateBot";
	}

	@Override
	public String getBotToken() {
		return "1645650570:AAGIOWNjEgsV_I1c3RNjJTcsliXo5eKQj3E";
	}

	private String switchSoapToCommand(String command) {
		return null;
	}

	public String switchRestToCommand(String command, CovidRequest request) {
		switch (command) {
		case "/infection":
			return "Es gab " + request.getNewInfection() + " Neuinfektionen in den letzten 24 Stunden.";
		case "/infected":
			return "Die Gesamtcoronainfektionen liegen bei " + request.getTotalInfection() + " Menschen.";
		case "/increase":
			return "Der prozentuale Anstieg der letzten 24 Stunden liegt bei " + request.getPercenteInfection() + "%.";
		case "/average":
			return "Der Anstieg in den letzten 7 Tagen beträgt " + request.getAverageIncreaseDay(7);
		case "/incidencevalue":
			return "Der Inzidenzwert für Deutschland liegt aktuell bei: " + request.getRWerthTotalGermany();
		case "/incidencegoal":
			return "Der Ziel-Inzidenzwert ist" + request.getTotalTargetInfection(35) + ".";
		case "/days":
			return "Es dauert aktuell"+request.getTargetIncidenceForRWert(35, 7)+ "Tage um den Ziel-Inzidenzwert zu erreichen.";
		default:
			return "Tut mir leid, diesen Befehl verstehe ich nicht.";
		}
	}

}
