package TopicModel;


public class TopicQueue {
	
	private TopicMessage head;
	private TopicMessage tail;
	
	public void add(TopicMessage topicMessage){
		if(head == null){
			head = topicMessage;
			tail = topicMessage;
		}
		else{
			tail.next = topicMessage;
			tail = tail.next;
		}
	}
	
	public void print(){
		TopicMessage node = head;
		while(node!=null){
			System.out.print(node.getMessage()+" ");
			node = node.next;
		}
		System.out.println();
	}
	
	public void remove(TopicMessage topicMessage){
		if(topicMessage == head){
			if(head == tail){
				head = null;
				tail = null;
			}
			else{
				head = head.next;
			}
			topicMessage.setRemove();
		}
	}
	
	public TopicMessage getNext(TopicMessage topicMessage){
		return topicMessage.next;
	}
	
	public TopicMessage head(){
		return head;
	}
	
}
