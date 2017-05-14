package com.wedevol.xmpp.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.wedevol.xmpp.bean.CcsInMessage;
import com.wedevol.xmpp.bean.CcsOutMessage;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.server.MessageHelper;
import com.wedevol.xmpp.service.PayloadProcessor;
import com.wedevol.xmpp.util.Util;


// Enviaremos una notificacion a TODOS los admnis del nuevo grupo (permiso) solicitado
public class CambioGrupoProcessor implements PayloadProcessor {
	CcsClient client ;
	String messageId ;
	Map<String, String> notificacionPayload , dataPayload;
	CcsOutMessage message ;
	String jsonRequest ;
	//Datos del grupo
	String grupoNuevo,grupoViejo,uidUser, nombre, avatar;

	@Override
	public void handleMessage(CcsInMessage inMessage) {
		client = CcsClient.getInstance();
		//** Obtenemos el bando del admin para reenviarlo a los users
		Map<String,String> datosAdmin = inMessage.getDataPayload();
		grupoNuevo = datosAdmin.get(Util.BACKEND_ATTRIBUTE_GRUPONUEVO);
		grupoViejo = datosAdmin.get(Util.BACKEND_ATTRIBUTE_GRUPOVIEJO);
		uidUser = datosAdmin.get(Util.BACKEND_ATTRIBUTE_UIDUSER);
		nombre = datosAdmin.get(Util.BACKEND_ATTRIBUTE_NOMBRE);
		avatar = datosAdmin.get(Util.BACKEND_ATTRIBUTE_AVATAR);
		notificacionPayload = new HashMap<>();
		// Creamos la notificacion a enviar (Si estamos fuera de la app)
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_TITLE, "Solicitud de Permisos de "+nombre);
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_BODY, "Permiso solicitado: "+grupoNuevo);
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_ICON, "ic_stat_touch_app");
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_COLOR, "#FF4081");
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_SOUND, System.getenv("notificacion_sonido"));
		notificacionPayload.put(Util.PAYLOAD_NOTIFICATION_CLICK_ACTION, Util.BACKEND_ACTION_CAMBIO_GRUPO);
		// Adjuntamos datos que nos interesan
		dataPayload = new HashMap<>();
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_UIDUSER, uidUser);
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_NOMBRE, nombre);
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_GRUPONUEVO, grupoNuevo);
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_GRUPOVIEJO, grupoViejo);
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_AVATAR, avatar);
		messageId = Util.getUniqueMessageId();
		message = new CcsOutMessage("/topics/administrador", messageId, dataPayload);  
		message.setNotificationPayload(notificacionPayload);
		jsonRequest = MessageHelper.createJsonOutMessage(message);
		client.send(jsonRequest);
	}

}