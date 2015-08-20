import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Listener implements Runnable{

	private int portnumber;
	ServerSocket listener;
	ClientSocketPool pool;
	
	
	public Listener(int portnumber, ClientSocketPool pool) throws IOException{
		this.portnumber = portnumber;
		this.pool = pool;
		listener = new ServerSocket(portnumber);
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Socket socket = listener.accept();
				ClientSocket clientSocket = pool.addClientSocket(socket);
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
