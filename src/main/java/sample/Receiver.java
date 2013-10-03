
package sample;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jms.AQjmsFactory;
import oracle.jms.AQjmsSession;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.sql.DataSource;
import java.sql.SQLException;

public class Receiver
{
    private static DataSource createDataSource(String url, String userName, String password ) throws SQLException
    {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(url);
        ods.setUser(userName);
        ods.setPassword(password);

        return ods;
    }

    public static void main(String[] args) throws Exception
    {
        final String user = "batch";
        final String password = "password";
        final String url = "jdbc:oracle:thin:@da896009:1521:XE";

        QueueConnectionFactory qfact = AQjmsFactory.getQueueConnectionFactory(
                createDataSource(url, user, password)
        );

        QueueConnection qcon = qfact.createQueueConnection();
        QueueSession qsess = qcon.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);
        Queue queue = ((AQjmsSession) qsess).getQueue("batch", "source");

        QueueReceiver queueReceiver = qsess.createReceiver(queue);
        qcon.start();
        for(;;) {
            TextMessage message = (TextMessage) queueReceiver.receive();
            System.out.println("received: " + message.getText());
        }
        //qcon.close();
    }
}
