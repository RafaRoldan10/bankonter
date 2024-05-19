package jpa.controllers;

import jpa.model.Tipocontrato;

public class ControladorTipoContrato extends SuperControlador{
private static ControladorTipoContrato instance = null;
	
	public ControladorTipoContrato() {
		super("tipocontrato",Tipocontrato.class);
	}
	
	public static ControladorTipoContrato getInstance() {
		if(instance == null) {
			instance = new ControladorTipoContrato();
		}
		return instance;
	}
}
