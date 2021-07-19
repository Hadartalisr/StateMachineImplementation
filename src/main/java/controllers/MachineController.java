package controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import machine.models.MachineProcessResponse;
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

	@GetMapping("/currentState")
	public ResponseEntity<?> getCurrentState() {
		State state = null;
		if (this.machineService.isRunning()) {
			state = this.machineService.getCurrentState();
		} else {
			return new ResponseEntity<>("ERROR - the machine is not Running.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<State>(state, HttpStatus.OK);
	}

	@GetMapping("/states")
	public ResponseEntity<Set<Class<? extends State>>> getAllStates() {
		Set<Class<? extends State>> states = this.machineService.getAllStates();
		return new ResponseEntity<Set<Class<? extends State>>>(states, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/start/{stateClassName}")
	public ResponseEntity<Boolean> start(@PathVariable String stateClassName) {
		HttpStatus httpStatus = HttpStatus.OK;
		Boolean returnBool = true;
		Class<? extends State> entryStateClass = null;
		try {
			entryStateClass = (Class<? extends State>) Class.forName(stateClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (entryStateClass instanceof Class<?>) { // TODO handle if the string is not a class
			this.machineService.start(entryStateClass);
		} else {
			returnBool = false;
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Boolean>(returnBool, httpStatus);
	}

	@PostMapping("/stop")
	public ResponseEntity<Boolean> stop() {
		HttpStatus httpStatus = HttpStatus.OK;
		Boolean returnBool = true;
		this.machineService.stop();
		return new ResponseEntity<Boolean>(returnBool, httpStatus);
	}

	@PostMapping("/process")
	public ResponseEntity<MachineProcessResponse> process(@RequestBody Object eventObject) {
		HttpStatus httpStatus = HttpStatus.OK;
		MachineProcessResponse processResponse = this.machineService.process(eventObject);
		return new ResponseEntity<MachineProcessResponse>(processResponse, httpStatus);
	}

}
