package TopicModel;
import java.io.IOException;


public class Server implements Runnable{
	private Topic topic;
	
	public Server(Topic topic){
		this.topic = topic;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException{
		Topic topic = Topic.getInstance();
		Listener listener = new Listener(8080, topic);
		Thread listenerThread = new Thread(listener);
		listenerThread.start();
		
		for(int i=0;i<10;i++){
			Client client = new Client();
			Thread clientThread = new Thread(client);
			clientThread.start();
		}
		
		
		Server server = new Server(topic);
		Thread serverThread = new Thread(server);
		serverThread.start();
	}
	
	public void run(){
		String[] lst = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		for(String s:lst){
			System.out.println("producer add "+s);
			topic.add(s);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
