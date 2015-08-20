package PublisherSubscribers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientSocket implements Runnable{
	BlockingQueue<Message> queue;
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	boolean close;
	
	public ClientSocket(Socket socket){
		queue = new LinkedBlockingQueue<Message>();
		this.socket = socket;
		this.close = false;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addMessage(Message message){
		queue.add(message);
	}
	
	@Override
	public void run() {
		// start close thread
//		Thread closeThread = new Thread(new Runnable(){
//			public void run(){
//				while(true){
//					String clientMsg;
//					try {
//						clientMsg = in.readLine();
//						System.out.println(clientMsg);
//						if(clientMsg.equals("Close")){
//							close = true;
//						}
//						break;
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//		closeThread.start();
		try {
			while(true){
				if(close == true){
					ClientSocketPool.getInstance().removeClient(socket);
					break;
				}
				Message message = queue.take();
				String messageXML = Message.convertObjectToXML(message);
				out.println(messageXML);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
