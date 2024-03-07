package echo;

import java.net.InetSocketAddress;

public class Config {
	public static final InetSocketAddress ADDRESS
		= new InetSocketAddress("localhost", 8080);

	public static final String CLOSE_MSG = "CLOSE";
}
