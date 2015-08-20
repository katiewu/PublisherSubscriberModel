package TopicModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable{
	private int portnumber;
	ServerSocket listener;
	Topic topic;
	
	
	public Listener(int portnumber, Topic topic) throws IOException{
		this.portnumber = portnumber;
		this.topic = topic;
		listener = new ServerSocket(portnumber);
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Socket socket = listener.accept();
				System.out.println("accept "+socket);
				// client subscribe the topic
				ClientSocket clientSocket = new ClientSocket(socket, topic);
				if(clientSocket != null) {
					Thread clientThread = new Thread(clientSocket);
					clientThread.start();
				}
				// process the condition if clientSocket is not successfully created
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
