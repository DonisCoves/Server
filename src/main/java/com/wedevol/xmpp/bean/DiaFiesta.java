package com.wedevol.xmpp.bean;

import java.util.Map;
import java.util.TreeMap;

import com.wedevol.xmpp.util.ComparadorEvento;


public  class DiaFiesta {
	private String uidDiaFiesta;
	private String titulo;
	private Map <String, Evento> eventos;
	public DiaFiesta(String uidDiaFiesta, String titulo, TreeMap<String, Evento> eventosNoOrdenados) {
		ComparadorEvento comparador ;
		
		this.uidDiaFiesta = uidDiaFiesta;
		this.titulo = titulo;
		comparador = new ComparadorEvento(eventosNoOrdenados);
		eventos = new TreeMap<String, Evento>(comparador);
		eventos.putAll(eventosNoOrdenados);
	}
	
	public DiaFiesta() {
	}
	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUidDiaFiesta() {
		return uidDiaFiesta;
	}
	public void setUidDiaFiesta(String uidDiaFiesta) {
		this.uidDiaFiesta = uidDiaFiesta;
	}
	public Map<String, Evento> getEventos() {
		return eventos;
	}
	public void setEventos(Map<String, Evento> eventos) {
		this.eventos = eventos;
	}

}
