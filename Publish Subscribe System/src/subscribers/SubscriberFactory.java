package subscribers;

import publishers.ConcretePublisher;
import states.subscriber.StateName;
import strategies.publisher.StrategyFactory;

/**
 * @author kkontog, ksiouni, mgrigori
 *  
 */
/**
 * @author kkontog, ktsiouni, mgrigori
 * creates new {@link AbstractSubscriber} objects
 * contributes to the State design pattern
 * implements the FactoryMethod design pattern   
 */
public class SubscriberFactory {

	
	/**
	 * creates a new {@link AbstractSubscriber} using an entry from the {@link SubscriberType} enumeration
	 * @param subscriberType a value from the {@link SubscriberType} enumeration specifying the type of Subscriber to be created 
	 * @return the newly created {@link AbstractSubscriber} instance 
	 */
	public static AbstractSubscriber createSubscriber(SubscriberType subscriberType, StateName stateName) {
		AbstractSubscriber CSA = null;
		switch (subscriberType) {
			case alpha : 
				CSA = new ConcreteSubscriberA();
				CSA.setState(stateName);
				return CSA;
			case beta :
				CSA = new ConcreteSubscriberA();
				CSA.setState(stateName);
				return CSA;
			default:
				CSA = new ConcreteSubscriberA();
				CSA.setState(stateName);
				return CSA;
					
		}
	}
}
