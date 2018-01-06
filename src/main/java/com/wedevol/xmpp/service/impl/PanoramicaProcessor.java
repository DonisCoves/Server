package com.wedevol.xmpp.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.wedevol.xmpp.bean.CcsInMessage;
import com.wedevol.xmpp.bean.CcsOutMessage;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.server.MessageHelper;
import com.wedevol.xmpp.service.PayloadProcessor;
import com.wedevol.xmpp.util.Util;


// Enviaremos una notificacion a todos los dispositivos del tema "Panoramica"
public class PanoramicaProcessor implements PayloadProcessor {
	CcsClient client ;
	String messageId ;
	Map<String, String> notificacionPayload , dataPayload;
	CcsOutMessage message ;
	String jsonRequest ;
	//Datos de la panoramica
	String direccion,uidPanoramica,nombreUser;

	@Override
	public void handleMessage(CcsInMessage inMessage) {
		client = CcsClient.getInstance();  
		//** Obtenemos el terrat del admin para reenviarlo a los users
		Map<String,String> datosAdmin = inMessage.getDataPayload();
		direccion = datosAdmin.get(Util.BACKEND_ATTRIBUTE_DIRECCION);
		nombreUser = datosAdmin.get(Util.BACKEND_ATTRIBUTE_NOMBREUSER);
		uidPanoramica = datosAdmin.get(Util.PAYLOAD_ATTRIBUTE_UIDPANORAMICA);
		notificacionPayload = new HashMap<>();
		// Creamos la notificacion a enviar
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_TITLE, nombreUser+" añadió panoramica en");
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_BODY, direccion);
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_ICON, "ic_action_panoramica");//
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_COLOR, "#FF4081");//new long[] {100, 250, 100, 500}
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_SOUND, "notificacion_sonido");  //System.getenv("notificacion_sonido")
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_CLICK_ACTION, Util.BACKEND_ACTION_PANORAMICA);
		// Adjuntamos datos que nos interesan
		dataPayload = new HashMap<>();
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_UIDPANORAMICA, uidPanoramica);
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_NOMBREUSER, nombreUser);
		messageId = Util.getUniqueMessageId();
		message = new CcsOutMessage("/topics/Panoramica", messageId, dataPayload);
		message.setNotificationPayload(notificacionPayload);
		jsonRequest = MessageHelper.createJsonOutMessage(message);
		client.send(jsonRequest);
	}

}