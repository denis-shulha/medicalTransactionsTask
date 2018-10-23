package itsm.liquiBaseSample.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageSender {

    private JmsTemplate jmsTemplate;

    public JmsMessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendRequest(Object request) {
        jmsTemplate.convertAndSend(request);
    }

    public void sendResponse(String response){
        jmsTemplate.convertAndSend(response);
    }
}
