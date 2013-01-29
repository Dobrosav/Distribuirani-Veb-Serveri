package rs.ac.bg.etf.kdp.aa080252d.server;

import java.net.Socket;
import rs.ac.bg.etf.kdp.aa080252d.centralserver.CentralServer;
import rs.ac.bg.etf.kdp.aa080252d.common.AbstractServerThread;
import rs.ac.bg.etf.kdp.aa080252d.common.StreamOperations;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class ServerThread extends AbstractServerThread {
	
	private static String centralServerAdress = Server.getCentralServerAddress();
	private static int centralServerPort = CentralServer.PORT;

	public ServerThread(Socket socket, int myPort) {
		super(socket, myPort);
	}

	@Override
	protected void fileDoesNotExist(String filename) {
		boolean fileDownloaded;
		fileDownloaded = StreamOperations.downloadFile(centralServerAdress, centralServerPort, myPort, filename);

		if (fileDownloaded) {
			StreamOperations.uploadFile(filename, output);
		} else {
			StreamOperations.send404(filename, output);
		}
	}
	
	/**
	 *
	 * @param filename
	 * @return
	 */
	@Override
	protected boolean httpGet(String filename) {
		boolean result;

		// Call super method
		result = super.httpGet(filename); 
		
		// Keep track of downloaded files
		Server.myServerInfo.addFile(filename);
		
		return result;
	}
}
