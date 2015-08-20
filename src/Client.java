import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client implements Runnable{
	
	private Topic topic;
	private int sign;
	
	public Client(Topic topic, int sign){
		this.topic = topic;
		this.sign = sign;
	}
//	public static void main(String[] args) throws UnknownHostException, IOException{
//		Socket s = new Socket("localhost", 8080);
//		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
//		System.out.println(input.readLine());
//	}
	
	public void run(){
		while(true){
			String s;
			try {
				s = topic.getNextMessage(sign);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
