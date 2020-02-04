package br.unitins.provaa2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.unitins.provaa2.application.Util;
import br.unitins.provaa2.model.Cartao;
import br.unitins.provaa2.model.Telefone;
import br.unitins.provaa2.model.Usuario;

public class CadastroDAO extends Dao<Usuario> {
	
	public boolean create(Usuario usuarioaux) {
		boolean resultado = false;
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO usuario (nome,sobrenome,datanascimento,cpf,login,senha,senhareal)"
													+ "VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stat.setString(1, usuarioaux.getNome());
			stat.setString(2, usuarioaux.getSobrenome());
			stat.setDate(3, usuarioaux.getDatanascimento() == null ? null : java.sql.Date.valueOf(usuarioaux.getDatanascimento()));
			stat.setString(4, usuarioaux.getCpf());
			stat.setString(5, usuarioaux.getLogin());
			stat.setString(6, Util.encryptByApache(usuarioaux.getSenha()));
			stat.setString(7, usuarioaux.getSenha());
			
			stat.executeUpdate();
			final ResultSet r = stat.getGeneratedKeys();
			r.next();
			final int lastId = r.getInt("idusuario");
			stat.close();
			
			stat = getConnection().prepareStatement("INSERT INTO endereso(idusuario,cep,rua,numero,estado) VALUES(?,?,?,?,?)");
			stat.setInt(1, lastId);
			stat.setString(2, usuarioaux.getEndereso().getCep());
			stat.setString(3, usuarioaux.getEndereso().getRua());
			stat.setString(4, usuarioaux.getEndereso().getNumero());
			stat.setInt(5, usuarioaux.getEndereso().getEstado().getId());
			stat.execute();
			stat.close();
			
			if(usuarioaux.getTelefone() != null) {
				for (Telefone telefone : usuarioaux.getTelefone()) {
					stat = getConnection().prepareStatement("INSERT INTO telefone (idusuario,ddd,codigoarea) VALUES (?,?,?)");
					stat.setInt(1, lastId);
					stat.setString(2, telefone.getDdd());
					stat.setString(3, telefone.getCodigoarea());
					stat.execute();
				}
			}
			stat.close();
			
			if(usuarioaux.getCartoes() != null) {
				for (Cartao cartoes: usuarioaux.getCartoes()) {
					stat = getConnection().prepareStatement("INSERT INTO cartao (idusuario,bandeira,tipodocartao,cpfcartao,nomedocartao,validadedocartao,"
															+ "cvv,numerocartao) VALUES(?,?,?,?,?,?,?,?)");
					stat.setInt(1, lastId);
					stat.setInt(2, cartoes.getBandeira().getId());
					stat.setInt(3, cartoes.getTipo().getId());
					stat.setString(4, cartoes.getCpf());
					stat.setString(5, cartoes.getNomenocartao());
					stat.setDate(6, cartoes.getValidadecartao() == null ? null : java.sql.Date.valueOf(usuarioaux.getDatanascimento()));
					stat.setInt(7, cartoes.getCvv());
					stat.setString(8, cartoes.getNumerocartao());
					stat.execute();
				}
				resultado = true;
			}
			stat.close();
			
		} catch (SQLException e) {
			Util.addMessageError("Falha ao incluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

}
