package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import machine.models.State;
import services.MachineService;

@RestController
@ComponentScan({ "services" })
public class MachineController {

	@Autowired
	MachineService machineService;

	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}

	@GetMapping("/isRunning")
	public ResponseEntity<Boolean> isRunning() {
		return new ResponseEntity<Boolean>(this.machineService.isRunning(), HttpStatus.OK);
	}

	@PostMapping("/isRunning/{isRunning}")
	public ResponseEntity<Boolean> setIsRunning(@PathVariable String isRunning) {
		HttpStatus httpStatus = HttpStatus.OK;
		Boolean returnBool = true;
		if (isRunning.equalsIgnoreCase("true") || isRunning.equalsIgnoreCase("false")) {
		    this.machineService.setIsRunning(Boolean.valueOf(isRunning));
		} else {
			returnBool = false;
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Boolean>(returnBool, httpStatus);
	}
	
	@GetMapping("/currentState")
	public ResponseEntity<State> getCurrentState() {
		State state = this.machineService.getCurrentState();
		return new ResponseEntity<State>(state, HttpStatus.OK);
	}

//	public MachineProcessResponse process(Event<?> event) {
//		
//	}
//	

}
