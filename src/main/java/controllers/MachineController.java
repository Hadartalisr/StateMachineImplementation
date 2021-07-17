package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import services.MachineService;

@RestController
@ComponentScan({"services"})
public class MachineController {

	@Autowired
	MachineService machineService;
	
	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}

	@GetMapping("/isRunning")
	public Boolean isRunning() {
		return this.machineService.isRunning();
	}

	@PostMapping("/isRunning/{isRunning}")
	public void setIsRunning(@RequestHeader boolean isRunning) {
		//TODO add parsing
		this.machineService.setIsRunning(isRunning);
	}

//	public MachineProcessResponse process(Event<?> event) {
//		
//	}
//	
//	public State getCurrentState() {
//		
//	}

}
