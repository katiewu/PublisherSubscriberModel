package TopicModel;

import java.net.Socket;
import java.util.HashMap;

public class Topic {

	private static Topic topic;
	
	private TopicQueue queue;
	private HashMap<Socket, TopicMessage> record;
	private int size;
	
	public Topic() {
		queue = new TopicQueue();
		record = new HashMap<Socket, TopicMessage>();
		size = 0;
	}
	
	public synchronized void clientRegister(){
		size++;
	}
	
	public static Topic getInstance(){
		if(topic == null){
			topic = new Topic();
		}
		return topic;
	}
	
	public synchronized void add(String message){
		TopicMessage topicMessage = new TopicMessage(message);
		notifyAll();
		queue.add(topicMessage);
	}
	
	public synchronized TopicMessage getNext(Socket socket){
		TopicMessage topicMessage = null;
		if(record.containsKey(socket)){
			TopicMessage current = record.get(socket);
			while(queue.getNext(current) == null){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			topicMessage = queue.getNext(current);
			record.put(socket, topicMessage);
			topicMessage.increCount();
			if(current.getCount() == size){
				queue.remove(current);
			}
		}
		else{
			while(queue.head() == null){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			topicMessage = queue.head();
			record.put(socket, topicMessage);
			topicMessage.increCount();
		}
		return topicMessage;
	}
}
