package com.wedevol.xmpp.util;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import com.wedevol.xmpp.bean.Evento;

public class ComparadorEvento implements Comparator<String>{
	Map<String,Evento> base;

	public ComparadorEvento(Map<String,Evento> base) {
		this.base = base;
	}

	@Override
	public int compare(String o1, String o2) {
		String hora1,hora2;
		Date d1,d2,topeNoche;
		
		
		hora1 = o1.substring(0, 5);
		hora2 = o2.substring(0, 5);
		d1 = Util.pasarHoraADate(hora1);
		d2 = Util.pasarHoraADate(hora2);
		topeNoche = Util.pasarHoraADate("05:59");
		//o1=01:00 y o2=02:00
		if (!o1.equalsIgnoreCase(o2)){
			if (topeNoche.after(d1) && topeNoche.after(d2))
				if (d1.before(d2))
					return -1;
				else
					return 1;
			//o1=01:00 y o2=22:00
			else if (topeNoche.after(d1) && topeNoche.before(d2))
				return 1;
			//o1=22:00 y 02=02:00
			else if (topeNoche.before(d1) && topeNoche.after(d2))
				return -1;
			//o1=22:00 y 02=23:00
			else if (d1.before(d2))
				return -1;
			else
				return 1;
		}
		else
			return 0;
	}

}
