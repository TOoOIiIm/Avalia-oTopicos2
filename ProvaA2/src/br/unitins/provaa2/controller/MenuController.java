package br.unitins.provaa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.provaa2.application.Session;
import br.unitins.provaa2.model.Usuario;
import br.unitins.provaa2.application.Util;
import br.unitins.provaa2.dao.MenuDAO;
import br.unitins.provaa2.model.ListaDeCompras;
import br.unitins.provaa2.model.Produto;

@FacesConfig(version = Version.JSF_2_3)
@Named
@ViewScoped
public class MenuController implements Serializable{
	
	private static final long serialVersionUID = 8805566664603242769L;
	private String nomelista;
	private String nomeproduto;
	private ListaDeCompras compras;
	private List<ListaDeCompras> listacompras;
	private List<Produto> produtos;
	
	public void search() {
		setProdutos(null);
	}
	// vai ter q mudar o selecOneMenu para value=objeto e nao string, e dps mudar o valor de passagem aq pra so objeto que voce quer passar 
	public void adicionarALista(Produto produto) {
		MenuDAO menu = new MenuDAO();
		Usuario usuario = (Usuario) Session.getInstance().getAttribute("talogado");
		if(usuario.getCompras().isEmpty()) {
			System.out.println("passaak");
			criarListaRedirect();
			Util.addMessageError("nao possui listas");
			return;
		}
		usuario.getCompras().add(menu.adicionarListadeCompras(getCompras(),produto.getNome(),usuario));
		limpar();
	}
	public void limpar() {
		setNomelista(null);
		setNomeproduto(null);
	}
	public void criarListaRedirect() {
		Util.redirect("criarLista.xhtml");
	}
	public void criarListadeCompras() {
		MenuDAO menu = new MenuDAO();
		Usuario usuario = (Usuario) Session.getInstance().getAttribute("talogado");
		if(usuario.getCompras() == null) {
			usuario.setCompras(new ArrayList<ListaDeCompras>());
		}
		if(usuario.getCompras().add(menu.criarListadeCompras(getNomelista(),usuario))) {
			Util.redirect("Menu.xhtml");
		}else {
			Util.addMessageError("falha");
		}
	}
	public void pesquisarSobreProduto() {
		Util.redirect("SobreProduto.xhtml");
	}
	public void sobre() {
		Util.redirect("Sobre.xhtml");
	}
	public void ListasdeCompras() {
		Util.redirect("ListasdeCompras.xhtml");
	}
	public void alterarCadastro() {
		Util.redirect("AlterarCadastro.xhtml");
	}
	public void logOut() {
		Session.getInstance().invalidateSession();
		//redirecionar para login
		Util.redirect("Login.xhtml");
	}
	
	public List<Produto> getProdutos() {
		if(produtos == null) {
			MenuDAO menu = new MenuDAO();
			produtos = menu.carregarListaProdutos(getNomeproduto());
			if(produtos == null) 
				produtos = new ArrayList<Produto>();
			menu.endConnection();
		}
		return produtos;
	}
	public String getNomelista() {
		return nomelista;
	}
	public void setNomelista(String nomelista) {
		this.nomelista = nomelista;
	}
	public String getNomeproduto() {
		return nomeproduto;
	}
	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public ListaDeCompras getCompras() {
		if(compras == null) {
			compras = new ListaDeCompras();
			System.out.println("passou aq");
		}
		System.out.println("aq tambem");
		return compras;
	}
	public void setCompras(ListaDeCompras compras) {
		this.compras = compras;
	}
	public List<ListaDeCompras> getListacompras() {
		if(listacompras == null) {
			Usuario usuario = (Usuario) Session.getInstance().getAttribute("talogado");
			listacompras = usuario.getCompras();
			if(listacompras == null) {
				Util.addMessageError("não possui listas");
				listacompras = new ArrayList<ListaDeCompras>();
			}
		}
		return listacompras;
	}
	public void setListacompras(List<ListaDeCompras> listacompras) {
		this.listacompras = listacompras;
	}
	
	
}
