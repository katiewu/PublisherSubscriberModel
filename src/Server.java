import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Server implements Runnable{
	private Topic topic;
	
	public Server(Topic topic){
		this.topic = topic;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException{
		Topic topic = new Topic(3);
		Server server = new Server(topic);
		Thread producer = new Thread(server);		
		for(int i=0;i<3;i++){
			Client client = new Client(topic, i);
			Thread thread = new Thread(client);
			thread.start();	
		}
		producer.start();
	}
	
	public void run(){
		String[] lst = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		for(String s:lst){
			try {
				System.out.println("producer add "+s);
				topic.add(s);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
