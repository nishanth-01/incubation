import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import static echo.Config.ADDRESS;
import static echo.Config.CLOSE_MSG;

public class Server {
	public static void main(String[] args) {
		ServerSocket socket = null;

		try {
			socket = new ServerSocket();
			socket.bind(ADDRESS);
		} catch (Exception e) {
			System.out.println("Server: unable to create/bind socket");
			System.exit(1);
		}

		Scanner inputScanner = new Scanner(System.in);
		Socket connection = null;

		for(;;) {
			try {
				connection = socket.accept();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				OutputStream writer = connection.getOutputStream();

				System.out.println("Press CTRL+C to stop");
				System.out.println("Enter blank message to print recived messages");
				System.out.println();

				String incomingMsg;
main:
				for(;;){
					while(reader.ready()) {
						String msg = reader.readLine();
						if(msg.equals(CLOSE_MSG))
							break main;
						System.out.println("Recived: " + msg);
					}

					System.out.print("Enter a Message: ");
					String outgoingMsg = inputScanner.nextLine();

					if(outgoingMsg.isEmpty()) continue;

					writer.write(outgoingMsg.getBytes());
					writer.write((byte)'\n');
				}

				connection.close();
			} catch (Exception e) {
				System.out.println("Server: something went wrong");
				System.exit(1);
			}
		}
	}
}
