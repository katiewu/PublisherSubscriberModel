
public class TopicQueue {
	private TopicMessage head;
	private TopicMessage tail;
	private int size;
	
	public TopicQueue() {
		size = 0;
	}
	
	public void add(TopicMessage topicMessage){
		if(head == null){
			head = topicMessage;
			tail = topicMessage;
		}
		else{
			tail.nextMessage = topicMessage;
			tail = tail.nextMessage;
		}
		size++;
	}
	
	public String remove(){
		String buffer = head.getMessage();
		if(size == 1){
			head = null;
			tail = null;
		}
		else{
			head = head.nextMessage;
		}
		size--;
		return buffer;
	}
	
	public TopicMessage head(){
		return head;
	}
	
	public int size(){
		return size;
	}
}
