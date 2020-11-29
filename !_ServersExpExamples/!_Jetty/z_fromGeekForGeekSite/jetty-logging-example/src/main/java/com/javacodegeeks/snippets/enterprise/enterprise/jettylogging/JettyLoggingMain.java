package com.javacodegeeks.snippets.enterprise.enterprise.jettylogging;

import java.io.PrintStream;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.RolloverFileOutputStream;
import org.eclipse.jetty.util.log.Log;

public class JettyLoggingMain {

	public static void main(String[] args) throws Exception {

		//We are configuring a RolloverFileOutputStream with file name pattern  and appending property
		RolloverFileOutputStream os = new RolloverFileOutputStream("yyyy_mm_dd_jcglogging.log", true);
		
		//We are creating a print stream based on our RolloverFileOutputStream
		PrintStream logStream = new PrintStream(os);

		//We are redirecting system out and system error to our print stream.
		System.setOut(logStream);
		System.setErr(logStream);	

		//We are creating and starting out server on port 8080
		Server server = new Server(8080);
		server.start();
	
		//Now we are appending a line to our log 
		Log.getRootLogger().info("JCG Embedded Jetty logging started.", new Object[]{});

		server.join();

	}

}
