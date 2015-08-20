import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;


public class ClientSocket implements Runnable{
	Socket socket;
	PrintWriter out;
	LinkedBlockingQueue<String> queue;
	
	public ClientSocket(Socket socket) throws IOException{
		this.socket = socket;
		out = new PrintWriter(socket.getOutputStream(), true);
		queue = new LinkedBlockingQueue<String>();
	}

	@Override
	public void run() {
		while(true){
			try {
				String message = queue.take();
				if(out.checkError() == false){
					// client ends the connection
					
				}
				out.write(message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
