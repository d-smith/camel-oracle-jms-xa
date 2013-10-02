
package sample;

import oracle.jms.AQjmsFactory;
import oracle.jms.AQjmsSession;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Receiver
{
    public static void main(String[] args) throws Exception
    {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";

        QueueConnectionFactory qfact = AQjmsFactory.getQueueConnectionFactory(
                "da896009", "XE", 1521, "thin"
        );

        QueueConnection qcon = qfact.createQueueConnection("batch", "password");
        QueueSession qsess = qcon.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);
        Queue queue = ((AQjmsSession) qsess).getQueue("batch", "sample_q");

        QueueReceiver queueReceiver = qsess.createReceiver(queue);
        qcon.start();
        TextMessage message = (TextMessage) queueReceiver.receive();
        System.out.println("received: " + message.getText());
        qcon.close();
    }
}
