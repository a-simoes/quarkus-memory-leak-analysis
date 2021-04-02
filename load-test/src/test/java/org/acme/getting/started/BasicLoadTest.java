package org.acme.getting.started;

import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.DslTestPlan;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;
import org.eclipse.jetty.http.HttpMethod;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import static us.abstracta.jmeter.javadsl.JmeterDsl.htmlReporter;
import static us.abstracta.jmeter.javadsl.JmeterDsl.httpSampler;
import static us.abstracta.jmeter.javadsl.JmeterDsl.testPlan;
import static us.abstracta.jmeter.javadsl.JmeterDsl.threadGroup;

public class BasicLoadTest {

    @Test
    public void testPerformance() throws IOException {
        String reportPath = "html-report" + Instant.now().toString().replace(":", "-");

        DslHttpSampler getSampler = httpSampler("get", "http://localhost:8080/hello")
                .method(HttpMethod.GET);

        DslTestPlan testPlan = testPlan(
                threadGroup(50, 100000, getSampler)
                        .rampUpPeriod(Duration.ofSeconds(30)),
                htmlReporter(reportPath));

        testPlan.run();
    }
}
