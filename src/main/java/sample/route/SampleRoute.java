package sample.route;

import org.apache.camel.builder.RouteBuilder;


public class SampleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:queue:batch.source?concurrentConsumers=5")
                .to("bean:inserter")
                .to("bean:flakey")
                .to("jms:queue:batch.destination");

    }
}
