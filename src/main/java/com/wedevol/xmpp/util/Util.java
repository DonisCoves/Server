package com.wedevol.xmpp.util;

import java.util.UUID;

/**
 * Util class for constants and generic methods
 */

public class Util {

	// For the GCM connection
	public static final String FCM_SERVER = "fcm-xmpp.googleapis.com";
	public static final int FCM_PORT = 5236;
	public static final String FCM_ELEMENT_NAME = "gcm";
	public static final String FCM_NAMESPACE = "google:mobile:data";
	public static final String FCM_SERVER_CONNECTION = "gcm.googleapis.com";

	// For the processor factory
	public static final String PACKAGE = "com.wedevol";
	public static final String BACKEND_ACTION_REGISTER = PACKAGE + ".REGISTER";
	public static final String BACKEND_ACTION_ECHO = PACKAGE + ".ECHO";
	public static final String BACKEND_ACTION_MESSAGE = PACKAGE + ".MESSAGE";
    public static final String BACKEND_ACTION_BANDO = "BANDO";
    public static final String BACKEND_ATTRIBUTE_MESSAGE = "message";
    public static final String BACKEND_ATTRIBUTE_TITULO = "titulo";
	public static final String BACKEND_ATTRIBUTE_FECHA = "fecha";
	


	// For the app common payload message attributes (android - xmpp server)
	public static final Object PAYLOAD_ATTRIBUTE_RECIPIENT = null;
	public static final Object PAYLOAD_ATTRIBUTE_ACTION = "action";
	public static final String PAYLOAD_ATTRIBUTE_UIDBANDO = "uidBando";
	
	// (xmpp server --> FCM)
	public static final String PAYLOAD_NOTIFICATION_TITLE = "title";
	public static final String PAYLOAD_NOTIFICATION_BODY = "body";
	public static final String PAYLOAD_NOTIFICATION_ICON = "icon";
	public static final String PAYLOAD_NOTIFICATION_SOUND = "sound";
	public static final String PAYLOAD_NOTIFICATION_COLOR = "color";
	public static final String PAYLOAD_NOTIFICATION_CLICK_ACTION = "click_action";


	/**
	 * Returns a random message id to uniquely identify a message
	 */
	public static String getUniqueMessageId() {
		// TODO: replace for your own random message ID that the DB generates
		return "m-" + UUID.randomUUID().toString();
	}

}
