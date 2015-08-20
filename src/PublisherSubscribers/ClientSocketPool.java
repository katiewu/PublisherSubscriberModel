package PublisherSubscribers;

import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ClientSocketPool {
	private static ClientSocketPool clientPool;
	private ConcurrentHashMap<Socket, ClientSocket> map;
	
	private ClientSocketPool(){
		map = new ConcurrentHashMap<Socket, ClientSocket>();
	}
	
	public static ClientSocketPool getInstance(){
		if(clientPool == null){
			clientPool = new ClientSocketPool();
		}
		return clientPool;
	}
	
	public Collection<ClientSocket> getCurrentClients(){
		Collection<ClientSocket> clients = null;
		clients = map.values();
		return clients;
	}
	
	public ClientSocket addClient(Socket socket){
		ClientSocket client = new ClientSocket(socket);
		map.put(socket, client);
		return client;
	}
	
	public void removeClient(Socket socket){
		if(map.containsKey(socket)){
			map.remove(socket);
		}
	}
	
}
