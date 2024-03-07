import java.io.OutputStream;
import java.net.Socket;

import static echo.Config.ADDRESS;

public class Client {
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("usage: java Client [MSG] ...");
			System.exit(1);
		}

		try {
			Socket socket = new Socket(
					ADDRESS.getAddress(), ADDRESS.getPort());

			OutputStream outputStream = socket.getOutputStream();

			outputStream.write(String.join(" ", args).getBytes());

			outputStream.write((byte) '\n');

			socket.close();
		} catch (Exception e) {
			System.out.println("Client: something went wrong");
			System.exit(1);
		}
	}
}
