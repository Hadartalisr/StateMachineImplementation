package components;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import machine.models.Event;
import machine.models.Machine;
import machine.models.MachineProcessResponse;

@SpringBootApplication
@ComponentScan({"controllers"})
public class StateMachineImplementationApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(StateMachineImplementationApplication.class, args);
	}
	

}
