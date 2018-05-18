//package com.ip.itest.itest;
//
//import com.siebel.data.SiebelDataBean;
//import com.siebel.data.SiebelException;
//import com.siebel.data.SiebelPropertySet;
//import com.siebel.data.SiebelService;
//import javax.naming.*;
//
//import java.io.*;
//import java.util.ResourceBundle;
//
//import javax.servlet.*;
//
//import javax.servlet.http.*;
//
//import com.siebel.integration.jca.cci.SiebelConnectionFactory;
//
//
//import java.io.*;
//
//import javax.servlet.*;
//
//import javax.servlet.http.*;
//
//import com.siebel.integration.jca.cci.notx.SiebelNoTxConnectionFactory;
//
//
//public class BookshelfNonManagedConnectionSample extends HttpServlet {
//
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//		PrintWriter reply = response.getWriter();
//
//		try {
//
//			EAI_File_TransportBusServAdapter bs = new EAI_File_TransportBusServAdapter("siebel.properties");
//
//			bs.setConnectionFactory(new SiebelNoTxConnectionFactory());
//
//			// Username, password, connect string, and language are read from
//			// siebel.properties, which must be in the classpath of the servlet
//			// and be specified in the constructor.
//
//			// Alternatively, they can be set here programmatically:
//
//			// bs.setUserName("USER");
//
//			// bs.setPassword("PWD");
//
//			// bs.setConnectString("siebel://examplecomputer:2321/siebel/SCCObjMgr_enu");
//
//			ReceiveInput input = new ReceiveInput();
//
//			input.setfCharSetConversion("UTF-8");
//
//			input.setfFileName("D:\\helloWorld.txt");
//
//			ReceiveOutput output = bs.mReceive(input);
//
//			reply.println(output.getf_Value_());
//
//		}
//
//		catch (Exception e) {
//
//			reply.println("Exception:" + e.getMessage());
//
//		}
//
//	}
//
//}