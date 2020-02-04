package br.unitins.provaa2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.provaa2.application.Util;
import br.unitins.provaa2.model.Bandeira;
import br.unitins.provaa2.model.Cartao;
import br.unitins.provaa2.model.Endereso;
import br.unitins.provaa2.model.Estados;
import br.unitins.provaa2.model.ListaDeCompras;
import br.unitins.provaa2.model.Produto;
import br.unitins.provaa2.model.Telefone;
import br.unitins.provaa2.model.TipoProduto;
import br.unitins.provaa2.model.TipodoCartao;
import br.unitins.provaa2.model.Usuario;

public class LoginDAO extends Dao<Usuario>{

	public Usuario verifyPassowrs(String login,String senha,String senhareal) {
		if(getConnection()== null) {
			Util.addMessageError("problema na conexao");
			return null;
		}
		PreparedStatement statpermitirlogin = null;
		PreparedStatement stattpreencherusuario = null;
		PreparedStatement statprodutos= null;
		PreparedStatement statpropro=null;
		Usuario usuarioreceptor = null;
		List<Cartao> cartao = null;
		List<Telefone> telefones = null;
		List<ListaDeCompras> listadecompras = null;
		List<Produto> produtos = null;
		int idlista=0;
		int idproduto=0;
		int id =0;
		try {
			statpermitirlogin = getConnection().prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");
			statpermitirlogin.setString(1, login);
			statpermitirlogin.setString(2, senha);
			ResultSet rs = statpermitirlogin.executeQuery();
			if(rs.next()) {
				stattpreencherusuario = getConnection().prepareStatement("UPDATE usuario SET senhareal = ? WHERE login =? AND senha = ?");
				stattpreencherusuario.setString(1, senhareal);
				stattpreencherusuario.setString(2, login);
				stattpreencherusuario.setString(3, senha);
				stattpreencherusuario.execute();
				
				stattpreencherusuario.close();
				
				usuarioreceptor = new Usuario();
				usuarioreceptor.setLogin(login);
				usuarioreceptor.setSenha(senhareal);
				usuarioreceptor.setNome(rs.getString("nome"));
				usuarioreceptor.setSobrenome(rs.getString("sobrenome"));
				usuarioreceptor.setCpf(rs.getString("cpf"));
				usuarioreceptor.setDatanascimento(rs.getDate("datanascimento") == null? null : (rs.getDate("datanascimento").toLocalDate()));
				id = rs.getInt("idusuario");
			
				stattpreencherusuario = getConnection().prepareStatement("SELECT * FROM endereso WHERE idusuario =? ");
				stattpreencherusuario.setInt(1, id);
				ResultSet rsendereso = stattpreencherusuario.executeQuery();
				if(rsendereso.next()) {
					usuarioreceptor.setEndereso(new Endereso(rsendereso.getString("cep"),rsendereso.getString("rua"),
							rsendereso.getString("numero"),Estados.valueOf(rsendereso.getInt("estado"))));
				}
				
				stattpreencherusuario.close();
				
				stattpreencherusuario = getConnection().prepareStatement("SELECT * FROM cartao WHERE idusuario = ?");
				stattpreencherusuario.setInt(1, id);
				ResultSet rscartao = stattpreencherusuario.executeQuery();
				cartao = new ArrayList<Cartao>();
				while(rscartao.next()) {
					Cartao card = new Cartao();
					card.setBandeira(Bandeira.valueOf(rscartao.getInt("bandeira")));
					card.setTipo(TipodoCartao.valueOf(rscartao.getInt("tipodocartao")));
					card.setCpf(rscartao.getString("cpfcartao"));
					card.setNumerocartao(rscartao.getString("numerocartao"));
					card.setNomenocartao(rscartao.getString("nomedocartao"));
					card.setValidadecartao(rscartao.getDate("validadedocartao")==null?null: rscartao.getDate("validadedocartao").toLocalDate());
					card.setCvv(rscartao.getInt("cvv"));
					cartao.add(card);
				}	
				
				stattpreencherusuario.close();
				
				stattpreencherusuario = getConnection().prepareStatement("SELECT * FROM telefone WHERE idusuario =?");
				stattpreencherusuario.setInt(1, id);
				ResultSet rstelefone = stattpreencherusuario.executeQuery();
				telefones = new ArrayList<Telefone>();
				while(rstelefone.next()) {
					Telefone telefone = new Telefone();
					telefone.setDdd(rstelefone.getString("ddd"));
					telefone.setCodigoarea(rstelefone.getString("codigoarea"));
					telefones.add(telefone);
				}
				stattpreencherusuario.close();
				
				stattpreencherusuario = getConnection().prepareStatement("SELECT * FROM listadecompras WHERE idusuario=?");
				stattpreencherusuario.setInt(1, id);
				ResultSet rslistas = stattpreencherusuario.executeQuery();
				listadecompras = new ArrayList<ListaDeCompras>();
				while(rslistas.next()) {
					ListaDeCompras listadecompra = new ListaDeCompras();
					idlista = rslistas.getInt("idlista");
					statprodutos = getConnection().prepareStatement("SELECT idproduto FROM listaproduto WHERE idulistadecompras=?");
					statprodutos.setInt(1, idlista);
					ResultSet rsproduto = statprodutos.executeQuery();
					produtos = new ArrayList<Produto>();
					while(rsproduto.next()) {
						idproduto = rsproduto.getInt("idproduto");
						statpropro = getConnection().prepareStatement("SELECT * FROM produto WHERE idproduto=?");
						statpropro.setInt(1, idproduto);
						ResultSet rspropro = statpropro.executeQuery();
						if(rspropro.next()) {
							Produto produto = new Produto();
							produto.setNome(rspropro.getString("nome"));
							produto.setValor(rspropro.getDouble("valor"));
							produto.setDescrisao(rspropro.getString("descrisao"));
							produto.setProduto(TipoProduto.valueOf(rspropro.getInt("tipoproduto")));
							produtos.add(produto);
						}
						statpropro.close();
					}
					statprodutos.close();
					listadecompra.setNome(rslistas.getString("nome"));
					listadecompra.setProdutos(produtos);
					listadecompras.add(listadecompra);
				}
				usuarioreceptor.setCompras(listadecompras);
				usuarioreceptor.setCartoes(cartao);
				usuarioreceptor.setTelefone(telefones);
				stattpreencherusuario.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Util.addMessageError("problema na consulta");
			e.printStackTrace();
		}finally {
			try {
				statpermitirlogin.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return usuarioreceptor;
	}
}
