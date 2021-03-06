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
public class NotificacionProcessor implements PayloadProcessor {
	CcsClient client ;
	String messageId ;
	Map<String, String> notificacionPayload , dataPayload;
	CcsOutMessage message ;
	String jsonRequest ;
	//Datos de la notificacion
	String titulo,uidImagen, fecha, cabecera;

	@Override
	public void handleMessage(CcsInMessage inMessage) {
		client = CcsClient.getInstance();
		//** Obtenemos el bando del admin para reenviarlo a los users
		Map<String,String> datosAdmin = inMessage.getDataPayload();
		titulo = datosAdmin.get(Util.BACKEND_ATTRIBUTE_TITULO);
		cabecera = datosAdmin.get(Util.BACKEND_ATTRIBUTE_CABECERA);
		fecha = datosAdmin.get(Util.BACKEND_ATTRIBUTE_FECHA);
		uidImagen = datosAdmin.get(Util.PAYLOAD_ATTRIBUTE_UIDNOTIFICACION);
		notificacionPayload = new HashMap<>();
		// Creamos la notificacion a enviar
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_TITLE, cabecera);
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_BODY, titulo + "  " + fecha);
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_ICON, "ic_stat_touch_app");
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_COLOR, "#FF4081");
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_SOUND, System.getenv("notificacion_sonido"));
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_CLICK_ACTION, Util.BACKEND_ACTION_NOTIFICACION);
		// Adjuntamos datos que nos interesan
		dataPayload = new HashMap<>();
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_UIDNOTIFICACION, uidImagen);
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_TITULO, titulo);
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_FECHA, fecha);
		messageId = Util.getUniqueMessageId();
		message = new CcsOutMessage("/topics/Notificacion", messageId, dataPayload);
		message.setNotificationPayload(notificacionPayload);
		jsonRequest = MessageHelper.createJsonOutMessage(message);
		client.send(jsonRequest);
	}

}