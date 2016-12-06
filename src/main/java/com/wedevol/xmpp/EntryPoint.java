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
		//token emulador normal d5ovB6uKS1Y:APA91bGZpV3n4EcvwdiWrkJwj5-GJma94jNmZD2BQIODXWnJ3GpWpKFjXMfWPAQjYonIyf6qlse7Y86ulclPpY2F2FtTrpMd3NVUY7v_rD2XVXSoaqA7Mj8zS5EU_CqbFzpYaBa1QKYV 

		CcsClient ccsClient = CcsClient.prepareClient(fcmProjectSenderId, fcmServerKey, true);

		try {
			ccsClient.connect();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		
		//** Obtenemos el bando del admin para reenviarlo a los users
//		Map<String,String>notificacionPayload = new HashMap<>();
//		// Creamos la notificacion a enviar
//		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_TITLE, "titulo");
//		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_BODY, "cuerpo");
//		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_COLOR, "#FF4081");
//		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_CLICK_ACTION, Util.BACKEND_ACTION_BANDO);
//		notificacionPayload.put(Util.BACKEND_ATTRIBUTE_UIDBANDO, "-KXZEhtfo4WI65475kZi");
//		String messageId = Util.getUniqueMessageId();
//		CcsOutMessage message = new CcsOutMessage("/topics/Bando", messageId, null);
//		message.setContentAvailable(true);
//		message.setNotificationPayload(notificacionPayload);
//		String jsonRequest = MessageHelper.createJsonOutMessage(message);
//		ccsClient.send(jsonRequest);
/*
		// Send a sample downstream message to a device
		String messageId = Util.getUniqueMessageId();
		Map<String, String> dataPayload = new HashMap<String, String>();
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_MESSAGE, "This is the simple sample message");
		//CcsOutMessage message = new CcsOutMessage(toRegId, messageId, dataPayload);
		CcsOutMessage message = new CcsOutMessage("/topics/mierda", messageId, dataPayload);
		
		String jsonRequest = MessageHelper.createJsonOutMessage(message);
		ccsClient.send(jsonRequest);*/
		
		
//		Map<String,String> notificacionPayload = new HashMap<>();
//		// Creamos la notificacion a enviar
//		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_TITLE, "mierdas");
//		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_BODY, "ddddd");
//		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_ICON, "ic_face_black_24dp");
//		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_COLOR, "#FF0082");//new long[] {100, 250, 100, 500}
//		//notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_SOUND, "#FF4081");
//		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_CLICK_ACTION, Util.BACKEND_ACTION_BANDO);
//		// Adjuntamos datos que nos interesan
//		Map<String,String> dataPayload = new HashMap<>();
//		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_UIDBANDO, "-KY6GKX3gKIAD6mqSC_P");
//		String messageId = Util.getUniqueMessageId();
//		CcsOutMessage message = new CcsOutMessage("/topics/Bando", messageId, dataPayload);
//		message.setNotificationPayload(notificacionPayload);
//		String jsonRequest = MessageHelper.createJsonOutMessage(message);
//		ccsClient.send(jsonRequest);

		while (true) {
			// TODO: Improve this because the app closes itself after the
			// execution of the connect method
		}
	}
}
