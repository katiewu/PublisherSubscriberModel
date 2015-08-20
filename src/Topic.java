import java.util.HashMap;

public class Topic {
	
	private TopicQueue queue;
	private HashMap<Integer, TopicMessage> record;
//	private ClientSocketPool pool;
	private int size;
	public Topic(int size){
		queue = new TopicQueue();
		record = new HashMap<Integer, TopicMessage>();
//		this.pool = pool;
		this.size = size;
	}
	
	public synchronized void add(String message) throws InterruptedException{
		TopicMessage topicMessage = new TopicMessage(message);
		queue.add(topicMessage);
		if(queue.size() > 0){
			notifyAll();
		}
	}
	
	public synchronized String getNextMessage(int sign) throws InterruptedException{
		String message = "";
		if(record.containsKey(sign)){
			// current has already been processed.
			TopicMessage current = record.get(sign);
			// check whether current is the tail of the queue, if
			// it is, wait server to add message
			while(current.nextMessage == null){
				wait();
			}
			TopicMessage topicMessage = current.nextMessage;
			if(topicMessage == null) topicMessage = queue.head();
			message = topicMessage.getMessage();
			record.put(sign, topicMessage);
			topicMessage.count++;
			System.out.println(sign+" "+topicMessage.getMessage());
//			if(topicMessage.count == size){
//				// all the clients get this message
//				System.out.println(queue.remove());
//			}
		}
		else{
			// clientSocket is just inserted into the pool,
			// it is the first time to get message from queue
			// retrieve the first message from queue
			while(queue.size() == 0){
				wait();
			}
			TopicMessage topicMessage = queue.head();
			message = topicMessage.getMessage();
			topicMessage.count++;
			record.put(sign, topicMessage);
			System.out.println(sign+" "+topicMessage.getMessage());
//			if(topicMessage.count == size){
//				System.out.println("remove "+topicMessage.count+queue.remove());
//			}
		}
		return message;
	}
}
