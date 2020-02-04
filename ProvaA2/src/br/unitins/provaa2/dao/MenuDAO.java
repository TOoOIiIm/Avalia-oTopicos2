package br.unitins.provaa2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.provaa2.application.Util;
import br.unitins.provaa2.model.ListaDeCompras;
import br.unitins.provaa2.model.Produto;
import br.unitins.provaa2.model.TipoProduto;
import br.unitins.provaa2.model.Usuario;

public class MenuDAO extends Dao<Usuario>{
	
	public List<Produto> carregarListaProdutos(String nome){
		if(getConnection()==null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		List<Produto> produto = new ArrayList<Produto>();
		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement("SELECT nome,valor,descrisao,tipoproduto FROM produto WHERE nome ILIKE ?");
			stat.setString(1, (nome== null? "%": "%"+nome+"%"));
			ResultSet rs = stat.executeQuery();
			Produto produtos = null;
			while(rs.next()) {
					produtos = new Produto();
					produtos.setNome(rs.getString("nome"));
					produtos.setValor(rs.getDouble("valor"));
					produtos.setDescrisao(rs.getString("descrisao"));
					produtos.setProduto(TipoProduto.valueOf(rs.getInt("tipoproduto")));
					produto.add(produtos);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Util.addMessageError("falha ao achar");
			e.printStackTrace();
			produto = null;
		}finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return produto;
	}
	public ListaDeCompras adicionarListadeCompras(ListaDeCompras compra,String nomeproduto,Usuario usuario){
		if(getConnection()==null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		if(compra.getNome().isBlank() || compra.getNome() == null) {
			Util.addMessageError("nao possui lista");
			return null;
		}
		//System.out.println(nomelista);
		System.out.println(nomeproduto);
		System.out.println("//");
		int idlista = 0;
		int idusuario = 0;
		int idproduto = 0;
		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement("SELECT idusuario FROM usuario WHERE cpf=? AND login=? AND datanascimento=?");
			stat.setString(1, usuario.getCpf());
			stat.setString(2, usuario.getLogin());
			stat.setDate(3, usuario.getDatanascimento() == null ? null : java.sql.Date.valueOf(usuario.getDatanascimento()));
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				idusuario = rs.getInt("idusuario");
				//System.out.println(idusuario);
			}
			stat.close();
			
			stat = getConnection().prepareStatement("SELECT idlista FROM listadecompras WHERE idusuario=? AND nome=?");
			stat.setInt(1, idusuario);
			stat.setString(2, compra.getNome());
			ResultSet rss = stat.executeQuery();
			if(rss.next()) {
				idlista = rss.getInt("idlista");
				//System.out.println(idlista);
			}
			stat.close();
			
			stat =getConnection().prepareStatement("SELECT idproduto FROM produto WHERE nome=?");
			stat.setString(1, nomeproduto);
			ResultSet rsss = stat.executeQuery();
			if(rsss.next()) {
				idproduto = rsss.getInt("idproduto");
			}
			stat.close();
			
			stat = getConnection().prepareStatement("INSERT INTO listaproduto(idulistadecompras,idproduto) VALUES(?,?) ");
			stat.setInt(1, idlista);
			stat.setInt(2, idproduto);
			stat.execute();
			stat.close();
			System.out.println("chegou aq");
			Util.addMessageError("inserção realizada com sucesso");
			System.out.println("haha");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Util.addMessageError("falha ao achar");
			e.printStackTrace();
		}finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public ListaDeCompras criarListadeCompras(String nomelista,Usuario usuario){
		if(getConnection()==null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		PreparedStatement stat = null;
		ListaDeCompras lista = null;
		int idusuario= 0;
		int idlista = 0;
		try {
			stat = getConnection().prepareStatement("SELECT idusuario FROM usuario WHERE cpf=? AND login=? AND datanascimento=?");
			stat.setString(1, usuario.getCpf());
			stat.setString(2, usuario.getLogin());
			stat.setDate(3, usuario.getDatanascimento() == null ? null : java.sql.Date.valueOf(usuario.getDatanascimento()));
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				idusuario = rs.getInt("idusuario");
				//System.out.println(idusuario);
			}
			stat.close();
			stat = getConnection().prepareStatement("SELECT idlista FROM listadecompras");
			ResultSet rsss = stat.executeQuery();
			while(rsss.next()) {
				idlista = rsss.getInt("idlista");
			}
			stat.close();
			
			lista = new ListaDeCompras();
			stat = getConnection().prepareStatement("INSERT INTO listadecompras(idlista,idusuario,nome) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);
			stat.setInt(1, idlista+1);
			stat.setInt(2, idusuario);
			stat.setString(3, nomelista);
			stat.execute();
			stat.close();
			
			lista.setNome(nomelista);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Util.addMessageError("erro ao criar lista");
			e.printStackTrace();
		}finally{
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
}
