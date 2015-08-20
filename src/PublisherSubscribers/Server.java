package PublisherSubscribers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class Server implements Runnable{

	ClientSocketPool clientSocketPool;
	
	public Server(ClientSocketPool clientSocketPool) {
		this.clientSocketPool = clientSocketPool;
	}
	
	public static void main(String[] args) throws IOException{
		ClientSocketPool clientSocketPool = ClientSocketPool.getInstance();
		Listener listener = new Listener(8080, clientSocketPool);
		Thread listenerThread = new Thread(listener);
		listenerThread.start();
		
//		for(int i=0;i<10;i++){
//			Client client = new Client();
//			Thread clientThread = new Thread(client);
//			clientThread.start();
//		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Server server = new Server(clientSocketPool);
		Thread serverThread = new Thread(server);
		serverThread.start();
	}
	
	@Override
	public void run() {
		Message message = new Message();
		message.setAttribute("attr");
		message.setPrice("12");
		message.setProduct("prod");
		message.setPublisher("publisher");
		message.setSource("src");
		message.setTimestamp("");
		message.setVolume("10");
		Random randomGenerator = new Random();
		Collection<ClientSocket> clients = clientSocketPool.getCurrentClients();	
		while(true){
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			message.setPrice(Integer.toString(randomGenerator.nextInt(100)));
			message.setTimestamp(sdf.format(cal.getTime()));
			message.setVolume(Integer.toString(randomGenerator.nextInt(10)));
			for(Iterator iterator = clients.iterator();iterator.hasNext();){
				ClientSocket client = (ClientSocket)iterator.next();
				System.out.println(client.socket);
				client.addMessage(message);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	

}
