package tech.oliver.orderworkerms;

import org.springframework.boot.SpringApplication;

public class TestOrderworkermsApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderworkermsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
