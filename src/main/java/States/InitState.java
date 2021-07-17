package States;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import machine.models.Event;
import machine.models.State;
import models.Click;
import models.DoubleClick;

@Component
public class InitState extends State{
	
	protected List<Class<? extends State>> allPossibleCalculations;
	
	public InitState() {
		this.allPossibleCalculations = new ArrayList<>();
		this.allPossibleCalculations.add(ClickState.class); // 0
		this.allPossibleCalculations.add(DoubleClickState.class); // 1
	}

	
	@Override
	public int calculate(Event<?> event) {
		int ret = Integer.MAX_VALUE; // the default is to stay in the current state
		Class<?> eventClass = event.getEventType();
		if(eventClass.equals(Click.class)) {
			ret = 0;
		}
		else if (eventClass.equals(DoubleClick.class)) {
			ret = 1;
		}
		return ret;
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
