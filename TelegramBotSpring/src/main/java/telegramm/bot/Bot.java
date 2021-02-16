package telegramm.bot;

import javax.annotation.PostConstruct;

import generated.GetCovidRequest;
import generated.GetCovidResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import telegramm.httpRequest.CovidRequest;
import telegramm.saopconsumer.SOAPConnector;

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
		//SendMessage message = new SendMessage().setChatId(chatId).setText(this.switchRestToCommand(command, new CovidRequest()));
		SendMessage message = new SendMessage().setChatId(chatId).setText(this.switchSoapToCommand(command));
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
		switch (command) {
			case "/start":
				return "Es stehen folgende Befehle zur Verfügung:\r\n"
						+ "/infection - Gibt die Anzahl der Neuinfektionen innerhalb der letzten 24 Stunden an\r\n"
						+ "/infected - Gibt die Anzahl der aktuellen Gesamtinfektionen an\r\n"
						+ "/increase - Gibt den prozentualen Anstieg der Infektionen an\r\n"
						+ "/average - Gibt den durchschnittlichen Anstieg in den letzten 7 Tagen an\r\n"
						+ "/incidencevalue - Gibt den aktuellen Inzidenzwert an\r\n"
						+ "/incidencegoal - Gibt den Ziel-Inzidenzwert an\r\n"
						+ "/days - Gibt die Dauer bis zum Erreichen des Ziel-Inzidenzwert an\r\n"
						+ "/date - Gibt das Datum des Datensatzes an\r\n"
						+ "/showallinfo - Zeigt alle Informationen an";

			case "/date":
				GetCovidRequest request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/date");
				request.setNDays(7);
				SOAPConnector soapConnector = new SOAPConnector();
				GetCovidResponse response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Aktuelles Datum: "+response.getCovid().getJsonInfo();
			/*
				case "/showallinfo":
				String allInfo = "Datum des Datensatz: " + new JSONObject(request.getDate()).get("value") + "\n" + "Es gab "
						+ new JSONObject(request.getNewInfection()).get("value")
						+ " Neuinfektionen in den letzten 24 Stunden." + "\n" + "Die Gesamtcoronainfektionen liegen bei "
						+ new JSONObject(request.getTotalInfection()).get("value") + " Menschen." + "\n"
						+ "Der prozentuale Anstieg der letzten 24 Stunden liegt bei "
						+ new JSONObject(request.getPercenteInfection()).get("value") + "%." + "\n"
						+ "Der Anstieg in den letzten 7 Tagen beträgt "
						+ new JSONObject(request.getAverageIncreaseDay(7)).get("value") + "\n"
						+ "Der Inzidenzwert für Deutschland liegt aktuell bei: "
						+ new JSONObject(request.getRWerthTotalGermany()).get("value") + "\n" + "Der Ziel-Inzidenzwert ist"
						+ new JSONObject(request.getTotalTargetInfection(35)).get("value") + "." + "\n"
						+ "Es dauert aktuell" + new JSONObject(request.getTargetIncidenceForRWert(35, 7)).get("value")
						+ "Tage um den Ziel-Inzidenzwert zu erreichen.";
				return allInfo;
			case "/date":
				return "Datum des Datensatz: " + new JSONObject(request.getDate()).get("value");
			case "/infection":
				return "Es gab " + new JSONObject(request.getNewInfection()).get("value")
						+ " Neuinfektionen in den letzten 24 Stunden.";
			case "/infected":
				return "Die Gesamtcoronainfektionen liegen bei " + new JSONObject(request.getTotalInfection()).get("value")
						+ " Menschen.";
			case "/increase":
				return "Der prozentuale Anstieg der letzten 24 Stunden liegt bei "
						+ new JSONObject(request.getPercenteInfection()).get("value") + "%.";
			case "/average":
				return "Der Anstieg in den letzten 7 Tagen beträgt "
						+ new JSONObject(request.getAverageIncreaseDay(7)).get("value");
			case "/incidencevalue":
				return "Der Inzidenzwert für Deutschland liegt aktuell bei: "
						+ new JSONObject(request.getRWerthTotalGermany()).get("value");
			case "/incidencegoal":
				return "Der Ziel-Inzidenzwert ist " + new JSONObject(request.getTotalTargetInfection(35)).get("value")
						+ ".";
			case "/days":
				return "Es dauert aktuell " + new JSONObject(request.getTargetIncidenceForRWert(35, 7)).get("value")
						+ "Tage um den Ziel-Inzidenzwert zu erreichen.";
			*/
			default:
				return "Tut mir leid, diesen Befehl verstehe ich nicht.";
		}
	}

	public String switchRestToCommand(String command, CovidRequest request) {
		switch (command) {
		case "/start":
		return "Es stehen folgende Befehle zur Verfügung:\r\n"
				+ "/infection - Gibt die Anzahl der Neuinfektionen innerhalb der letzten 24 Stunden an\r\n"
				+ "/infected - Gibt die Anzahl der aktuellen Gesamtinfektionen an\r\n"
				+ "/increase - Gibt den prozentualen Anstieg der Infektionen an\r\n"
				+ "/average - Gibt den durchschnittlichen Anstieg in den letzten 7 Tagen an\r\n"
				+ "/incidencevalue - Gibt den aktuellen Inzidenzwert an\r\n"
				+ "/incidencegoal - Gibt den Ziel-Inzidenzwert an\r\n"
				+ "/days - Gibt die Dauer bis zum Erreichen des Ziel-Inzidenzwert an\r\n"
				+ "/date - Gibt das Datum des Datensatzes an\r\n"
				+ "/showallinfo - Zeigt alle Informationen an";
		case "/showallinfo":
			String allInfo = "Datum des Datensatz: " + new JSONObject(request.getDate()).get("value") + "\n" + "Es gab "
					+ new JSONObject(request.getNewInfection()).get("value")
					+ " Neuinfektionen in den letzten 24 Stunden." + "\n" + "Die Gesamtcoronainfektionen liegen bei "
					+ new JSONObject(request.getTotalInfection()).get("value") + " Menschen." + "\n"
					+ "Der prozentuale Anstieg der letzten 24 Stunden liegt bei "
					+ new JSONObject(request.getPercenteInfection()).get("value") + "%." + "\n"
					+ "Der Anstieg in den letzten 7 Tagen beträgt "
					+ new JSONObject(request.getAverageIncreaseDay(7)).get("value") + "\n"
					+ "Der Inzidenzwert für Deutschland liegt aktuell bei: "
					+ new JSONObject(request.getRWerthTotalGermany()).get("value") + "\n" + "Der Ziel-Inzidenzwert ist"
					+ new JSONObject(request.getTotalTargetInfection(35)).get("value") + "." + "\n"
					+ "Es dauert aktuell" + new JSONObject(request.getTargetIncidenceForRWert(35, 7)).get("value")
					+ "Tage um den Ziel-Inzidenzwert zu erreichen.";
			return allInfo;
		case "/date":
			return "Datum des Datensatz: " + new JSONObject(request.getDate()).get("value");
		case "/infection":
			return "Es gab " + new JSONObject(request.getNewInfection()).get("value")
					+ " Neuinfektionen in den letzten 24 Stunden.";
		case "/infected":
			return "Die Gesamtcoronainfektionen liegen bei " + new JSONObject(request.getTotalInfection()).get("value")
					+ " Menschen.";
		case "/increase":
			return "Der prozentuale Anstieg der letzten 24 Stunden liegt bei "
					+ new JSONObject(request.getPercenteInfection()).get("value") + "%.";
		case "/average":
			return "Der Anstieg in den letzten 7 Tagen beträgt "
					+ new JSONObject(request.getAverageIncreaseDay(7)).get("value");
		case "/incidencevalue":
			return "Der Inzidenzwert für Deutschland liegt aktuell bei: "
					+ new JSONObject(request.getRWerthTotalGermany()).get("value");
		case "/incidencegoal":
			return "Der Ziel-Inzidenzwert ist " + new JSONObject(request.getTotalTargetInfection(35)).get("value")
					+ ".";
		case "/days":
			return "Es dauert aktuell " + new JSONObject(request.getTargetIncidenceForRWert(35, 7)).get("value")
					+ "Tage um den Ziel-Inzidenzwert zu erreichen.";
		default:
			return "Tut mir leid, diesen Befehl verstehe ich nicht.";
		}
	}
}
