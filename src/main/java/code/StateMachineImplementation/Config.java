package code.StateMachineImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import machine.factory.MachineFactory;
import machine.models.Machine;
import machine.models.State;

@Configuration
public class Config {


	@Autowired
	StateA stateA;

	@Autowired
	StateB stateB;
	
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
