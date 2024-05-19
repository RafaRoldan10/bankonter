package jpa.controllers;

import jpa.model.Usuario;

public class ControladorUsuario extends SuperControlador{
private static ControladorUsuario instance = null;
	
	public ControladorUsuario() {
		super("usuario",Usuario.class);
	}
	
	public static ControladorUsuario getInstance() {
		if(instance == null) {
			instance = new ControladorUsuario();
		}
		return instance;
	}
}
