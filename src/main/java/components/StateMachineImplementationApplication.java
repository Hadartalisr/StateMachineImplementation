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
	
	
// 	private static final String ENTER_EVENT_STRING = "Enter event :";
	
	
//	Machine machine = context.getBean(Machine.class);
//	Scanner scanner = new Scanner(System.in);
//	System.out.println(ENTER_EVENT_STRING);
//	while (scanner.hasNext() == true) {
//		String input = scanner.next();
//		Event<String> event = new Event<String>(input);
//		MachineProcessResponse machineProcessResponse = machine.process(event);
//		System.out.println(machineProcessResponse);
//		System.out.println(ENTER_EVENT_STRING);
//	}
	
	
//	@Override
//	public void printCurrentState() {
//		String decorator = String.format("\n%s\n", "*".repeat(100));
//		String currentStateStr = String.format("Current state: %s.", currentState);
//		System.out.println(decorator + currentStateStr + decorator);
//	}
	
	

}
