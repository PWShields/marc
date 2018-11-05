package au.gov.nla.marc;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarcApplication  {


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MarcApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }


}
