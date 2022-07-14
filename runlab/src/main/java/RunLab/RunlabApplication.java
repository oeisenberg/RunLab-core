package RunLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import RunLab.wrappers.Strava;

@SpringBootApplication
public class RunlabApplication {

    @Bean
    public Strava stravaWrapper() {
        return new Strava();
    }

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/runlab-api");
        SpringApplication.run(RunlabApplication.class, args);
    }

}