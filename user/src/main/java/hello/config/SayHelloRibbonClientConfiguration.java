package hello.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Created by bapaydin on 02.04.2017.
 */

public class SayHelloRibbonClientConfiguration {

    @Autowired
    IClientConfig ribbonCientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig iClientConfig) {
        return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig iClientConfig) {
        return new AvailabilityFilteringRule();
    }
}