package itsm.liquiBaseSample.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@Component
public class ReportRequestListener implements MessageListener {

    private JmsTemplate jmsTemplate;

    public ReportRequestListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String msg = ((TextMessage) message).getText();
                System.out.println("Message has been consumed : " + msg);
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("Message Error");
        }
    }

    public Object receiveMessage() throws JMSException {
        return  jmsTemplate.receiveAndConvert();
    }
}
