package main;

import java.io.File;
import org.apache.catalina.startup.Tomcat;

public class WebServerLauncher {
	public static void main(String[] args) throws Exception {
		String webappDirLocation = "webapp/";
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		System.out.println("configuring app with basedir: {}"+ new File("./"	+ webappDirLocation).getAbsolutePath());

		tomcat.start();
		tomcat.getServer().await();
	}
}
