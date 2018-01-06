package com.wedevol.xmpp;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import com.google.api.client.googleapis.util.Utils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.wedevol.xmpp.bean.DiaFiesta;
import com.wedevol.xmpp.bean.DiaFiestaMeta;
import com.wedevol.xmpp.bean.Evento;
import com.wedevol.xmpp.bean.Fiestas;
import com.wedevol.xmpp.bean.Imagen;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.util.ComparadorEvento;
import com.wedevol.xmpp.util.Util;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * Entry Point class for the XMPP Server in dev mode for debugging and testing
 * purposes
 */
public class EntryPoint {
	static Map<String, Fiestas> fiestasMap = new HashMap<String, Fiestas>();
	static Map<String, DiaFiesta> diasFiestas = new HashMap<String, DiaFiesta>();
	static Map<String, DiaFiesta> diasFiestasSel = new HashMap<String, DiaFiesta>(); //De la fiesta en particular
	static Map<String, DiaFiestaMeta> diasFiestasMeta = new HashMap<String, DiaFiestaMeta>();
	static DatabaseReference keysDiasFiestaFire;

	static boolean datosObtenidos;
	static long tiempoInicialWeb,tiempoDatosRecogidos,tiempoEspera;
	public static void main(String[] args) throws SmackException, IOException, InterruptedException {
		final String fcmProjectSenderId = "369052423513";// projectId o  SENDER_ID o Identificador del remitente
		final String Nexus5 = "f1LXNdF2vaA:APA91bG4bysyvfX806pJ3xvRfAXpNggXdIn67b0z7jii0kpf5piAAIQTpP6FqTel7xqyz8u434bmMxEgeKVhr0dkfsyWuScjeuuuuZgBbV92Iu-53mkQEm8HPvMtWYFeEJ5THprEZtOO";
		final String fcmServerKey = "AAAAVe09FVk:APA91bEveYHn4S0MGmL7otGH-Re2wFIgA7mxvGEOV0D94g9mOPbu6xi4hMuICFVsCwGbKtflacK_Nozg9gf7cPDgIotctIQiJLFG7Ot8k47oSRpYimj-e4LOrIvuSwssMTvMCGjM-CtW"; // apiKey
		final String toRegId = "dHVWzOv7mt4:APA91bEz96sGTO93he75PXiS19lPszsVTWpXo4eAWHwlA_uKV0rolcDwXk3KWdUq5rm9UyDeQ8khqqc-3vgnfJ9MqFND1cBUS_Jg0zOfKgOBtv4oBPrCFQWL3oHM7BHJHekBhaA1Gob4";
		final String Cliente1 = "eHh1_E0-2ko:APA91bGKCqb33i77y-Yl9ol0-6ISuvSnkW6VulQuG7fOLaLN7i9szUtR5e5w8Hp_beets1w1vBI9qP7S3_re1-Gma7OKksLs8KqpiS5C0BNmoSHMruAi5iTuoMuSwe_T2VgKpfnXDxjk";
		final String Cliente2 = "feV7KhODqTs:APA91bEe97htXeNNNg_A0JvxEfEW9qmGPvxNYm0iTo5C9pPs9n5vOkGaUurp8ezHR19SG1aTtpKQS6uXcK7Mfh57hipjP5GHgSOMpR_jxn7p29jhcG_TazwjWBDsEN5okGAlsu43Qfmf";
		final String Servidor = "dHVWzOv7mt4:APA91bEz96sGTO93he75PXiS19lPszsVTWpXo4eAWHwlA_uKV0rolcDwXk3KWdUq5rm9UyDeQ8khqqc-3vgnfJ9MqFND1cBUS_Jg0zOfKgOBtv4oBPrCFQWL3oHM7BHJHekBhaA1Gob4";

		
		tiempoInicialWeb = System.currentTimeMillis();
		


		FileInputStream serviceAccount = new FileInputStream("clubesportiu-8143a-firebase-adminsdk-zfmnn-f999682d8a.json");
		
		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
		  .setDatabaseUrl("https://clubesportiu-8143a.firebaseio.com")
		  .build();
		
		FirebaseApp.initializeApp(options);
		
		
		CcsClient ccsClient = CcsClient.prepareClient(fcmProjectSenderId, fcmServerKey, true);
		https:
		try {
			ccsClient.connect();
		} catch (XMPPException e) {
			e.printStackTrace();
		}

		// Esto se pone porque despues de conectar se cierra la conexion, de esta
		// manera no se cierra
		while(true){}
	}		
}