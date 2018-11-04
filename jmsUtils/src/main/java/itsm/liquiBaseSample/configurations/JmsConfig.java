package itsm.liquiBaseSample.configurations;

import itsm.liquiBaseSample.jms.TransactionsReportMessageConverter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@ComponentScan(basePackages = "itsm.liquiBaseSample.jms")
@PropertySource("classpath:jms.properties")
public class JmsConfig {

    @Value("${connection.url}")
    private String jmsConnectionURL;

    @Value("${destinationQueue.name}")
    private String destinationQueueName;

    @Bean
    public SingleConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(jmsConnectionURL);
        //factory.setTrustAllPackages(true);
        return new SingleConnectionFactory(factory);
    }


    @Bean
    public MessageConverter messageConverter() {
        return  new TransactionsReportMessageConverter();
    }

    @Bean
    public JmsTemplate jmsTemplate(SingleConnectionFactory factory, MessageConverter converter) {
        JmsTemplate jmsTemplate = new JmsTemplate(factory);
        jmsTemplate.setMessageConverter(converter);
        jmsTemplate.setDefaultDestination(destinationQueue());
        return jmsTemplate;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory
                = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

    @Bean
    public ActiveMQQueue destinationQueue() {
        return new ActiveMQQueue(destinationQueueName);
    }
}
