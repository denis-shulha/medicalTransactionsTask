package itsm.liquiBaseSample.jms;


import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.*;

public class TransactionsReportMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        if(object instanceof TransactionsReportRequest) {
            return session.createObjectMessage((TransactionsReportRequest) object);
        }
        else
            return session.createTextMessage((String) object);

    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        if (message instanceof ObjectMessage)
            return ((ObjectMessage)message).getObject();
        else
            return ((TextMessage)message).getText();

    }
}
