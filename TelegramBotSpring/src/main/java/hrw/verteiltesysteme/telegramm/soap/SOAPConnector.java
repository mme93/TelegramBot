package hrw.verteiltesysteme.telegramm.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
/**
 *
 * @author Markus Meier, Leon Wagner und Leona Cerimi
 *
 */
public class SOAPConnector extends WebServiceGatewaySupport {

    public Object callWebService(String url, Object request){

        return getWebServiceTemplate().marshalSendAndReceive(url, request);
    }
}
