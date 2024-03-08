import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.IOException;

import static echo.Config.ADDRESS;

public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket();
		} catch (IOException e) {
			System.err.println("Server: unable to create socket");
			System.exit(1);
		}

		try {
			serverSocket.bind(ADDRESS);
		} catch (IOException e) {
			System.err.println("Server: unable to bind socket to " + ADDRESS);
			System.exit(1);
		}

		System.out.println("Press CTRL+C to stop");
		System.out.println();

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
