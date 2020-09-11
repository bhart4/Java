package orchestration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pubSubServer.AbstractChannel;
import pubSubServer.ChannelDiscovery;
import pubSubServer.SubscriptionManager;
import publishers.AbstractPublisher;
import publishers.PublisherFactory;
import publishers.PublisherType;
import states.subscriber.StateName;
import strategies.publisher.StrategyName;
import subscribers.AbstractSubscriber;
import subscribers.SubscriberFactory;
import subscribers.SubscriberType;

public class Orchestration {

	public static void main(String[] args) {

		List<AbstractPublisher> listOfPublishers = new ArrayList<>();
		List<AbstractSubscriber> listOfSubscribers = new ArrayList<>();
		Orchestration testHarness = new Orchestration();
		try {
			listOfPublishers = testHarness.createPublishers();
			listOfSubscribers = testHarness.createSubscribers();
			
			List<AbstractChannel> channels = ChannelDiscovery.getInstance().listChannels();
			//For demonstration purposes only
			try {
				BufferedReader initialChannels = new BufferedReader(new FileReader(new File("Channels.chl")));
				List<String> channelList = new ArrayList<String>();
				String line = "";
				while((line = initialChannels.readLine()) != null ) {
					channelList.add(line);
				}
				int subscriberIndex = 0;
				for(AbstractSubscriber subscriber : listOfSubscribers) {
					System.out.println(subscriber);
					// subscriber is null
					subscriber.subscribe(channelList.get(subscriberIndex%channelList.size()));
					subscriberIndex++;
				}
				initialChannels.close();
			} catch(IOException ioe) {
				System.out.println("Loading Channels from file failed proceeding with random selection");
				for(AbstractSubscriber subscriber : listOfSubscribers) {
					int index = (int) Math.round((Math.random()*10))/3;
					SubscriptionManager.getInstance().subscribe(channels.get(index).getChannelTopic(), subscriber);
				}
			}
			for(AbstractPublisher publisher : listOfPublishers) {
				System.out.println(publisher);
				publisher.publish();
			}
			
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			System.out.println("Will now terminate");
			return;
		}
		for(AbstractPublisher publisher : listOfPublishers) {
			System.out.println(publisher);
			publisher.publish();
		}
	}

	// reads line which is a pair of numbers 
	// split on 8 whole empty spaces 
	// create new array 
	// load values into it
	try {
	BufferedReader orchestration = new BufferedReader(new FileReader(new File("orchestration.orc")));

	String readIn = orchestration.readLine();

	while (readIn != null){
		//String word = tokenizer.nextToken();
		//StringTokenizer tokenizer = new StringTokenizer(readIn);

		if (tokenizer.countTokens() == 2 && tokenizer.nextToken() == "PUB"){
			int publisherID = Integer.parseInt(tokenizer.nextToken());
			String channelName = tokenizer.nextToken();

		} else if (tokenizer.countTokens() == 5 && tokenizer.nextToken() == "PUB"){
			int publisherID = Integer.parseInt(tokenizer.nextToken());
			String channelName = tokenizer.nextToken();
			String eventType = tokenizer.nextToken();
			String meventMssageHeader = tokenizer.nextToken();
			String eventMessagePayload = tokenizer.nextToken();

		} else if (tokenizer.countTokens() == 3 && tokenizer.nextToken() == "SUB"){
			int subscriberID = Integer.parseInt(tokenizer.nextToken());
			String channelName = tokenizer.nextToken();


		} else if (tokenizer.countTokens() == 3 && tokenizer.nextToken() == "BLOCK") {
			int subscriberID = Integer.parseInt(tokenizer.nextToken());
			String channelName = tokenizer.nextToken();

		} else if (tokenizer.countTokens() == 3 && tokenizer.nextToken() == "UNBLOCK") {
			int subscriberID = Integer.parseInt(tokenizer.nextToken());
			String channelName = tokenizer.nextToken();

		} else {
			throw new IllegalArgumentException("Illegal arguments formatting.");
		}
	}
	/*switch(word){

			case "SUB":

				int subscriberID = Integer.parseInt(tokenizer.nextToken());
				String channelName = tokenizer.nextToken();


				break;
			case "PUB":

				int publisherID= Integer.parseInt(tokenizer.nextToken());


				//PUB <publisher-ID> <event-type> <event-message-header> <event-message-paylod>
				if (tokenizer.hasMoreElements()){
					String eventType = tokenizer.nextToken();
					String meventMssageHeader = tokenizer.nextToken();
					String eventMessagePayload = tokenizer.nextToken();

				//PUB <publisher-ID>
				} else {
					AbstractPublisher publisher = PublisherFactory.createPublisher(publisherID);
					listOfPublishers.add(publisher);
				}

				break;
			case "BLOCK":

				int subscriberID = Integer.parseInt(tokenizer.nextToken());
				String channelName = tokenizer.nextToken();


				break;
			case "UNBLOCK":

				int subscriberID = Integer.parseInt(tokenizer.nextToken());
				String channelName = tokenizer.nextToken();

				break;
		}
	}*/
	
	private List<AbstractPublisher> createPublishers() throws IOException{
		List<AbstractPublisher> listOfPublishers = new ArrayList<>();
		AbstractPublisher newPub;
		BufferedReader StrategyBufferedReader = new BufferedReader(new FileReader(new File("Strategies.str")));
		while(StrategyBufferedReader.ready()) {
			String PublisherConfigLine = StrategyBufferedReader.readLine();
			System.out.println(PublisherConfigLine);
			String[] PublisherConfigArray = PublisherConfigLine.split("\t");
			int[] PublisherConfigIntArray = new int[2];
			for(int i = 0; i < PublisherConfigArray.length; i++)
				PublisherConfigIntArray[i] = Integer.parseInt(PublisherConfigArray[i]);
			newPub = PublisherFactory.createPublisher(
					PublisherType.values()[PublisherConfigIntArray[0]],
					StrategyName.values()[PublisherConfigIntArray[1]]);
			listOfPublishers.add(newPub);
		}
		StrategyBufferedReader.close();
		//System.out.println(listOfPublishers);
		return listOfPublishers;
	}
	
	private List<AbstractSubscriber> createSubscribers() throws IOException{
		List<AbstractSubscriber> listOfSubscribers = new ArrayList<>();
		AbstractSubscriber newSub;
		BufferedReader StateBufferedReader = new BufferedReader(new FileReader(new File("States.sts")));
		while(StateBufferedReader.ready()) {
			String StateConfigLine = StateBufferedReader.readLine();
			String[] StateConfigArray = StateConfigLine.split("\t");
			int[] StateConfigIntArray = new int[2];
			for(int i = 0; i < StateConfigArray.length; i++)
				StateConfigIntArray[i] = Integer.parseInt(StateConfigArray[i]);
			newSub = SubscriberFactory.createSubscriber(
					SubscriberType.values()[StateConfigIntArray[0]], 
					StateName.values()[StateConfigIntArray[1]]);
			listOfSubscribers.add(newSub);
		}
		StateBufferedReader.close();
		return listOfSubscribers;
	}
	
	
	
}
