package com.wedevol.xmpp.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.wedevol.xmpp.bean.CcsInMessage;
import com.wedevol.xmpp.bean.CcsOutMessage;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.server.MessageHelper;
import com.wedevol.xmpp.service.PayloadProcessor;
import com.wedevol.xmpp.util.Util;


// Enviaremos una notificacion a todos los dispositivos del tema "Terrat"
public class ImagenesProcessor implements PayloadProcessor {
	CcsClient client ;
	String messageId ;
	Map<String, String> notificacionPayload , dataPayload;
	CcsOutMessage message ;
	String jsonRequest ;
	//Datos del terrat
	String direccion,uidTerrat;

	@Override
	public void handleMessage(CcsInMessage inMessage) {
		client = CcsClient.getInstance();
		//** Obtenemos el terrat del admin para reenviarlo a los users
		Map<String,String> datosAdmin = inMessage.getDataPayload();
		direccion = datosAdmin.get(Util.BACKEND_ATTRIBUTE_DIRECCION);		
		uidTerrat = datosAdmin.get(Util.PAYLOAD_ATTRIBUTE_UIDTERRAT);
		notificacionPayload = new HashMap<>();
		// Creamos la notificacion a enviar
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_TITLE, direccion);
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_ICON, "ic_action_terrat");
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_COLOR, "#FF4081");//new long[] {100, 250, 100, 500}
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_SOUND, System.getenv("notificacion_sonido"));
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_CLICK_ACTION, Util.BACKEND_ACTION_TERRAT);
		// Adjuntamos datos que nos interesan
		dataPayload = new HashMap<>();
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_UIDTERRAT, uidTerrat);
		messageId = Util.getUniqueMessageId();
		message = new CcsOutMessage("/topics/Terrat", messageId, dataPayload);
		message.setNotificationPayload(notificacionPayload);
		jsonRequest = MessageHelper.createJsonOutMessage(message);
		client.send(jsonRequest);
	}

}