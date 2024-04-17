package Contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
//@EnableJpaRepositories(basePackages = {"Contact.data"})
public class MyAnnotationApp {
    public static void main(String[] args) {
        SpringApplication.run(MyAnnotationApp.class, args);
    }
}
