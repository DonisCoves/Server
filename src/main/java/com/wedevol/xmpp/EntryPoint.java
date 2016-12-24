package com.wedevol.xmpp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.util.MultiMap;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.NonNull;
import com.google.firebase.tasks.OnCompleteListener;
import com.google.firebase.tasks.OnFailureListener;
import com.google.firebase.tasks.Task;
import com.wedevol.xmpp.bean.CcsOutMessage;
import com.wedevol.xmpp.bean.DiaFiesta;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.server.MessageHelper;
import com.wedevol.xmpp.util.Util;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * Entry Point class for the XMPP Server in dev mode for debugging and testing
 * purposes
 */
public class EntryPoint {
	public static void main(String[] args) throws SmackException, IOException, InterruptedException {
		final String fcmProjectSenderId = "351834780446";//projectId o SENDER_ID
		final String fcmServerKey = "AAAAUer8sx4:APA91bGGvMhgdZ2pG7ErlnTp1JEFJ5rqDJ3egtK45xknfu5KEdUItKePQPiGQzhpGCxULgN025jydVuoo6X9IO91YWzILvlMy-J_t0x0X42I4uU6h5N7_5M4R2phAi6mZNZLmg5P7ZWIZ8ptW_eL5rUpsAysLuTAUw"; //apiKey
		final String toRegId = "dHVWzOv7mt4:APA91bEz96sGTO93he75PXiS19lPszsVTWpXo4eAWHwlA_uKV0rolcDwXk3KWdUq5rm9UyDeQ8khqqc-3vgnfJ9MqFND1cBUS_Jg0zOfKgOBtv4oBPrCFQWL3oHM7BHJHekBhaA1Gob4";
		final String Cliente1 = "eHh1_E0-2ko:APA91bGKCqb33i77y-Yl9ol0-6ISuvSnkW6VulQuG7fOLaLN7i9szUtR5e5w8Hp_beets1w1vBI9qP7S3_re1-Gma7OKksLs8KqpiS5C0BNmoSHMruAi5iTuoMuSwe_T2VgKpfnXDxjk"; 
		final String Cliente2 = "feV7KhODqTs:APA91bEe97htXeNNNg_A0JvxEfEW9qmGPvxNYm0iTo5C9pPs9n5vOkGaUurp8ezHR19SG1aTtpKQS6uXcK7Mfh57hipjP5GHgSOMpR_jxn7p29jhcG_TazwjWBDsEN5okGAlsu43Qfmf";
		final String Nexus5 = "fw3kfeKphUo:APA91bE1QFlKnxZXJqM5OgbFNP8Jay8Ztz19NIGPtHdW_1yMvBoCa-Z__7brrGvN2DpJxlaTi97i7yrsB2qx61RX6INMtKUOsuHg4tP4nqh9KfEc85d7MQuqB5dm2wl9-KaXihCPtFTE";	
		final String Servidor = "dHVWzOv7mt4:APA91bEz96sGTO93he75PXiS19lPszsVTWpXo4eAWHwlA_uKV0rolcDwXk3KWdUq5rm9UyDeQ8khqqc-3vgnfJ9MqFND1cBUS_Jg0zOfKgOBtv4oBPrCFQWL3oHM7BHJHekBhaA1Gob4";
		List <DiaFiesta> diasFiesta = new ArrayList();


//		FirebaseOptions options = new FirebaseOptions.Builder()
//				.setServiceAccount(new FileInputStream("C:\\Server\\project-1031372115432573568-firebase-adminsdk-vqcwh-64723994ce.json"))
//				.setDatabaseUrl("https://turistorre.firebaseio.com/")
//				.build();
//		FirebaseApp.initializeApp(options);
//
//
//		DatabaseReference mDataBaseRootRef = FirebaseDatabase.getInstance().getReference();
//		DatabaseReference mDataBaseFestRef = mDataBaseRootRef.child("DiasFiesta");
//		DatabaseReference dbRef = mDataBaseFestRef.child("Dia1");
//		try {
//			port(5000);
//			staticFileLocation("/public");
//
//			get("/", (request, response) -> {
//
//				return new ModelAndView(null, "index.ftl");
//			}, new FreeMarkerEngine());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		get("/pruebas", (request, response) -> {
//			Map<String, Object> attributes = new HashMap<>();
//			String [] dias = {"Someone & Co","Someone &amp; Co.","miercoles"};
//			String dia = "lunes";
//			attributes.put("dias", dias);
//			return new ModelAndView(attributes, "pruebas.ftl");
//		}, new FreeMarkerEngine());

		//        dbRef.setValue("I'm writing data");
		//        try {
		//			Thread.sleep(10000);
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//        System.out.println("FIN!.");
		//        final CountDownLatch sync = new CountDownLatch(1);
		//        dbRef.push().setValue("new value")
		//           .addOnCompleteListener(new OnCompleteListener<Void>() {
		//              public void onComplete(Task<Void> task) {
		//                sync.countDown();
		//              }
		//            });
		//        sync.await();


		//    	try {
		//            dbRef.setValue("MIERDA").addOnCompleteListener(new OnCompleteListener<Void>() {
		//                @Override
		//                public void onComplete(@NonNull Task<Void> task) {
		//                    System.out.println("Exito");
		//                }
		//
		//            }).addOnFailureListener(new OnFailureListener() {
		//                @Override
		//                public void onFailure(@NonNull Exception e) {
		//                	System.out.println("Fracaso");
		//                }
		//            });
		//        }
		//        catch (Exception e) {
		//        	System.out.println("Fracaso");
		//        }
		//    	try {
		//			Thread.sleep(20000);
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}





				CcsClient ccsClient = CcsClient.prepareClient(fcmProjectSenderId, fcmServerKey, true);
		
				try {
					ccsClient.connect();
				} catch (XMPPException e) {
					e.printStackTrace();
				}

				port(Integer.valueOf(System.getenv("PORT")));
			    staticFileLocation(System.getenv("PORT"));
//			    port(5000);
//			    staticFileLocation("/public");
		//	    
		//	    System.out.println("el puerto es ************* "+System.getenv("PORT"));
			    get("/hello", (req, res) -> "Hello World");
		//	    
			    get("/", (request, response) -> {
			            Map<String, Object> attributes = new HashMap<>();
			            attributes.put("message", "Hello World!");
		
			            return new ModelAndView(attributes, "index.ftl");
			        }, new FreeMarkerEngine());
		
		//	    get("/fiestas", (request, response) -> {
		//            Map<String, Object> attributes = new HashMap<>();
		//            attributes.put("dias", 9);
		//            attributes.put("campos", 4);
		//
		//            return new ModelAndView(attributes, "fiestas.ftl");
		//        }, new FreeMarkerEngine());
		//	    
		//	    post("/env_fire_fiestas", (request, response) -> {
		//	    	
		//	    	String [] titulos = request.queryMap("titulo").values();
		//	    	String [] descripciones = request.queryMap("descripcion").values();
		//	    	String [] fechas = request.queryMap("fecha").values();
		//	    	String [] lugares = request.queryMap("lugar").values();
		//	    	
		//	    	DiaFiesta diaFiesta;
		//	    	for (int i=0;i<titulos.length;i++){
		//	    		diaFiesta = new DiaFiesta(titulos[i],descripciones[i],fechas[i],lugares[i]);
		//	    		diasFiesta.add(diaFiesta);
		//	    	}
		//	    	try {
		//		    	FirebaseOptions options = new FirebaseOptions.Builder()
		//		    			  .setServiceAccount(new FileInputStream("C:/Server/TurisTorre-0ba686a57a60.json"))
		//		    			  .setDatabaseUrl("https://turistorre.firebaseio.com/")
		//		    			  .build();
		//		    			FirebaseApp.initializeApp(options);
		//				
		//			} catch (Exception e) {
		//				System.out.println(e.getMessage());
		//			}
		//	    	DatabaseReference mDataBaseRootRef = FirebaseDatabase.getInstance().getReference();
		//	    	DatabaseReference mDataBaseFestRef = mDataBaseRootRef.child("DiasFiesta");
		//	        DatabaseReference dbRef = mDataBaseFestRef.child("Dia1");
		//
		//	    	
		//	    	try {
		//	            dbRef.setValue(diasFiesta.get(0)).addOnCompleteListener(new OnCompleteListener<Void>() {
		//	                @Override
		//	                public void onComplete(@NonNull Task<Void> task) {
		//	                    System.out.println("Exito");
		//	                }
		//
		//	            }).addOnFailureListener(new OnFailureListener() {
		//	                @Override
		//	                public void onFailure(@NonNull Exception e) {
		//	                	System.out.println("Fracaso");
		//	                }
		//	            });
		//	        }
		//	        catch (Exception e) {
		//	        	System.out.println("Fracaso");
		//	        }
		//	    	
		//	    	
		//	    	mDataBaseFestRef.push().setValue(diasFiesta.get(0)).addOnCompleteListener(new OnCompleteListener<Void>() {
		//				
		//	    		
		//				@Override
		//				public void onComplete(Task<Void> arg0) {
		//					System.out.println("EXITO");					
		//				}
		//			}).addOnFailureListener(new OnFailureListener() {
		//                @Override
		//                public void onFailure(@NonNull Exception e) {
		//                	System.out.println("ERROR");	
		//                }
		//            });;
		//
		//	    	 
		//
		//            return new ModelAndView( null,"index2.ftl");
		//        }, new FreeMarkerEngine());
		//
		//	    
		//
		//	
		//
				while (true) {
					// TODO: Improve this because the app closes itself after the
					// execution of the connect method
				}
	}
}
