package ir.amin.jpa.h2;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.Server;

public class H2Server  {
	

	private H2Container h2Container = new H2Container();

	public void createDBByNameAndDefaultUser(String dbname) {
		try {
			startTCPServer(h2Container);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			setConnection(dbname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			startWebServer(h2Container);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void startWebServer(final H2Container h2Container) throws SQLException {

		Server webServer = Server.createWebServer("-webAllowOthers", "-browser");// open browser
		webServer.startWebServer(h2Container.getConnection());
		h2Container.setWebServer(webServer);

	}

	public void startTCPServer(H2Container h2Container ) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Server server = Server.createTcpServer("-tcp", "-ifNotExists", "-tcpAllowOthers").start();
		h2Container.setTcpServer(server);
	}

	public void setConnection(String dbname) throws SQLException {
		String url = h2Container.getTcpServer().getURL();
		h2Container.setUrl("jdbc:h2:" + url + "/mem:" + dbname + ";DB_CLOSE_DELAY=-1");
		h2Container.setConnection(DriverManager
				.getConnection(h2Container.getUrl(), "sa", ""));
	}

	public H2Container getH2Container() {
		return h2Container;
	}

	public void shutdown() {

		if (h2Container.getWebServer() != null) {
			h2Container.getWebServer().shutdown();
		}

		if (h2Container.getTcpServer() != null) {
			h2Container.getTcpServer().shutdown();
		}

	}

	
	public void runDBByCMD() throws IOException {
		URL resource = getClass().getClassLoader().getResource("RunDB.bat");
		  if (resource == null) {
		      throw new IllegalArgumentException("file not found!");
		  } else {
			  Runtime.getRuntime().exec(
					  new String[] { "cmd.exe", "/c", resource.getFile() });
			  
		  }
	}


	/*
	 * public void test() throws SQLException { if (config.networked ||
	 * config.memory) { return; } DbStarter listener = new DbStarter();
	 * TestServletContext context = new TestServletContext(); String url =
	 * getURL("servlet", true); context.setInitParameter("db.url", url);
	 * context.setInitParameter("db.user", getUser());
	 * context.setInitParameter("db.password", getPassword());
	 * context.setInitParameter("db.tcpServer", "-tcpPort 8888");
	 * ServletContextEvent event = new ServletContextEvent(context);
	 * listener.contextInitialized(event); Connection conn1 =
	 * listener.getConnection(); Connection conn1a = (Connection)
	 * context.getAttribute("connection"); assertTrue(conn1 == conn1a);
	 * Statement stat1 = conn1.createStatement(); stat1.execute(
	 * "CREATE TABLE T(ID INT)"); String u2 =
	 * url.substring(url.indexOf("servlet")); u2 =
	 * "jdbc:h2:tcp://localhost:8888/" + getBaseDir() + "/" + u2; Connection
	 * conn2 = DriverManager.getConnection(u2, getUser(), getPassword());
	 * Statement stat2 = conn2.createStatement(); stat2.execute(
	 * "SELECT * FROM T"); stat2.execute("DROP TABLE T"); //
	 * assertThrows(ErrorCode.TABLE_OR_VIEW_NOT_FOUND_1, stat1).execute(
	 * "SELECT * FROM T"); // conn2.close(); //
	 * listener.contextDestroyed(event); // // listener must be stopped //
	 * assertThrows(ErrorCode.CONNECTION_BROKEN_1,
	 * this).getConnection("jdbc:h2:tcp://localhost:8888/" + getBaseDir() +
	 * "/servlet", getUser(), getPassword()); // // connection must be closed //
	 * assertThrows(ErrorCode.OBJECT_CLOSED, stat1).execute("SELECT * FROM DUAL"
	 * ); deleteDb("servlet"); }
	 */
}
