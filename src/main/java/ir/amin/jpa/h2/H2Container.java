package ir.amin.jpa.h2;

import java.sql.Connection;

import org.h2.tools.Server;

public class H2Container {

	private Server tcpServer;
	private Server webServer;
	private Connection connection;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Server getTcpServer() {
		return tcpServer;
	}

	public void setTcpServer(Server tcpServer) {
		this.tcpServer = tcpServer;
	}

	public Server getWebServer() {
		return webServer;
	}

	public void setWebServer(Server webServer) {
		this.webServer = webServer;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}