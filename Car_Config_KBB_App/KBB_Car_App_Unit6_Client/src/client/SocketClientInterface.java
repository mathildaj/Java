/*
 * Program Description: SocketClientInterface interface
 */


package client;

public interface SocketClientInterface {

	boolean openConnection();
	void handleSession();
	void closeSession();
}
