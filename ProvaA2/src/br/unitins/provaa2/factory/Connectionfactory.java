package br.unitins.provaa2.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectionfactory {

	private Connectionfactory() {}
	
	public static Connection getInstance(){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/provaa2","topicos1", "123456");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Falha ao registrar o Driver de banco");
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("falha ao conectar com o banco de dados");
			e.printStackTrace();
		}
		return conn;
	}
}
