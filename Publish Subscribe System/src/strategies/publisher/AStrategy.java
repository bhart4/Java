package strategies.publisher;

import events.AbstractEvent;

public class AStrategy implements IStrategy {

	@Override
	public void doPublish(int publisherId) {
		//Output in the console for "Publication of an event by a publisher"
		System.out.println("Publisher " + this + " publishes event " + event);
	}

	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		//Output in the console for "Publication of an event by a publisher"
		System.out.println("Publisher " + this + " publishes event");
	}

}
