package tech.oliver.orderworkerms;

import org.springframework.boot.SpringApplication;

import java.util.List;
import java.util.Map;

import static tech.oliver.orderworkerms.ContainersConfig.*;

public class TestOrderworkermsApplication {

	public static void main(String[] args) {
		localStackContainer.setPortBindings(List.of("4566:4566"));
		localStackContainer.start();
		setupSqs();
        for (Map.Entry<String, Object> entry : getProperties().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.setProperty(key, (String) value);
        }

        SpringApplication.from(OrderworkermsApplication::main)
				.with(ServiceConnectionConfig.class)
				.run(args);
	}

}
