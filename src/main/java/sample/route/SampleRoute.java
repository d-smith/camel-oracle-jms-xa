package sample.route;

import org.apache.camel.builder.RouteBuilder;


public class SampleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:queue:batch.sample_q?connectionFactory=AQconnectionFactory&transacted=true&transactionManager=#jtaTransactionManager")
                .to("bean:inserter")
                .to("bean:flakey")
                .to("log:sample.log?level=INFO");

    }
}
