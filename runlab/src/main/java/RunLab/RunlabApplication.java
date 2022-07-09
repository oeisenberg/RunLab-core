package RunLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunlabApplication {
    
    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/Runlab");
        SpringApplication.run(RunlabApplication.class, args);
    }

}