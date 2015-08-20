package TopicModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket("localhost", 8080);
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String message = "";
			while((message = input.readLine())!=null){
				System.out.println(s.getLocalPort()+" receive "+message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
