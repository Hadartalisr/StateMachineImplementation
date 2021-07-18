package states;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

import org.springframework.stereotype.Component;

import machine.models.Event;
import machine.models.State;
import models.A;
import models.B;


public class B2 extends State {

	@Override
	public Class<? extends State> calculate(Event<?> event) {
		Class<? extends State> nextStateClass = EntryState.class; // the default is to stay in the current state
		Class<?> eventClass = event.getEventType();
		if(eventClass.equals(A.class)) {
			nextStateClass = A1.class;
		}
		else if(eventClass.equals(B.class)) {
			nextStateClass = B3.class;
		}
		return nextStateClass;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}


}
