package TopicModel;

public class TopicMessage {

	String message;
	int count;
	TopicMessage next;
	boolean remove;
	
	public TopicMessage(String message){
		this.message = message;
		this.count = 0;
		this.remove = false;
	}
	
	public void increCount(){
		count++;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setRemove(){
		remove = true;
	}
	
	public String getMessage(){
		return message;
	}
}
