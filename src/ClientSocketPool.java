import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


public class ClientSocketPool {
	private static ClientSocketPool clientSocketPool;
	private static ConcurrentHashMap<Socket, ClientSocket> pool;
	
	private ClientSocketPool(){
		pool = new ConcurrentHashMap<Socket, ClientSocket>();
	}
	
	public static ClientSocketPool getInstance(){
		if(clientSocketPool == null){
			clientSocketPool = new ClientSocketPool();
		}
		return clientSocketPool;
	}
	
	public int getSize(){
		return pool.size();
	}
	
	public ClientSocket addClientSocket(Socket socket){
		ClientSocket clientSocket = null;
		try {
			clientSocket = new ClientSocket(socket);
			pool.put(socket, clientSocket);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return clientSocket;
	}
	
	public void removeClientSocket(Socket socket){
		
	}
	
	public void addMessage(String message){
		
	}
	
	
}
