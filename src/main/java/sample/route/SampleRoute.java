package sample.route;

import org.apache.camel.builder.RouteBuilder;


public class SampleRoute extends RouteBuilder {
    private static final String COMPONENT_CONFIG =
            "?connectionFactory=AQconnectionFactory" +
                    "&transacted=true&transactionManager=#jtaTransactionManager" +
                    "&concurrentConsumers=5";
    @Override
    public void configure() throws Exception {
        from("jms:queue:batch.sample_q" + COMPONENT_CONFIG)
                .to("bean:inserter")
                .to("bean:flakey")
                .to("log:sample.log?level=INFO");

    }
}
