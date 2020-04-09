package by.bobruisk.homework;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
        basePackageClasses = {MainApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer{

	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	
}
