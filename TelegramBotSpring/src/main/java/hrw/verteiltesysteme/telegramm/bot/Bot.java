package hrw.verteiltesysteme.telegramm.bot;

import javax.annotation.PostConstruct;

import generated.GetCovidRequest;
import generated.GetCovidResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import hrw.verteiltesysteme.telegramm.httpRequest.CovidRequest;
import hrw.verteiltesysteme.telegramm.soap.SOAPConnector;
/**
 *
 * @author Mithulan Satheskumar und Simone Lohmann
 *
 */
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
		SendMessage message;
		long chatId = update.getMessage().getChatId();
		String command = update.getMessage().getText();
		if(command.equals("/start")){
			String startInfo="Es stehen folgende Befehle zur Verfügung:\r\n"
					+"/restshowallinfo – zeigt alle informationen an\r\n"
					+"/restinfection – gibt die Anzahl der Neuinfektionen in den letzten 24 Stunden an\r\n"
					+"/restinfected – gibt die aktuelle Anzahl der Gesamtinfektionen an\r\n"
					+"/restincrease - gibt den prozentualen Anstieg der Infektionen\r\n"
					+"/restaverage - gibt den durchschnittlichen Anstieg in den letzten 7 Tagen an\r\n"
					+"/restincidencevalue - gibt den aktuellen Inzidenzwert an\r\n"
					+"/restincidencegoal - gibt den Ziel-Inzidenzwert an\r\n"
					+"/restdays - gibt die Dauer bis zum Erreichen des Ziel-Inzidenzwert an\r\n"
					+"/restdate - gibt das Datum des Datensatzes an\r\n"
					+"/soapshowallinfo – zeigt alle informationen an\r\n"
					+"/soapinfection – gibt die Anzahl der Neuinfektionen in den letzten 24 Stunden an\r\n"
					+"/soapinfected – gibt die aktuelle Anzahl der Gesamtinfektionen an\r\n"
					+"/soapincrease - gibt den prozentualen \r\n";
			message = new SendMessage().setChatId(chatId).setText(startInfo);
		}else if(command.substring(0,5).equals("/soap")){
			command="/"+command.substring(5,command.length());
			 message = new SendMessage().setChatId(chatId).setText(this.switchSoapToCommand(command));
		}else if(command.substring(0,5).equals("/rest")){
			command="/"+command.substring(5,command.length());
			 message = new SendMessage().setChatId(chatId).setText(this.switchRestToCommand(command, new CovidRequest()));
		}else{
			 message = new SendMessage().setChatId(chatId).setText("Befehl wurde nicht erkannt.");
		}
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

					allSoapInfo[0]=new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();

					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/infection");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[1]=new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();

					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/infected");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[2]=new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();

					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/increase");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[3]=new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();
					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/average");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[4]=new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();
					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/incidencevalue");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[5]=new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();
					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/incidencegoal");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[6]=new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();
					request = new GetCovidRequest();
					request.setRValue(35);
					request.setInfo("/days");
					request.setNDays(7);
					response =(GetCovidResponse)soapConnector.callWebService(
							"https://covidsoap.herokuapp.com/ws/covid;",request
					) ;

					allSoapInfo[7]=new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();


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
				return "Datum des Datensatz: " + new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();
			case "/infection":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/infection");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Es gab " + new JSONObject(response.getCovid().getJsonInfo()).get("value").toString()
						+ " Neuinfektionen in den letzten 24 Stunden.";
			case "/infected":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/infected");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Die Gesamtcoronainfektionen liegen bei " + new JSONObject(response.getCovid().getJsonInfo()).get("value").toString()
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
						+ new JSONObject(response.getCovid().getJsonInfo()).get("value").toString() + "%.";
			case "/average":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/average");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Der Anstieg in den letzten 7 Tagen beträgt "
						+ new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();
			case "/incidencevalue":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/incidencevalue");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Der Inzidenzwert für Deutschland liegt aktuell bei: "
						+ new JSONObject(response.getCovid().getJsonInfo()).get("value").toString();
			case "/incidencegoal":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/incidencegoal");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;
				return "Der Ziel-Inzidenzwert ist " + new JSONObject(response.getCovid().getJsonInfo()).get("value").toString()
						+ ".";
			case "/days":
				request = new GetCovidRequest();
				request.setRValue(35);
				request.setInfo("/days");
				request.setNDays(7);
				response =(GetCovidResponse)soapConnector.callWebService(
						"https://covidsoap.herokuapp.com/ws/covid;",request
				) ;

				return "Es dauert aktuell " + new JSONObject(response.getCovid().getJsonInfo()).get("value").toString()+ "Tage um den Ziel-Inzidenzwert zu erreichen.";

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
