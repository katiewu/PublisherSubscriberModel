
public class TopicMessage {
	private String message;
	int count;
	TopicMessage nextMessage;
	
	public TopicMessage(String message){
		this.message = message;
		count = 0;
	}
	
	public String getMessage(){
		return message;
	}
}
