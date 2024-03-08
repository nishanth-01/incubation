import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

import static echo.Config.ADDRESS;

public class Client {
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("usage: java Client [MSG] ...");
			System.exit(1);
		}

		Socket socket = null;

		try {
			socket = new Socket(ADDRESS.getAddress(), ADDRESS.getPort());
		} catch (IOException e) {
			System.err.println("Client: unable to create server");
			System.exit(1);
		}

		try {
			OutputStream outgoingStream = socket.getOutputStream();
			final String msg = String.join(" ", args);

			outgoingStream.write(msg.getBytes());
			outgoingStream.write((byte) '\n');

			socket.close();
		} catch (Exception e) {
			System.err.println("Client: something went wrong");
			System.exit(1);
		}
	}
}
