package com.wedevol.xmpp.bean;

import java.util.Map;

public class Evento {
	public String uidEvento;
	public String titulo;
	public String descripcion;
	public String hora_inicial;
	public String hora_final;
	Map<String, Imagen> imagenes;
	public Evento(String uidEvento, String titulo, String descripcion, String hora_inicial, String hora_final,
			Map<String, Imagen> imagenes) {
		this.uidEvento = uidEvento;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.hora_inicial = hora_inicial;
		this.hora_final = hora_final;
		this.imagenes = imagenes;
	}
	
	public Evento() {
	}

	public String getUidEvento() {
		return uidEvento;
	}
	public void setUidEvento(String uidEvento) {
		this.uidEvento = uidEvento;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getHora_inicial() {
		return hora_inicial;
	}
	public void setHora_inicial(String hora_inicial) {
		this.hora_inicial = hora_inicial;
	}
	public String getHora_final() {
		return hora_final;
	}
	public void setHora_final(String hora_final) {
		this.hora_final = hora_final;
	}
	public Map<String, Imagen> getImagenes() {
		return imagenes;
	}
	public void setImagenes(Map<String, Imagen> imagenes) {
		this.imagenes = imagenes;
	}	
	
	
	


}
