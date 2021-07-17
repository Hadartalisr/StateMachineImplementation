package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import machine.models.Event;
import machine.models.Machine;
import machine.models.MachineProcessResponse;
import machine.models.State;

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


	public MachineProcessResponse process(Event<?> event) {
		// TODO validate the event
		return this.machine.process(event);
	}

	public State getCurrentState() {
		return this.machine.getCurrentState();
	}

}
