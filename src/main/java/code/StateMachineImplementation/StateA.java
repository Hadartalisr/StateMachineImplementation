package code.StateMachineImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import machine.models.State;

@Component
public class StateA extends State {

	List<Class<? extends State>> allPossibleCalculations;

	public StateA() {
		this.allPossibleCalculations = new ArrayList<>();
		this.allPossibleCalculations.add(StateA.class); // 0
		this.allPossibleCalculations.add(StateB.class); // 1
	}
	
	@Override
	public int calculate(Object event) {
		if(event instanceof String && event.equals("b")) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<Class<? extends State>> getAllPossibleCalculations() {
		return this.allPossibleCalculations;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
