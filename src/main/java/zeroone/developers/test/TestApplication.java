package zeroone.developers.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String encryptedKey = bc.encode("latta");
        System.out.println(encryptedKey);


        // for swagger documentation
        // http://localhost:8080/swagger-ui/index.html

        // my portfolio
        // https://nizomiddin-portfolio.netlify.app/




    }

}
