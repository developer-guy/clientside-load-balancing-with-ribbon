package hello;

import hello.config.SayHelloRibbonClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RestController
@RibbonClient(name = "say-hello", configuration = SayHelloRibbonClientConfiguration.class)
public class UserApplication {

    private static final Logger logger = LoggerFactory.getLogger(UserApplication.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "Artaban") String name) {
        int port = loadBalancerClient.choose("say-hello").getPort();
        logger.info("Choosen port for say-hello application: " + port);
        String greeting = restTemplate.getForObject("http://say-hello:8090/greeting", String.class);
        return String.format("%s , %s", greeting, name);
    }


    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
