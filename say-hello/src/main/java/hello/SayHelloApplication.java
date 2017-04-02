package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by bapaydin on 02.04.2017.
 */

@SpringBootApplication
@RestController
public class SayHelloApplication {

    private static Logger log = LoggerFactory.getLogger(SayHelloApplication.class);


    @GetMapping(value = "/greeting")
    @ResponseBody
    public String greeting() {
        log.info("Access /greeting");

        List<String> greetings = Arrays.stream(new String[]{"Hi there", "Greetings", "Salutations"}).collect(Collectors.toList());
        Random random = new Random();

        int index = random.nextInt(greetings.size());
        return greetings.get(index);
    }

    @GetMapping(value = "/**")
    @ResponseBody
    public String home() {
        log.info("Access /**");
        return "Hi";
    }

    public static void main(String[] args) {
        SpringApplication.run(SayHelloApplication.class, args);
    }
}
