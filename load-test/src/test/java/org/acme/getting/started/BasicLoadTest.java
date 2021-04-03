package org.acme.getting.started;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.property.IntegerProperty;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.collections.HashTree;
import org.apache.logging.log4j.core.appender.ConfigurationFactoryData;
import org.eclipse.jetty.http.HttpMethod;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.JmeterDsl;
import us.abstracta.jmeter.javadsl.core.DslTestElement;
import us.abstracta.jmeter.javadsl.core.DslTestPlan;
import us.abstracta.jmeter.javadsl.core.preprocessors.DslJsr223PreProcessor;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import static us.abstracta.jmeter.javadsl.JmeterDsl.htmlReporter;
import static us.abstracta.jmeter.javadsl.JmeterDsl.httpSampler;
import static us.abstracta.jmeter.javadsl.JmeterDsl.testPlan;
import static us.abstracta.jmeter.javadsl.JmeterDsl.threadGroup;

public class BasicLoadTest {

    private static final int REQUEST_TIMEOUT_MS = 5000;

    private static final int NUMBER_OF_WORKERS = 100;
    private static final int WORKERS_RAMP_UP_PERIOD_SEC = 30;

    private static final int TEST_DURATION_SEC = 150;

    private static final String URL_ENDPOINT = "http://localhost:8080/hello";

    @Test
    public void testResourceLeak() throws IOException {
        String reportPath = "html-report_" + Instant.now().toString().replace(":", "-");

        DslHttpSampler sampler = httpSampler("hello", preProcessorVars -> {
            preProcessorVars.sampler.setProperty(new IntegerProperty(HTTPSamplerBase.CONNECT_TIMEOUT, REQUEST_TIMEOUT_MS));
            preProcessorVars.sampler.setProperty(new IntegerProperty(HTTPSamplerBase.RESPONSE_TIMEOUT, REQUEST_TIMEOUT_MS));
            return URL_ENDPOINT;
        }).method(HttpMethod.GET);


        DslTestPlan testPlan = testPlan(
                threadGroup(NUMBER_OF_WORKERS, Duration.ofSeconds(TEST_DURATION_SEC), sampler)
                        .rampUpPeriod(Duration.ofSeconds(WORKERS_RAMP_UP_PERIOD_SEC)),
                htmlReporter(reportPath));

        testPlan.run();
    }

}
