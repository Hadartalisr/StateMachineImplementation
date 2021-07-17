package States;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

import org.springframework.stereotype.Component;

import machine.models.Event;
import machine.models.State;
import models.Click;
import models.DoubleClick;

@Component
public class ClickState extends State {

	private AtomicInteger count = new AtomicInteger(1);
	private static final int COUNT_TO_PRINT = 3; // the amount of events in order to 
	protected List<Class<? extends State>> allPossibleCalculations;

	public ClickState() {
		this.allPossibleCalculations = new ArrayList<>();
		this.allPossibleCalculations.add(InitState.class); // 0
		this.allPossibleCalculations.add(DoubleClickState.class); // 1
	}

	
	@Override
	public int calculate(Event<?> event) {
		int ret = Integer.MAX_VALUE; // the default is to stay in the current state
		Class<?> eventClass = event.getEventType();
		if(eventClass.equals(Click.class)) { // if we should update the count of clicks
		    IntBinaryOperator ibo = (x, y) -> {
		    	int calc = x+y;
		    	return calc == COUNT_TO_PRINT ? 0: calc;
		    };
			int previousCount = this.count.getAndAccumulate(1, ibo);
			if(previousCount+1 == COUNT_TO_PRINT) {
				System.out.println("OMG! 3 Clicks were emitted !!!");
			}
		}
		else {// if the machine should be switched into other state
			this.count.set(1);
			if (eventClass.equals(DoubleClick.class)) { 
				ret = 1;
			} else { // event which is not click or doubleClick
				ret = 0;
			}
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
