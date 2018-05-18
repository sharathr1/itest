package com.ip.itest.itest;


import com.siebel.data.SiebelDataBean;
import com.siebel.data.SiebelException;
import com.siebel.data.SiebelPropertySet;
import com.siebel.data.SiebelService;

public class JDBQueryFEParts {
    public static void main(String[] args) throws SiebelException {
	SiebelDataBean dataBean = new SiebelDataBean();
	SiebelDataBean dataBean2 = new SiebelDataBean();
	SiebelDataBean dataBean3 = new SiebelDataBean();

	// siebel.conmgr.virtualhosts =
	// VirtualServer=prd1:int-sbl-prd-03.am.health.ge.com:2321,prd2:int-sbl-prd-05.am.health.ge.com:2321,prd3:int-sbl-prd-06.am.health.ge.com:2321;

	try {
	    /*********** input Variable Declaration **********************/
	    // stg1:int-sbl-stg-03.am.health.ge.com
//	    String url="siebel.TCPIP.None.None://int-sbl-tst-04.am.health.ge.com:2321/inttst/EAIObjMgrAPI_enu";
//	    String url="siebel.TCPIP.None.None://int-sbl-prd-03.am.health.ge.com:2321/intprd/EAIObjMgrAPI_enu";
//	    String url = "siebel.TCPIP.None.None://int-sbl-trn-03.am.health.ge.com:2321/inttrn/EAIObjMgrAPI_enu";
	    // String
	    // url="siebel.TCPIP.None.None://int-sbl-stg-03.am.health.ge.com:2321/intstg/EAIObjMgrAPI_enu";
//	     String url = "siebel.TCPIP.None.None://int-sbl-crp-01.am.health.ge.com:2321/intcrp/EAIObjMgrAPI_enu";
//	    String url ="siebel.TCPIP.None.None://int-sbl-trn-01.am.health.ge.com:2321/inttrn/EAIObjMgrAPI_enu";
//	     String url = "siebel.TCPIP.None.None://int-stm-tst-01:2321/inttst/EAIObjMgrAPI_enu";
//	    String url = "siebel.TCPIP.None.None://int-sbl-tst-04.am.health.ge.com:2321/inttst/EAIObjMgrAPI_enu";
	    String url = "siebel.tcpip.none.none://sam8-gtw-cap-01.am.health.ge.com:2324/siebel/EAIObjMgr_enu";
	    // Stringsiebel.TCPIP.None.None://int-stm-tst-01.am.health.ge.com:2321/inttst/EAIObjMgrAPI_enu
	    // url="siebel.TCPIP.None.None://int-sbl-stg-03.am.health.ge.com:2321/inttst/EAIObjMgrAPI_enu";
	    // 212369687
	    String sso = "502593533";

	    String tokenid = "HELLO";
	    String lang = "enu";
	    String PageSize = "20";
	    String StartRowNumber = "0";
	    String SiebelIO = "GEHC Mob Price List IO";
	    int totalRec = 0;
	    int totalRecNotes = 0;
	    String ProductName = "M1052831"; // Input as Seacrh creteria
	    String PriceListId = "1-IMEHHR"; // Input as Seacrh creteria

	    // String SearchCreteria = "[Price List Item.Product Name] = '" +
	    // ProductName + "' and [Price List Item.Price List Id] = '" +
	    // PriceListId + "'";
	    String SearchCreteria = "[Price List Item.Product Name] = '" + ProductName + "'";

//	    dataBean.login(url, sso, tokenid, lang);
//	    dataBean.login("siebel.tcpip.none.none://sam8-stm-cap-01.am.health.ge.com:2324/siebel/EAIObjMgr_enu", "502549115", "Temp44you", "enu");
//	    dataBean.login("siebel.tcpip.none.none://sam8-stm-cap-01.am.health.ge.com:2324/siebel/EAIObjMgr_enu", "502549115", "Temp44you", "enu");
//	    dataBean.login("siebel.tcpip.none.none://sam8-stm-cap-01.am.health.ge.com:2324/ip17poc/EAIObjMgr_enu", "305015647", "New18user", "enu");
	    dataBean.login("siebel.tcpip.none.none://sam8-gtw-cap-01.am.health.ge.com:2324/ip17poc/EAIObjMgr_enu", "502549115", "Temp44you", "enu");  
	    System.out.println("Login");

//	    dataBean2.login(url, sso, tokenid, lang);	    dataBean2.login(url, sso, tokenid, lang);
//	    dataBean2.login(url, sso, tokenid, lang);
//	    dataBean2.login(url, sso, tokenid, lang);

//	    System.out.println("Login");

//	    dataBean3.login(url, sso, tokenid, lang);
	    
//	    dataBean.login("siebel.TCPIP.None.None://sam8-sbl-dev-02.am.health.ge.com:2321/sam8dev/EAIObjMgrAPI_enu", "212065839", "HELLO", "enu");
//	    System.out.println("Login");
	    /***************** Setting Input Parameters *********************/

	    SiebelService businessService = dataBean.getService("EAI Siebel Adapter");
//	    SiebelService businessService2 = dataBean2.getService("EAI Siebel Adapter");
//	    SiebelService businessService3 = dataBean3.getService("EAI Siebel Adapter");

	    SiebelPropertySet qpinput = new SiebelPropertySet();
	    SiebelPropertySet qpoutput = new SiebelPropertySet();
	    SiebelPropertySet qpoutput1 = new SiebelPropertySet();
	    SiebelPropertySet qpoutput2= new SiebelPropertySet();

	    SiebelPropertySet propSet = new SiebelPropertySet();
	    qpinput.setProperty("PageSize", PageSize);
	    qpinput.setProperty("StartRowNum", StartRowNumber);
	    qpinput.setProperty("OutputIntObjectName", SiebelIO);
	    qpinput.setProperty("ViewMode", "All");
	    qpinput.setProperty("SearchSpec", SearchCreteria);

	    /***************** Calling Server **************************/
	    System.out.println("qpinput "+qpinput);

	    businessService.invokeMethod("QueryPage", qpinput, qpoutput);
//	    businessService2.invokeMethod("QueryPage", qpinput, qpoutput1);
//	    businessService2.invokeMethod("QueryPage", qpinput, qpoutput2);
//	    businessService2.invokeMethod("QueryPage", qpinput, qpoutput1);
//
//	    businessService3.invokeMethod("QueryPage", qpinput, qpoutput2);

//	    System.out.println("qpoutput "+qpoutput);

	    propSet = qpoutput.getChild(0).getChild(0);

	    totalRec = propSet.getChildCount();
	    if (totalRec == 0)
		System.out.println("No Record found !!");

	    for (int i = 0; i < totalRec; i++) {
		/**************************
		 * Iterate Addresses
		 **************************/
//		System.out.println("Part Number " + i + "" + propSet.getChild(0));

		totalRecNotes = propSet.getChild(0).getChild(0).getChildCount();
		for (int a = 0; a < totalRecNotes; a++) {
		    System.out.println("Part Number Notes" + a + "" + propSet.getChild(0).getChild(0).getChild(a));
		}
	    } // end of for

	} // end of try

	/*************** Exception Handling ********************/
	catch (SiebelException e) {
	    System.out.println("Exception: " + e.getMessage());
	    e.printStackTrace();
	} finally {
	    dataBean.detach();
	} // end of finally
    }// end of main
}// end of class