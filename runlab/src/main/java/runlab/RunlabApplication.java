package runlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import runlab.wrappers.APIWrapper;

@SpringBootApplication
public class RunlabApplication {

    @Bean
    public APIWrapper apiWrapper() {
        return new APIWrapper();
    }

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/runlab-api");
        SpringApplication.run(RunlabApplication.class, args);
    }

}
