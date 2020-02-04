package br.unitins.provaa2.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.provaa2.dao.LoginDAO;
import br.unitins.provaa2.application.Session;
import br.unitins.provaa2.application.Util;
import br.unitins.provaa2.model.Usuario;

@Named
@ViewScoped
public class LoginController implements Serializable {
	
	private static final long serialVersionUID = -3030480197131001789L;
	private Usuario usuario;

	public void entrar() {
		LoginDAO auxiliar = new LoginDAO();
		String auxiliar1 = Util.encryptByApache(getUsuario().getSenha());
		Usuario usuariodeentrada = auxiliar.verifyPassowrs(getUsuario().getLogin(), auxiliar1, getUsuario().getSenha());
		if(usuariodeentrada != null) {
			Session.getInstance().setAttribute("talogado", usuariodeentrada);
			Util.redirect("Menu.xhtml");
			return;
		}else {
			Util.addMessageError("Falha ao encontrar");
		}
	}
	public void limpar() {
		setUsuario(null);
	}
	public void cadastrar() {
		Util.redirect("Cadastro.xhtml");
	}
	public void sobre() {
		
	}
	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
