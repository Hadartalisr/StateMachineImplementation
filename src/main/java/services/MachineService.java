package services;

import org.springframework.beans.factory.annotation.Autowired;

import machine.models.Event;
import machine.models.Machine;
import machine.models.MachineProcessResponse;
import machine.models.State;

@org.springframework.stereotype.Service
public class MachineService {
	
	@Autowired
	Machine machine;

	public void start() {
		// TODO implement what happens when the machine is already running
		this.machine.start();
	}

	public void stop() {
		// TODO implement what happens when the machine is already in STOP mode
		this.machine.stop();
	}
	
	public MachineProcessResponse process(Event<?> event) {
		// TODO validate the event
		return this.machine.process(event);
	}
	
	public State getCurrentState() {
		return this.machine.getCurrentState();
	}
	
}
