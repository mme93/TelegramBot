package hrw.verteiltesysteme.telegramm.soap.config;

import hrw.verteiltesysteme.telegramm.soap.SOAPConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

@EnableWs
@Configuration
public class CovidSoapConsumerConfig extends WsConfigurerAdapter {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("generated");
        return marshaller;
    }

    @Bean
    public SOAPConnector soapConnector(Jaxb2Marshaller marshaller) {
        SOAPConnector client = new SOAPConnector();
        client.setDefaultUri("https://covidwebservice.herokuapp.com/ws/covid");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
