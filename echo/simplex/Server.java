import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;

import static echo.Config.ADDRESS;

public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(ADDRESS);
		} catch (Exception e) {
			System.out.println("Server: unable to create/bind socket");
			System.exit(1);
		}

		for(;;) {
			try {
				Socket clientSocket = serverSocket.accept();

				InputStream incomingMessage = clientSocket.getInputStream();
				for(;;) {
					char c = (char) incomingMessage.read();

					if (c == '\n') break;
					System.out.print(c);
				}
				System.out.println();

				clientSocket.close();
			} catch (Exception e) {
				System.out.println("Server: something went wrong");
				System.exit(1);
			}
		}
	}
}
