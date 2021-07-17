package components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import States.ClickState;
import States.DoubleClickState;
import machine.factory.MachineFactory;
import machine.models.Machine;
import machine.models.State;

@Configuration
@ComponentScan({"States"})
public class Config {


	@Autowired
	ClickState stateA;

	@Autowired
	DoubleClickState stateB;
	
	@Bean
	public MachineFactory getMachineFactory() {
		return new MachineFactory();
	}

	@Bean
	public Machine getMachine(){		
		List<State> states = new ArrayList<>();
		states.add(stateA);
		states.add(stateB);
		return getMachineFactory().getMachineA(states, 0);
	}

}
