package com.wedevol.xmpp.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.wedevol.xmpp.bean.CcsInMessage;
import com.wedevol.xmpp.bean.CcsOutMessage;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.server.MessageHelper;
import com.wedevol.xmpp.service.PayloadProcessor;
import com.wedevol.xmpp.util.Util;


// Enviaremos una notificacion a todos los dispositivos del tema "Liga"
public class LigaProcessor implements PayloadProcessor {
	CcsClient client ;
	String messageId ;
	Map<String, String> notificacionPayload , dataPayload;
	CcsOutMessage message ;
	String jsonRequest ;
	//Datos de la imagen de la liga
	String nombreUser,uidImagenLiga,tituloJornada,uidJornada;

	@Override
	public void handleMessage(CcsInMessage inMessage) {
		client = CcsClient.getInstance();
		//** Obtenemos la imagen de liga  para reenviarla a los users
		Map<String,String> datosUSer = inMessage.getDataPayload();
		nombreUser= datosUSer.get(Util.BACKEND_ATTRIBUTE_NOMBREUSER);		
		uidImagenLiga = datosUSer.get(Util.BACKEND_ATTRIBUTE_UIDIMAGENLIGA);
		tituloJornada = datosUSer.get(Util.BACKEND_ATTRIBUTE_TITULOJORNADA);
		uidJornada = datosUSer.get(Util.BACKEND_ATTRIBUTE_UIDJORNADA);
		notificacionPayload = new HashMap<>();
		// Creamos la notificacion a enviar
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_TITLE, nombreUser+" añadió una fotillo de liga");
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_BODY, "En la jornada "+tituloJornada);
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_ICON, "ic_action_liga");
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_COLOR, "#FF4081");//new long[] {100, 250, 100, 500}
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_SOUND, "notificacion_sonido");
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_CLICK_ACTION, Util.BACKEND_ACTION_LIGA);
		// Adjuntamos datos que nos interesan
		dataPayload = new HashMap<>();
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_UIDLIGA, uidImagenLiga);
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_NOMBREUSER, nombreUser);
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_UIDJORNADA, uidJornada);
		messageId = Util.getUniqueMessageId();
		message = new CcsOutMessage("/topics/Liga", messageId, dataPayload);
		message.setNotificationPayload(notificacionPayload);
		jsonRequest = MessageHelper.createJsonOutMessage(message);
		client.send(jsonRequest);
	}

}