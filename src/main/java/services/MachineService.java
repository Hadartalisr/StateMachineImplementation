package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import machine.models.Event;
import machine.models.Machine;
import machine.models.MachineProcessResponse;
import machine.models.State;
import machine.models.Status;

@Service
@Component
@ComponentScan({"components"})
public class MachineService {

	@Autowired
	Machine machine;

	public Boolean isRunning() {
		return this.machine.isRunning();
	}

	public void setIsRunning(boolean isRunning) {
		// TODO implement what happens when the machine is already running
		if(isRunning) {
			this.machine.start();	
		}
		else {
			this.machine.stop();
		}
	}

	public MachineProcessResponse process(Object eventObject) {
		JsonObject json = JsonParser.parseString(eventObject.toString()).getAsJsonObject();
		String type = json.get("type").getAsString();
		// TODO if the JSON do not contain the type field
		Class<?> clazz = null;
		Object object = null;
		MachineProcessResponse machineProcessResponse = null;
		try {
			clazz = Class.forName(String.format("models.%s", type));
		} catch (ClassNotFoundException e) {}
		if(clazz != null) {// if the event class exists in the project 
			object = new Gson().fromJson(json, clazz); //TODO try catch on the parse
			Event<?> event = new Event<>(object);   
			machineProcessResponse = this.machine.process(event);
		}
		return machineProcessResponse;
	}

	public State getCurrentState() {
		return this.machine.getCurrentState();
	}

}
