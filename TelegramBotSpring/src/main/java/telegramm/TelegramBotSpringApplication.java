package telegramm;

import generated.GetCovidRequest;
import generated.GetCovidResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.ApiContext;
import telegramm.saopconsumer.SOAPConnector;

@SpringBootApplication
public class TelegramBotSpringApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(TelegramBotSpringApplication.class, args);
	}
	@Bean
	CommandLineRunner lookup(SOAPConnector soapConnector) {
		return args -> {
			String name = "Markus";//Default Name
			if(args.length>0){
				name = args[0];
			}
			GetCovidRequest request = new GetCovidRequest();
			request.setRValue(35);
			request.setInfo("/date");
			request.setNDays(7);
			GetCovidResponse response =(GetCovidResponse)soapConnector.callWebService(
					"https://covidsoap.herokuapp.com/ws/covid;",request
			) ;
			System.out.println("Got Response As below ========= : ");
			System.out.println("Info : "+response.getCovid().getJsonInfo());


			request.setRValue(0);
			request.setInfo("/infection");
			request.setNDays(0);
			response =(GetCovidResponse)soapConnector.callWebService(
					"https://covidsoap.herokuapp.com/ws/covid;",request
			) ;
			System.out.println("Next Abfrage ");
			System.out.println("Info : "+response.getCovid().getJsonInfo());
			request.setRValue(0);
			request.setInfo("/asdasd");
			request.setNDays(0);
			response =(GetCovidResponse)soapConnector.callWebService(
					"https://covidsoap.herokuapp.com/ws/covid;",request
			) ;
			System.out.println("Next Abfrage ");
			System.out.println("Info : "+response.getCovid().getJsonInfo());

		};
	}

}
