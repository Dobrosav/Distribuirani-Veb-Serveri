package rs.ac.bg.etf.kdp.aa080252d.client;

import rs.ac.bg.etf.kdp.aa080252d.common.StreamOperations;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class Client {
	
	public Client() {
	}
	
	public void sendGetRequest(String host, int port, String filename) {		
		StreamOperations.downloadFile(host, port, 0, "/" + filename);
	}
	
	public void sendPutRequest(String host, int port, String filename) {
		StreamOperations.sendFile("localhost", 8080, "/" + filename);
	}
	
	public void sendDeleteRequest(String host, int port, String filename) {
		StreamOperations.deleteFile(host, port, "/" + filename);
	}
}
