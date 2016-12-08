package com.wedevol.xmpp.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.wedevol.xmpp.bean.CcsInMessage;
import com.wedevol.xmpp.bean.CcsOutMessage;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.server.MessageHelper;
import com.wedevol.xmpp.service.PayloadProcessor;
import com.wedevol.xmpp.util.Util;


// Enviaremos una notificacion a todos los dispositivos del tema "bando"
public class BandoProcessor implements PayloadProcessor {
	CcsClient client ;
	String messageId ;
	Map<String, String> notificacionPayload , dataPayload;
	CcsOutMessage message ;
	String jsonRequest ;
	//Datos del bando
	String titulo,uidBando, fecha;

	@Override
	public void handleMessage(CcsInMessage inMessage) {
		client = CcsClient.getInstance();
		//** Obtenemos el bando del admin para reenviarlo a los users
		Map<String,String> datosAdmin = inMessage.getDataPayload();
		titulo = datosAdmin.get(Util.BACKEND_ATTRIBUTE_TITULO);		
		fecha = datosAdmin.get(Util.BACKEND_ATTRIBUTE_FECHA);
		uidBando = datosAdmin.get(Util.PAYLOAD_ATTRIBUTE_UIDBANDO);
		notificacionPayload = new HashMap<>();
		// Creamos la notificacion a enviar
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_TITLE, titulo);
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_BODY, fecha);
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_ICON, "ic_action_bando");
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_COLOR, "#FF4081");//new long[] {100, 250, 100, 500}
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_SOUND, System.getenv("notificacion_sonido"));
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_CLICK_ACTION, Util.BACKEND_ACTION_BANDO);
		// Adjuntamos datos que nos interesan
		dataPayload = new HashMap<>();
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_UIDBANDO, uidBando);
		dataPayload.put(Util.PAYLOAD_NOTIFICATION_SOUND, System.getenv("notificacion_sonido"));
		messageId = Util.getUniqueMessageId();
		message = new CcsOutMessage("/topics/Bando", messageId, dataPayload);
		message.setNotificationPayload(notificacionPayload);
		jsonRequest = MessageHelper.createJsonOutMessage(message);
		System.out.println("LA variable de entorno vale==>"+System.getenv("notificacion_sonido"));
		client.send(jsonRequest);
	}

}