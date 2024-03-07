import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.net.Socket;

import static echo.Config.ADDRESS;
import static echo.Config.CLOSE_MSG;

public class Client {
	private static final String CLOSE = "close";

	public static void main(String[] args) {
		try {
			Socket socket = new Socket(
					ADDRESS.getAddress(), ADDRESS.getPort());

			Scanner outgoingMsgScanner = new Scanner(System.in);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			OutputStream writer = socket.getOutputStream();

			System.out.println("Enter " + CLOSE_MSG + " to exit");
			System.out.println("Enter blank message to print recived messages");
			System.out.println();

			String outgoingMsg;
			do {
				System.out.print("Enter a Message: ");
				outgoingMsg = outgoingMsgScanner.nextLine();
				if(!outgoingMsg.isEmpty()) {
					writer.write(outgoingMsg.getBytes());
					writer.write((byte)'\n');
				}

				while(reader.ready())
					System.out.println("Recived: " + reader.readLine());
			} while(!outgoingMsg.equals(CLOSE_MSG));

			socket.close();
		} catch (Exception e) {
			System.out.println("Client: something went wrong");
			System.exit(1);
		}
	}
}
