package br.unitins.provaa2.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.provaa2.application.Util;
import br.unitins.provaa2.dao.CadastroDAO;
import br.unitins.provaa2.model.Bandeira;
import br.unitins.provaa2.model.Cartao;
import br.unitins.provaa2.model.Endereso;
import br.unitins.provaa2.model.Estados;
import br.unitins.provaa2.model.Telefone;
import br.unitins.provaa2.model.TipodoCartao;
import br.unitins.provaa2.model.Usuario;

@Named
@ViewScoped
public class CadastroController implements Serializable{

	private static final long serialVersionUID = 4119198454944223359L;
	private Usuario usuario;
	private Endereso endereso;
	private Telefone tele;
	private Cartao cardd;
	
	public void cadastrar() {
		getUsuario().setEndereso(getEndereso());
		incluirTelefone();
		CadastroDAO cadastro = new CadastroDAO();
		if(cadastro.create(getUsuario())) {
			limpar();
			Util.redirect("Login.xhtml");
		}
		cadastro.endConnection();
	}
	public void limpar() {
		setUsuario(null);
		setCardd(null);
		setTelefone(null);
		setEndereso(null);
	}
	public void incluirTelefone() {
		if(getUsuario().getTelefone() == null) {
			getUsuario().setTelefone(new ArrayList<Telefone>());
		}
		getUsuario().getTelefone().add(getTelefone());
		setTelefone(null);
	}
	
	public void removerTelefone(Telefone tel) {
		getUsuario().getTelefone().remove(tel);
		//getUsuario().getTelefone().set(getUsuario().getTelefone().indexOf(tel), null);	
	}
	public void incluirCartao() {
		if(getUsuario().getCartoes() == null) {
			getUsuario().setCartoes(new ArrayList<Cartao>());
		}
		getUsuario().getCartoes().add(getCardd());
		for (Cartao card : getUsuario().getCartoes()) {
			System.out.println(card);
		}
		setCardd(null);
	}
	public void excluirCartao(Cartao card) {
		getUsuario().getCartoes().remove(card);
	}
	public Estados[] getListaEstado() {
		return Estados.values();
	}
	public Bandeira[] getListaCartao() {
		return Bandeira.values();
	}
	public TipodoCartao[] getListaCaartao() {
		return TipodoCartao.values();
	}
	
	
	public Endereso getEndereso() {
		if(endereso == null) {
			endereso = new Endereso();
		}
		return endereso;
	}
	public void setEndereso(Endereso endereso) {
		this.endereso = endereso;
	}
	public Telefone getTelefone() {
		if(tele == null) {
			tele = new Telefone();
		}
		return tele;
	}
	public void setTelefone(Telefone tele) {
		this.tele = tele;
	}
	public Cartao getCardd() {
		if(cardd == null) {
			cardd = new Cartao();
		}
		return cardd;
	}
	public void setCardd(Cartao cardd) {
		this.cardd = cardd;
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
