package TopicModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientSocket implements Runnable{
	Socket socket;
	PrintWriter out;
	Topic topic;
	
	public ClientSocket(Socket socket, Topic topic){
		this.topic = topic;
		this.socket = socket;
		topic.clientRegister();
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true){
			String message = topic.getNext(socket).getMessage();
			out.println(message);
		}
		
	}

}
