package com.wedevol.xmpp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import com.wedevol.xmpp.bean.CcsOutMessage;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.server.MessageHelper;
import com.wedevol.xmpp.util.Util;

import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * Entry Point class for the XMPP Server in dev mode for debugging and testing
 * purposes
 */
public class EntryPoint {
	public static void main(String[] args) throws SmackException, IOException {
		final String fcmProjectSenderId = "351834780446";//projectId o SENDER_ID
		final String fcmServerKey = "AAAAUer8sx4:APA91bGGvMhgdZ2pG7ErlnTp1JEFJ5rqDJ3egtK45xknfu5KEdUItKePQPiGQzhpGCxULgN025jydVuoo6X9IO91YWzILvlMy-J_t0x0X42I4uU6h5N7_5M4R2phAi6mZNZLmg5P7ZWIZ8ptW_eL5rUpsAysLuTAUw"; //apiKey
		final String toRegId = "dHVWzOv7mt4:APA91bEz96sGTO93he75PXiS19lPszsVTWpXo4eAWHwlA_uKV0rolcDwXk3KWdUq5rm9UyDeQ8khqqc-3vgnfJ9MqFND1cBUS_Jg0zOfKgOBtv4oBPrCFQWL3oHM7BHJHekBhaA1Gob4";
		final String Cliente1 = "eHh1_E0-2ko:APA91bGKCqb33i77y-Yl9ol0-6ISuvSnkW6VulQuG7fOLaLN7i9szUtR5e5w8Hp_beets1w1vBI9qP7S3_re1-Gma7OKksLs8KqpiS5C0BNmoSHMruAi5iTuoMuSwe_T2VgKpfnXDxjk"; 
		final String Cliente2 = "feV7KhODqTs:APA91bEe97htXeNNNg_A0JvxEfEW9qmGPvxNYm0iTo5C9pPs9n5vOkGaUurp8ezHR19SG1aTtpKQS6uXcK7Mfh57hipjP5GHgSOMpR_jxn7p29jhcG_TazwjWBDsEN5okGAlsu43Qfmf";
		final String Nexus5 = "fw3kfeKphUo:APA91bE1QFlKnxZXJqM5OgbFNP8Jay8Ztz19NIGPtHdW_1yMvBoCa-Z__7brrGvN2DpJxlaTi97i7yrsB2qx61RX6INMtKUOsuHg4tP4nqh9KfEc85d7MQuqB5dm2wl9-KaXihCPtFTE";	
		final String Servidor = "dHVWzOv7mt4:APA91bEz96sGTO93he75PXiS19lPszsVTWpXo4eAWHwlA_uKV0rolcDwXk3KWdUq5rm9UyDeQ8khqqc-3vgnfJ9MqFND1cBUS_Jg0zOfKgOBtv4oBPrCFQWL3oHM7BHJHekBhaA1Gob4";

		CcsClient ccsClient = CcsClient.prepareClient(fcmProjectSenderId, fcmServerKey, true);

		try {
			ccsClient.connect();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		
		port(Integer.valueOf(System.getenv("PORT")));
	    staticFileLocation(System.getenv("PORT"));
	    System.out.println("el puerto es ************* "+System.getenv("PORT"));
	    get("/hello", (req, res) -> "Hello World");
	    get("/", (request, response) -> {
	            Map<String, Object> attributes = new HashMap<>();
	            attributes.put("message", "Hello World!");

	            return new ModelAndView(attributes, "index.ftl");
	        }, new FreeMarkerEngine());
		
	

		while (true) {
			// TODO: Improve this because the app closes itself after the
			// execution of the connect method
		}
	}
}
