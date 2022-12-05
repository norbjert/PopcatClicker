package de.norbjert.popcatclicker;

import de.norbjert.popcatclicker.service.ClickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class PopcatClickerApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(PopcatClickerApplication.class, args);
	}

	@Override
	public void run(String... args) {

		ClickerService controller = (ClickerService) appContext.getBean("ClickerService");
		controller.spamClick();

	}

}
