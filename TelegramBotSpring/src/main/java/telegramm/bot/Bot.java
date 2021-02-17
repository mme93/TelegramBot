package telegramm.bot;

import javax.annotation.PostConstruct;

import generated.GetCovidRequest;
import generated.GetCovidResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

	@Autowired SOAPConnector soapConnector;

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
		System.err.println(command.substring(0,5));
		System.err.println(command.substring(5,command.length()));
		System.err.println("/"+command.substring(5,command.length()));
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
		GetCovidRequest request;
		GetCovidResponse response;
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
					String [] allSoapInfo=new String[8];
					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/date");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[0]=response.getCovid().getJsonInfo();

					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/infection");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[1]=response.getCovid().getJsonInfo();

					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/infected");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[2]=response.getCovid().getJsonInfo();

					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/increase");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[3]=response.getCovid().getJsonInfo();
					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/average");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[4]=response.getCovid().getJsonInfo();
					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/incidencevalue");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[5]=response.getCovid().getJsonInfo();
					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/incidencegoal");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[6]=response.getCovid().getJsonInfo();
					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/days");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[7]=response.getCovid().getJsonInfo();


				String allInfo = "Datum des Datensatz: " + allSoapInfo[0] + "\n" + "Es gab "
						+ allSoapInfo[1]
						+ " Neuinfektionen in den letzten 24 Stunden." + "\n" + "Die Gesamtcoronainfektionen liegen bei "
						+ allSoapInfo[2]+ " Menschen." + "\n"
						+ "Der prozentuale Anstieg der letzten 24 Stunden liegt bei "
						+ allSoapInfo[3]+ "%." + "\n"
						+ "Der Anstieg in den letzten 7 Tagen beträgt "
						+ allSoapInfo[4] + "\n"
						+ "Der Inzidenzwert für Deutschland liegt aktuell bei: "
						+ allSoapInfo[5]+ "\n" + "Der Ziel-Inzidenzwert ist"
						+ allSoapInfo[6] + "." + "\n"
						+ "Es dauert aktuell" + allSoapInfo[7]
						+ "Tage um den Ziel-Inzidenzwert zu erreichen.";
				return allInfo;

			case "/date":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/date");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Datum des Datensatz: " + response.getCovid().getJsonInfo();
			case "/infection":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/infection");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Es gab " + response.getCovid().getJsonInfo()
						+ " Neuinfektionen in den letzten 24 Stunden.";
			case "/infected":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/infected");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Die Gesamtcoronainfektionen liegen bei " + response.getCovid().getJsonInfo()
						+ " Menschen.";
			case "/increase":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/increase");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Der prozentuale Anstieg der letzten 24 Stunden liegt bei "
						+ response.getCovid().getJsonInfo() + "%.";
			case "/average":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/average");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Der Anstieg in den letzten 7 Tagen beträgt "
						+ response.getCovid().getJsonInfo();
			case "/incidencevalue":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/incidencevalue");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Der Inzidenzwert für Deutschland liegt aktuell bei: "
						+ response.getCovid().getJsonInfo();
			case "/incidencegoal":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/incidencegoal");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Der Ziel-Inzidenzwert ist " + response.getCovid().getJsonInfo()
						+ ".";
			case "/days":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/days");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Es dauert aktuell " + response.getCovid().getJsonInfo()+ "Tage um den Ziel-Inzidenzwert zu erreichen.";

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
