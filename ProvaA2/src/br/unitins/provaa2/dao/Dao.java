package br.unitins.provaa2.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.unitins.provaa2.factory.Connectionfactory;

public abstract class Dao<T> {
	
	private Connection conn = null;

	public Connection getConnection() {
		if(conn == null) {
			conn = Connectionfactory.getInstance();
		}
		return conn;
	}

	public void endConnection() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
