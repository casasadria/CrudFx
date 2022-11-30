package com.adria.crud.demo.demo;

import java.sql.*;

public class DataBaseMySQLDAO implements DataBaseMDAO {

    //Dades de connexió a la base de dades
    private static final String URL = "jdbc:mysql://localhost:3306/dam2";
	private static final String USER = "root";
	private static final String PASS = "";
    //Connexió a la base de dades
    private Connection conn;

	public DataBaseMySQLDAO(){
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}













