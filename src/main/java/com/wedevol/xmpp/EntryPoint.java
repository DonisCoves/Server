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
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import com.google.api.client.googleapis.util.Utils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.wedevol.xmpp.bean.DiaFiesta;
import com.wedevol.xmpp.bean.DiaFiestaMeta;
import com.wedevol.xmpp.bean.Evento;
import com.wedevol.xmpp.bean.Fiestas;
import com.wedevol.xmpp.bean.Imagen;
import com.wedevol.xmpp.server.CcsClient;
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
		final String fcmProjectSenderId = "351834780446";// projectId o  SENDER_ID
		final String Nexus5 = "f1LXNdF2vaA:APA91bG4bysyvfX806pJ3xvRfAXpNggXdIn67b0z7jii0kpf5piAAIQTpP6FqTel7xqyz8u434bmMxEgeKVhr0dkfsyWuScjeuuuuZgBbV92Iu-53mkQEm8HPvMtWYFeEJ5THprEZtOO";
		final String fcmServerKey = "AAAAUer8sx4:APA91bGGvMhgdZ2pG7ErlnTp1JEFJ5rqDJ3egtK45xknfu5KEdUItKePQPiGQzhpGCxULgN025jydVuoo6X9IO91YWzILvlMy-J_t0x0X42I4uU6h5N7_5M4R2phAi6mZNZLmg5P7ZWIZ8ptW_eL5rUpsAysLuTAUw"; // apiKey
		final String toRegId = "dHVWzOv7mt4:APA91bEz96sGTO93he75PXiS19lPszsVTWpXo4eAWHwlA_uKV0rolcDwXk3KWdUq5rm9UyDeQ8khqqc-3vgnfJ9MqFND1cBUS_Jg0zOfKgOBtv4oBPrCFQWL3oHM7BHJHekBhaA1Gob4";
		final String Cliente1 = "eHh1_E0-2ko:APA91bGKCqb33i77y-Yl9ol0-6ISuvSnkW6VulQuG7fOLaLN7i9szUtR5e5w8Hp_beets1w1vBI9qP7S3_re1-Gma7OKksLs8KqpiS5C0BNmoSHMruAi5iTuoMuSwe_T2VgKpfnXDxjk";
		final String Cliente2 = "feV7KhODqTs:APA91bEe97htXeNNNg_A0JvxEfEW9qmGPvxNYm0iTo5C9pPs9n5vOkGaUurp8ezHR19SG1aTtpKQS6uXcK7Mfh57hipjP5GHgSOMpR_jxn7p29jhcG_TazwjWBDsEN5okGAlsu43Qfmf";
		final String Servidor = "dHVWzOv7mt4:APA91bEz96sGTO93he75PXiS19lPszsVTWpXo4eAWHwlA_uKV0rolcDwXk3KWdUq5rm9UyDeQ8khqqc-3vgnfJ9MqFND1cBUS_Jg0zOfKgOBtv4oBPrCFQWL3oHM7BHJHekBhaA1Gob4";

		FirebaseOptions options = new FirebaseOptions.Builder()
//				.setServiceAccount(new FileInputStream("C:/Server/project-1031372115432573568-firebase-adminsdk-vqcwh-64723994ce.json")) //local
				.setServiceAccount(new FileInputStream("project-1031372115432573568-firebase-adminsdk-vqcwh-64723994ce.json"))
				.setDatabaseUrl("https://turistorre.firebaseio.com/")
				.build();
		FirebaseApp.initializeApp(options);
		tiempoInicialWeb = System.currentTimeMillis();
		
		CcsClient ccsClient = CcsClient.prepareClient(fcmProjectSenderId, fcmServerKey, true);

		try {
			ccsClient.connect();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		

//				crearFiestasFireEjemplo();
		//recogidaDatos();

		port(Integer.valueOf(System.getenv("PORT")));
	    //port(5000);
		staticFileLocation("/public");
System.out.println("ACTUALIZADOs2323");
		get("/", (request, response) -> {
			return new ModelAndView(null, "index.ftl");
		}, new FreeMarkerEngine());
		
		post("/form-login", (request, response) -> {
			String email = request.queryParams("email");
			String password = request.queryParams("password");
			if (email.equals("turistorre17@gmail.com") && password.equals("adminadmin")){
				return new ModelAndView(null, "opciones.ftl");
			}
			else {
				return new ModelAndView(null, "index.ftl");	
			}
		}, new FreeMarkerEngine());
		
		
//		get("/", (request, response) -> {
//			return new ModelAndView(null, "index.ftl");
//		}, new FreeMarkerEngine());

		get("/fiestas_main", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			List <String> uidsFiestas = new ArrayList<String>(); 
			uidsFiestas = getUidsFiestas(); //Si no hay ninguna devuelve null

			attributes.put("uidsFiestas", uidsFiestas);
			return new ModelAndView(attributes, "fiestas_main.ftl");
		}, new FreeMarkerEngine());

		get("/fiestas", (request, response) -> { //Venimos de la creacion de la fiesta, fiestas_main
			return new ModelAndView(null, "fiestas.ftl");
		}, new FreeMarkerEngine());

		post("/crear_dias", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			List<String> titulosDiasFiesta = new ArrayList<String>();
			String fecha_inicio, fecha_final, titulo, descripcion;
			int duracion ;
			DiaFiesta diaFiesta;
			String anyo;
			String uidFiestas = request.queryParams("uidFiestas");
			if (uidFiestas!=null){ //Cargamos Datos. Sentre alguna vegà??
				setDiasFiesta(uidFiestas);
				diaFiesta = getPrimerDiaFiesta(uidFiestas);
				anyo = diaFiesta.getUidDiaFiesta().substring(diaFiesta.getUidDiaFiesta().length()-4);
				titulosDiasFiesta = getTitulosDiasFiesta(uidFiestas);
				attributes.put("dia_fiesta", diaFiesta);
			}
			else {  //Creamos   AQUI!!!!!!
				titulo = request.queryParams("titulo");
				anyo = titulo.substring(titulo.length()-4);
				descripcion = request.queryParams("descripcion");
				fecha_inicio = request.queryParams("fecha_inicio");
				fecha_final = request.queryParams("fecha_final");
				uidFiestas = titulo.replace(" ", "");
				duracion = duracionFiestas(fecha_inicio, fecha_final);
				titulosDiasFiesta = creacionTitulosDias(fecha_inicio, duracion);
				setFiesta(uidFiestas,titulo,descripcion,titulosDiasFiesta);  // Creamos la fiesta 
				Util.mDataBaseFiestasRef.child(uidFiestas).setValue(fiestasMap.get(uidFiestas));
				crearPaginasDias(uidFiestas);
			}
			attributes.put("titulosDiasFiesta", titulosDiasFiesta);
			attributes.put("anyo", anyo);
			return new ModelAndView(attributes, "dias_fiesta.ftl");
		}, new FreeMarkerEngine());

		get("/crear_dias", (request, response) -> { //Venimos del dropdown
			Map<String, Object> attributes = new HashMap<>();
			List<String> titulosDiasFiesta = new ArrayList<String>();
			DiaFiesta diaFiesta;
			String anyo;
			String uidFiestas = request.queryParams("uidFiestas");
			anyo = uidFiestas.substring(uidFiestas.length()-4);
			setDiasFiesta(uidFiestas);
			diaFiesta = getPrimerDiaFiesta(uidFiestas);
			titulosDiasFiesta = getTitulosDiasFiesta(uidFiestas);

			crearPaginasDias(uidFiestas);

			attributes.put("anyo", anyo);
			attributes.put("dia_fiesta", diaFiesta);
			attributes.put("dia_seleccionado", titulosDiasFiesta.get(0));
			attributes.put("titulosDiasFiesta", titulosDiasFiesta);
			return new ModelAndView(attributes, "dias_fiesta.ftl");
		}, new FreeMarkerEngine());

		post("/crear_dia", (request, response) -> {  //al pulsar boton "crear dia" de dias_fiesta
			Map<String, Object> attributes = new HashMap<>();
			List<String> titulosDiasFiesta = new ArrayList<String>();
			DiaFiesta diaFiesta;
			Map<String, Evento> eventos = new HashMap<String, Evento>();
			Evento evento;
			String tituloDiaFiesta = request.queryParams("dia_seleccionado");
			String anyo = request.queryParams("anyo");
			String uidDiaFiesta = tituloDiaFiesta.replace(" ", "");

			uidDiaFiesta = uidDiaFiesta + anyo;
			//Si hay algun dia que se corresponde lo cargamos
			//diaFiesta = getDiaFiesta(uidDiaFiesta);
			String [] titulos = request.queryMap("titulo").values();
			String [] descripciones = request.queryMap("descripcion").values();
			String [] hora_iniciales = request.queryMap("hora_inicial").values();
			String [] eliminaciones = null ;
			if (request.queryParams("eliminar")!=null)
				eliminaciones = request.queryMap("eliminar").values();
			for (int i=0;i<titulos.length;i++){
				if (titulos[i]!="") {
					if (eliminaciones!=null) {
						if (!eliminarEventos(eliminaciones, i)){
							evento = new Evento(hora_iniciales[i]+" "+"uidEvento"+(i+1),titulos[i],descripciones[i],hora_iniciales[i],null);
							eventos.put(evento.getUidEvento(),evento);
						}
					}
					else {
						evento = new Evento(hora_iniciales[i]+" "+"uidEvento"+(i+1),titulos[i],descripciones[i],hora_iniciales[i],null);
						eventos.put(evento.getUidEvento(),evento);
					}
				}
			}
			
			///OJOOORRLL///
			Iterator<String> itUidEventos = eventos.keySet().iterator();
			Map<Date,String> eventosDate = new TreeMap<>();
			while (itUidEventos.hasNext()){
				String uidEvento = itUidEventos.next();
				Evento ev = eventos.get(uidEvento);
				String hora = ev.getHora_inicial();
				eventosDate.put(Util.pasarHoraADate(hora),uidEvento);
			}
			
			Iterator<Date> itUidEventos2 = eventosDate.keySet().iterator();
			int i=0;
			Util.mDataBaseKeysRef.child(uidDiaFiesta).setValue(null);
			while (itUidEventos2.hasNext()){
				Date key = itUidEventos2.next();
				String uidEvento = eventosDate.get(key);
				Util.mDataBaseKeysRef.child(uidDiaFiesta).child(uidEvento).setValue(i++);
			}
			


			//Añadimos el uid (que es el dia seleccionado) dela metainformacion del dia para que este todo linkado
			diaFiesta=new DiaFiesta(uidDiaFiesta,tituloDiaFiesta,eventos);
			diasFiestas.put(uidDiaFiesta, diaFiesta);
			//Borramos todo el dia
			Util.mDataBaseDiasFiestaRef.child(diaFiesta.getUidDiaFiesta()).setValue(null);
			Util.mDataBaseDiasFiestaRef.child(diaFiesta.getUidDiaFiesta()).setValue(diaFiesta);
			String uidFiestas = getUidFiesta(uidDiaFiesta);
			titulosDiasFiesta = getTitulosDiasFiesta(uidFiestas);
			attributes.put("titulosDiasFiesta", titulosDiasFiesta);
			attributes.put("dia_fiesta", diaFiesta);
			attributes.put("dia_seleccionado", tituloDiaFiesta);
			attributes.put("anyo", anyo);


			return new ModelAndView(attributes, "dias_fiesta.ftl");
		}, new FreeMarkerEngine());



		// Esto se pone porque despues de conectar se cierra la conexion, de esta
		// manera no se cierra
		while(true){}
	}

	//Miramos si una fila tiene el check de eliminar para no insertarla
	private static boolean eliminarEventos(String[] eliminaciones, int fila) {
		boolean eliminar =false;
		for (int i=0;i<eliminaciones.length;i++){
			String filaEliminarStr = eliminaciones[i].substring(15, eliminaciones[i].length());
			int filaEliminar = Integer.valueOf(filaEliminarStr) - 1; 
			if (filaEliminar==fila)
				eliminar = true;
		}
		return eliminar;
	}

	private static void crearPaginasDias(String uidFiestas) {
		Fiestas fiesta = getFiesta(uidFiestas);
		Map <String, DiaFiestaMeta> diasFiestaMetaMap = fiesta.getDiasFiestas();	
		Iterator<String> it = diasFiestaMetaMap.keySet().iterator();
		while (it.hasNext()){
			String tituloDiaFiesta = it.next();
			get("/"+tituloDiaFiesta, (request, response) -> {
				String anyo = request.queryParams("anyo"); 
				String uidDiaFiesta = tituloDiaFiesta.replace(" ", "");
//				uidDiaFiesta = uidDiaFiesta + anyo;
				DiaFiesta diaFiesta = diasFiestas.get(uidDiaFiesta);  //Sino existe es que lo estamos creando
				List <String> titulosDiasFiesta = new ArrayList<String>();
				Map<String, Object> attributes = new HashMap<>();
				String dia_seleccionado = request.queryParams("dia_seleccionado");  //uidDiaFiesta
				titulosDiasFiesta = getTitulosDiasFiesta(uidFiestas);
				attributes.put("titulosDiasFiesta", titulosDiasFiesta);
				attributes.put("dia_fiesta", diaFiesta);
				attributes.put("dia_seleccionado", dia_seleccionado);
				attributes.put("anyo", anyo);
				return new ModelAndView(attributes, "dias_fiesta.ftl");
			}, new FreeMarkerEngine());
		}
	}

	//	//Dado el uid de un dia de fiesta nos devuelve el uid de la fiesta a la que pertenece
	private static String getUidFiesta(String uidDiaFiesta) {
		boolean encontrado=false;
		Map <String, DiaFiestaMeta> diasFiestaMetaMap ;
		Fiestas fiesta;
		String uidFiesta,uidDiaFiestaAux,uidFiestaEncontrado = null;
		Iterator<String> it = fiestasMap.keySet().iterator();
		while (it.hasNext() && !encontrado){
			uidFiesta = it.next();
			fiesta = fiestasMap.get(uidFiesta);
			diasFiestaMetaMap = fiesta.getDiasFiestas();
			Iterator<String> it2 = diasFiestaMetaMap.keySet().iterator();
			while (it2.hasNext() && !encontrado){
				uidDiaFiestaAux = it2.next();
				if (uidDiaFiestaAux.equals(uidDiaFiesta)){
					encontrado=true;
					uidFiestaEncontrado = uidFiesta;
				}
			}
		}
		return uidFiestaEncontrado;
	}

	//Nos devuelve un listado de los ids de dias fiestas (sabado23junio2015, domingo24junio2015...)
	private static List<String> getUidsDiasFiesta(String uidFiestas) {
		Fiestas fiesta = getFiesta(uidFiestas);
		List<String> dias = new ArrayList<String>();
		Map <String, DiaFiestaMeta> diasFiestaMetaMap = fiesta.getDiasFiestas();
		Iterator<String> it = diasFiestaMetaMap.keySet().iterator();
		while (it.hasNext()) {
			dias.add(it.next());
		}
		return dias;
	}

	//Nos devuelve un listado de los titulos de dias fiestas (sabado 23 junio,  domingo 24 junio...)
	private static List<String> getTitulosDiasFiesta(String uidFiestas) {
		Fiestas fiesta = getFiesta(uidFiestas);
		List<String> titulos = new ArrayList<String>();
		Map <String, DiaFiestaMeta> diasFiestaMetaMap = fiesta.getDiasFiestas();
		Iterator<String> it = diasFiestaMetaMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next(); //uidDiasFiesta
			DiaFiestaMeta diaFiestaMeta = diasFiestaMetaMap.get(key);
			//DiaFiesta diaFiesta = diasFiestas.get(key);  
			titulos.add(diaFiestaMeta.getTituloDiaFiesta());  
		}
		return titulos;
	}

	//Devuelve el primer dia de fiesta o null en caso de que no exista la fiesta (creacion)
	private static DiaFiesta getPrimerDiaFiesta(String uidFiestas) {
		Fiestas fiesta = getFiesta(uidFiestas);
		if (fiesta!= null){
			Map <String, DiaFiestaMeta> diasFiestaMetaMap = fiesta.getDiasFiestas();
			Iterator<String> it = diasFiestaMetaMap.keySet().iterator();
			String key = it.next(); //uidFiesta
			DiaFiesta value = diasFiestas.get(key);  
			return value;
		}
		else
			return null;
	}

	//Dado el uidDiaFiesta devuelve el dia de fiesta con todos sus datos (eventos , ectc...)
	private static DiaFiesta getDiaFiesta(String uidDiaFiesta){
		return diasFiestas.get(uidDiaFiesta);
	}

	//Crea una fiesta con toda su metainformacion (incluido los dias fiestas, sus ids pasados como parametro de entrada)
	//Tambien creamos el orden en Firebase para despues mostrarlos ordenados por fecha
	private static void setFiesta(String uidFiestas, String titulo, String descripcion, List<String> titulosDiasFiesta) {
		Map<String, DiaFiestaMeta> fiestasMeta = new HashMap<String, DiaFiestaMeta>();
		DiaFiestaMeta diaFiestaMeta ;
		Fiestas fiestas ;
		String uidDiaFiesta;
		keysDiasFiestaFire = Util.mDataBaseKeysRef.child(uidFiestas);

		for (int i=0;i<titulosDiasFiesta.size();i++){
			uidDiaFiesta = setUidDiaFiesta(titulosDiasFiesta.get(i),uidFiestas);
			diaFiestaMeta = new DiaFiestaMeta(uidDiaFiesta,titulosDiasFiesta.get(i));
			fiestasMeta.put(uidDiaFiesta, diaFiestaMeta);
			keysDiasFiestaFire.child(uidDiaFiesta).setValue(i);
		}
		fiestas = new Fiestas(uidFiestas,titulo,descripcion,fiestasMeta);
		//Si existe la sobreescribimos con los nuevos valores
		fiestasMap.put(fiestas.getUidFiestas(), fiestas);

	}

	//Crea el uid del dia de fiesta que será "sabado24febrero2015"
	private static String setUidDiaFiesta(String tituloDiasFiesta, String uidFiestas) {
		String titulo;
		String anyo;
		String uid;
		titulo = tituloDiasFiesta.replace(" ", "");
		anyo= uidFiestas.substring(uidFiestas.length()-4);
		uid = titulo+anyo;
		return uid;
	}

	//Dado un uidFiesta , desde su metainformacion obtenemos toda la informacion de sus dias y lo guardamos
	//En un mapa de dias fiesta
	private static void setDiasFiesta(String uidFiestas) {
		Fiestas fiesta = getFiesta(uidFiestas);
		Map <String, DiaFiestaMeta> diasFiestaMetaMap = fiesta.getDiasFiestas();	
		Iterator<String> it = diasFiestaMetaMap.keySet().iterator();
		while (it.hasNext()){
			String key = it.next(); //uidFiesta
			DiaFiesta value = diasFiestas.get(key);  //toda la informacion, eventos, etc...
			diasFiestasSel.put(key, value);  //diasFiestas se creará/actualizará en crearPaginasDias 
		}
	}



	//Dado el uid de una fiesta nos devuelve todos sus meta datos o null sino existe esa fiesta
	private static Fiestas getFiesta(String uidFiesta) {
		return fiestasMap.get(uidFiesta);
	}

	////Dado el uid de meta de un dianos devuelve toda su info
	//private static DiaFiestaMeta getDiaFiesta(String uidDiaFiestaMeta) {
	//	return diasFiestasMap.get(uidDiaFiestaMeta);
	//}

	//Extrae los uids de FireBase del nodo fiestas(el uidFiestas)
	private static List<String> getUidsFiestas() {
		//static Map<String, Fiestas> fiestasMap = new HashMap<String, Fiestas>();
		List<String> titulos=new ArrayList<String>();
		for (String titulo : fiestasMap.keySet()){
			titulos.add(titulo);
		}
		if (titulos.isEmpty())
			return null;
		else
			return titulos;
	}

	private static void recogidaDatos() {
		//Festes
		Util.mDataBaseFiestasRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for (DataSnapshot fiestasList: dataSnapshot.getChildren()) {
					Fiestas fiestas = fiestasList.getValue(Fiestas.class);
					fiestasMap.put(fiestasList.getKey(), fiestas);
				}
				tiempoDatosRecogidos = System.currentTimeMillis();
				tiempoEspera = (tiempoDatosRecogidos - tiempoInicialWeb)/100;//3532
				System.out.println("El tiempo de espera para recoger los datos de fiestas es de : "+tiempoEspera+ " decimas de segundos");
			}
			@Override
			public void onCancelled(DatabaseError arg0) {}
		});

		Util.mDataBaseDiasFiestaRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for (DataSnapshot diasFiestasList: dataSnapshot.getChildren()) {
					DiaFiesta diaFiesta = diasFiestasList.getValue(DiaFiesta.class);
					diasFiestas.put(diasFiestasList.getKey(), diaFiesta);
				}
				tiempoDatosRecogidos = System.currentTimeMillis();
				tiempoEspera = (tiempoDatosRecogidos - tiempoInicialWeb)/100;//3532
				System.out.println("El tiempo de espera para recoger los datos de diasfiestas es de : "+tiempoEspera+ " decimas de segundos");
			}
			@Override
			public void onCancelled(DatabaseError arg0) {}
		});

		//DiesDeFestes
	}



	// Formato "[Sabado 25 julio,Sabado 26 julio]"
	private static List<String> creacionTitulosDias(String fecha_inicio, int duracion) {
		String nombreMes, nombreDiaSemana;
		List <String> listaDias=new ArrayList<String>();
		String strDiaFinal ;
		StringTokenizer tokens = new StringTokenizer(fecha_inicio, "-");
		Calendar cal = Calendar.getInstance();
		String anyoStr = tokens.nextToken();
		String mesStr = tokens.nextToken();
		String diaStr = tokens.nextToken();
		int anyo = Integer.parseInt(anyoStr);
		int mes = Integer.parseInt(mesStr);
		int dia =Integer.parseInt(diaStr);

		cal.set(anyo,mes-1 ,dia);
		for (int i = 0; i < duracion; i++) {
			nombreDiaSemana = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("es","ES"));
			nombreDiaSemana = nombreDiaSemana.substring(0,1).toUpperCase() + nombreDiaSemana.substring(1); //primera letra en mayuscula
			dia = cal.get(Calendar.DATE);
			nombreMes = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es","ES"));
			nombreMes = nombreMes.substring(0,1).toUpperCase() + nombreMes.substring(1); //primera letra en mayuscula
			strDiaFinal = String.format("%s %d de %s", nombreDiaSemana, dia, nombreMes);  //Domingo 27 de Noviembre
			listaDias.add(strDiaFinal);
			cal.add(Calendar.DATE,1);
		}
		return listaDias;
	}

	private static int duracionFiestas(String fecha_inicio, String fecha_final) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		int dias = 0;
		try {
			Date inicio = formato.parse(fecha_inicio);
			Date fin = formato.parse(fecha_final);
			long inicio_milis = inicio.getTime();
			long fin_milis = fin.getTime();
			long duracion = fin_milis - inicio_milis;
			dias = (int)Math.floor(duracion / (1000 * 60 * 60 * 24))+1; 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dias;
	}

	private static void crearFiestasFireEjemplo(){
		Imagen img1 = new Imagen("uidImagen1");
		Imagen img2 = new Imagen("uidImagen2");
		Imagen img3 = new Imagen("uidImagen3");
		Imagen img4 = new Imagen("uidImagen4");

		Map<String, Imagen> imagenes1 = new HashMap<String, Imagen>();
		Map<String, Imagen> imagenes2 = new HashMap<String, Imagen>();
		imagenes1.put(img1.getUidImagen(), img1);
		imagenes1.put(img2.getUidImagen(), img2);
		imagenes2.put(img3.getUidImagen(), img3);
		imagenes2.put(img4.getUidImagen(), img4);
		Evento evento1 = new Evento("uidEvento1","Evento1","Es el evento1","09:00",imagenes1);
		Evento evento2 = new Evento("uidEvento2","Evento2","Es el evento2","09:00",imagenes2);
		Evento evento3 = new Evento("uidEvento3","Evento3","Es el evento3","09:00",imagenes1);
		Evento evento4 = new Evento("uidEvento4","Evento4","Es el evento4","09:00",imagenes2);

		Map<String, Evento> eventos1 = new HashMap<String, Evento>();
		Map<String, Evento> eventos2 = new HashMap<String, Evento>();
		eventos1.put(evento1.getUidEvento(), evento1);
		eventos1.put(evento2.getUidEvento(), evento2);
		eventos2.put(evento3.getUidEvento(), evento3);
		eventos2.put(evento4.getUidEvento(), evento4);

		//Creamos la metainformacion de los dias(para que la bbdd no sea muy anidada
		DiaFiestaMeta diaFiestaMeta1=new DiaFiestaMeta("diaFiesta12015","dia Fiesta 12015");
		DiaFiestaMeta diaFiestaMeta2=new DiaFiestaMeta("diaFiesta22015","dia Fiesta 12015");
		DiaFiestaMeta diaFiestaMeta3=new DiaFiestaMeta("diaFiesta32015","dia Fiesta1 2015");
		DiaFiestaMeta diaFiestaMeta4=new DiaFiestaMeta("diaFiesta42015","dia Fiesta 12015");

		//Creamos los dias de Fiestas con sus eventos y con sus imagenes
		DiaFiesta diaFiesta1=new DiaFiesta(diaFiestaMeta1.getUidDiaFiesta(),"diaFiesta1 2015", eventos1);
		DiaFiesta diaFiesta2=new DiaFiesta(diaFiestaMeta2.getUidDiaFiesta(), "diaFiesta2 2015", eventos2);
		DiaFiesta diaFiesta3=new DiaFiesta(diaFiestaMeta3.getUidDiaFiesta(), "diaFiesta3 2015", eventos1);
		DiaFiesta diaFiesta4=new DiaFiesta(diaFiestaMeta4.getUidDiaFiesta(), "diaFiesta4 2015", eventos2);

		//Generamos la metainformacion de fiestas 
		Map<String, DiaFiestaMeta> fiestas1Meta = new HashMap<String, DiaFiestaMeta>();
		Map<String, DiaFiestaMeta> fiestas2Meta = new HashMap<String, DiaFiestaMeta>();
		fiestas1Meta.put(diaFiestaMeta1.getUidDiaFiesta(), diaFiestaMeta1);
		fiestas1Meta.put(diaFiestaMeta2.getUidDiaFiesta(), diaFiestaMeta2);
		fiestas2Meta.put(diaFiestaMeta3.getUidDiaFiesta(), diaFiestaMeta3);
		fiestas2Meta.put(diaFiestaMeta4.getUidDiaFiesta(), diaFiestaMeta4);

		//Generamos fiestas 
		Map<String, DiaFiesta> fiestas1 = new HashMap<String, DiaFiesta>();
		Map<String, DiaFiesta> fiestas2 = new HashMap<String, DiaFiesta>();
		fiestas1.put(diaFiesta1.getUidDiaFiesta(), diaFiesta1);
		fiestas1.put(diaFiesta2.getUidDiaFiesta(), diaFiesta2);
		fiestas2.put(diaFiesta3.getUidDiaFiesta(), diaFiesta3);
		fiestas2.put(diaFiesta4.getUidDiaFiesta(), diaFiesta4);

		Fiestas fiestasx = new Fiestas("fiestas1","Fiestas titulo1","Fiestas1 Descripcion",fiestas1Meta);
		Fiestas fiestasy = new Fiestas("fiestas2","Fiestas titulo2","Fiestas2 Descripcion",fiestas2Meta);

		Util.mDataBaseRootRef.child("DiasFiestas").child(diaFiesta1.getUidDiaFiesta()).setValue(diaFiesta1);
		Util.mDataBaseRootRef.child("DiasFiestas").child(diaFiesta2.getUidDiaFiesta()).setValue(diaFiesta2);
		Util.mDataBaseRootRef.child("DiasFiestas").child(diaFiesta3.getUidDiaFiesta()).setValue(diaFiesta3);
		Util.mDataBaseRootRef.child("DiasFiestas").child(diaFiesta4.getUidDiaFiesta()).setValue(diaFiesta4);
		Util.mDataBaseRootRef.child("Fiestas").child(fiestasx.getUidFiestas()).setValue(fiestasx);
		Util.mDataBaseRootRef.child("Fiestas").child(fiestasy.getUidFiestas()).setValue(fiestasx);
	}
		
}