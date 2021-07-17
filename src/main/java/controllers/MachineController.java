package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import machine.models.Event;
import machine.models.MachineProcessResponse;
import machine.models.State;
import machine.models.Status;
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

	@PostMapping("/process")
	public ResponseEntity<MachineProcessResponse> process(@RequestBody Object eventObject) {
		JsonObject json = JsonParser.parseString(eventObject.toString()).getAsJsonObject();
		String type = json.get("type").getAsString();
		Class<?> clazz = null;
		Object object = null;
		try {
			clazz = Class.forName(String.format("models.%s", type));
			object = new Gson().fromJson(json, clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// TODO if the JSON do not contain the type field
		}
		Event<?> event = new Event<>(object);   
		HttpStatus httpStatus = HttpStatus.OK;
		MachineProcessResponse processResponse = this.machineService.process(event);
		if(!processResponse.getStatus().equals(Status.OK)) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MachineProcessResponse>(processResponse, httpStatus);
	}

}
