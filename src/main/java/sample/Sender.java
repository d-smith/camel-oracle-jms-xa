
package sample;


import oracle.jms.AQjmsFactory;
import oracle.jms.AQjmsSession;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Sender
{
    public static void main(String[] args) throws Exception
    {


        QueueConnectionFactory qfact = AQjmsFactory.getQueueConnectionFactory(
                "da896009", "XE", 1521, "thin"
        );

        QueueConnection qcon = qfact.createQueueConnection("batch", "password") ;
        QueueSession qsess = qcon.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);
        Queue queue = ((AQjmsSession) qsess).getQueue("batch", "sample_q");
        QueueSender sender = qsess.createSender(queue);
        for(int i = 0; i < 10000; i++) {
            System.out.println("Send message...");
            TextMessage tm = ((AQjmsSession) qsess).createTextMessage("Hello, Oracle JMS");
            sender.send(tm);
            //Thread.sleep(500);
        }
    }
}
