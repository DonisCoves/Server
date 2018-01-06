package com.wedevol.xmpp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    public static final String BACKEND_ACTION_NOTIFICACION = "NOTIFICACION";
    public static final String BACKEND_ACTION_PANORAMICA = "PANORAMICA";
    public static final String BACKEND_ACTION_LIGA = "LIGA";
    public static final String BACKEND_ACTION_CAMBIO_GRUPO = "CAMBIO_GRUPO";
    public static final String BACKEND_ATTRIBUTE_MESSAGE = "message";
    public static final String BACKEND_ATTRIBUTE_TITULO = "titulo";
    
    public static final String BACKEND_ATTRIBUTE_CABECERA = "cabecera";
    public static final String BACKEND_ATTRIBUTE_DIRECCION = "direccion";
    public static final String BACKEND_ATTRIBUTE_NOMBREUSER = "nombreUser";
    public static final String BACKEND_ATTRIBUTE_UIDIMAGENLIGA = "uidImagenLiga";
    public static final String BACKEND_ATTRIBUTE_TITULOJORNADA = "tituloJornada";
    public static final String BACKEND_ATTRIBUTE_UIDJORNADA = "uidJornada";
    public static final String BACKEND_ATTRIBUTE_FECHA = "fecha";
	public static final String BACKEND_ATTRIBUTE_GRUPONUEVO = "grupoNuevo";
	public static final String BACKEND_ATTRIBUTE_GRUPOVIEJO = "grupoViejo";
	public static final String BACKEND_ATTRIBUTE_UIDUSER = "uidUser";
	public static final String BACKEND_ATTRIBUTE_NOMBRE = "nombre";
	public static final String BACKEND_ATTRIBUTE_AVATAR = "avatar";
	
	// For the app common payload message attributes (android - xmpp server)
	public static final Object PAYLOAD_ATTRIBUTE_RECIPIENT = null;
	public static final Object PAYLOAD_ATTRIBUTE_ACTION = "action";
	public static final String PAYLOAD_ATTRIBUTE_UIDNOTIFICACION = "uidNotificacion";
	public static final String PAYLOAD_ATTRIBUTE_UIDLIGA = "uidImagenLiga";
	public static final String PAYLOAD_ATTRIBUTE_UIDJORNADA = "uidJornada";
	public static final String PAYLOAD_ATTRIBUTE_FECHA = "fecha";
	public static final String PAYLOAD_ATTRIBUTE_TITULO = "titulo";
	public static final String PAYLOAD_ATTRIBUTE_UIDPANORAMICA = "uidPanoramica"; 
	public static final String PAYLOAD_ATTRIBUTE_NOMBREUSER = "nombreUser";
	public static final String PAYLOAD_ATTRIBUTE_UIDIMAGEN = "uidImagen";
	public static final String PAYLOAD_ATTRIBUTE_UIDUSER = "uidUser";
	public static final String PAYLOAD_ATTRIBUTE_NOMBRE = "nombre";
	public static final String PAYLOAD_ATTRIBUTE_GRUPONUEVO = "grupoNuevo";
	public static final String PAYLOAD_ATTRIBUTE_GRUPOVIEJO = "grupoViejo";
	public static final String PAYLOAD_ATTRIBUTE_AVATAR = "avatar";
	// (xmpp server --> FCM)
	public static final String PAYLOAD_NOTIFICATION_TITLE = "title";
	public static final String PAYLOAD_NOTIFICATION_BODY = "body";
	public static final String PAYLOAD_NOTIFICATION_ICON = "icon";
	public static final String PAYLOAD_NOTIFICATION_SOUND = "sound";
	public static final String PAYLOAD_NOTIFICATION_COLOR = "color";
	public static final String PAYLOAD_NOTIFICATION_CLICK_ACTION = "click_action";
	// FireBase 
	public static final DatabaseReference mDataBaseRootRef =FirebaseDatabase.getInstance().getReference();
	public static final DatabaseReference mDataBaseFiestasRef = mDataBaseRootRef.child("Fiestas");
	public static final DatabaseReference mDataBaseDiasFiestaRef = mDataBaseRootRef.child("DiasFiestas");
    public static final DatabaseReference mDataBaseKeysRef = mDataBaseRootRef.child("keys");
	
	

	/**
	 * Returns a random message id to uniquely identify a message
	 */
	public static String getUniqueMessageId() {
		// TODO: replace for your own random message ID that the DB generates
		return "m-" + UUID.randomUUID().toString();
	}
	
	public static String getUidUnico(String cadena) {
		// TODO: replace for your own random message ID that the DB generates
		return cadena + UUID.randomUUID().toString();
	}
	

	    public static Date pasarHoraADate(String hora) {
	        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("HH:mm");
	        Date fecha = null;
	        try {
	            fecha = formatoDelTexto.parse(hora);
	        } catch (ParseException ex) {

	            ex.printStackTrace();
	        }
	        return fecha;
	    }


	
	

}
