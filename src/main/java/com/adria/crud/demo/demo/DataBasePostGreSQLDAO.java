package com.adria.crud.demo.demo;

import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class DataBasePostGreSQLDAO implements DataBasePDAO {

    //Dades de connexió a la base de dades
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASS = "a";
	//Realitzem la connexió

    //Connexió a la base de dades
    private Connection conn;

	public DataBasePostGreSQLDAO(){
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean inserirAlumnes(pupilModel pupilModel) {
		Statement ps = null;
		boolean insert = false;
		try {
			String sql = "INSERT INTO alumnes (nom, cognoms, data_naixement, curs_actual,progenitors) VALUES ('"+ pupilModel.getNom() +"','"+ pupilModel.getCognoms() +"', '"+ pupilModel.getData_naixement() +"', '"+ pupilModel.getCurs_actual() +"'::public.curs,"+ pupilModel.getProgenitors() +")";
			ps = conn.createStatement();
			ps.executeUpdate(sql);
			insert = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insert;
	}

	@Override
	public ObservableList<pupilModel> selectAlumnes(ObservableList<pupilModel> listView) {
		try {
			String sql = "SELECT * FROM alumnes";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			String id,nom, cognoms, curs_actual,progenitor1,progenitor2, progenitors;
			String data_naixement;


			while (rs.next()) {
				id = rs.getString("id");
				nom = rs.getString("nom");
				cognoms = rs.getString("cognoms");
				data_naixement = rs.getString("data_naixement");
				curs_actual = rs.getString("curs_actual");
				progenitor1 = rs.getString("progenitors").split(",")[0];
				progenitor2 = rs.getString("progenitors").split(",")[1];
				progenitors = progenitor1 + " i " + progenitor2;
				listView.add(new pupilModel(id,nom,cognoms,data_naixement,curs_actual,progenitors));
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return listView;
	}

	@Override
	public boolean deletePupil(String text) {
		boolean delete = false;
		Statement ps = null;
		try {
			String sql = "DELETE FROM alumnes WHERE id = " + text;
			ps = conn.createStatement();
			ps.executeUpdate(sql);
			delete = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return delete;
	}

	@Override
	public boolean updatePupil(pupilModel pupilModel) {
		boolean update = false;
		Statement ps = null;
		try {
			String sql = "UPDATE alumnes SET nom = '"+ pupilModel.getNom() +"', cognoms = '"+ pupilModel.getCognoms() +"', data_naixement = '"+ pupilModel.getData_naixement() +"', curs_actual = '"+ pupilModel.getCurs_actual() +"'::public.curs, progenitors = "+ pupilModel.getProgenitors()+" WHERE id = "+ pupilModel.getId();
			ps = conn.createStatement();
			ps.executeUpdate(sql);
			update = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public pupilModel selectAlumne(String text) {
		pupilModel pupilModel = null;

		try {
			String sql = "SELECT * FROM alumnes WHERE id = " + text;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			String id, nom, cognoms, curs_actual, progenitor1, progenitor2, progenitors;
			String data_naixement;

			while (rs.next()) {
				id = rs.getString("id");
				nom = rs.getString("nom");
				cognoms = rs.getString("cognoms");
				data_naixement = rs.getString("data_naixement");
				curs_actual = rs.getString("curs_actual");
				progenitor1 = rs.getString("progenitors").split(",")[0];
				progenitor2 = rs.getString("progenitors").split(",")[1];
				progenitors = progenitor1 + "," + progenitor2;
				pupilModel = new pupilModel(id, nom, cognoms, data_naixement, curs_actual, progenitors);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pupilModel;
	}

		public boolean validateLogin(String username, String password) {
		boolean login = false;
		try {
			System.out.println(username);
			System.out.println(password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT count(1) FROM useraccounts where username = '"+username+"' and password = '"+password+"'");
			while (rs.next()) {
				if(rs.getInt(1) == 1) {
					login = true;
				}else {
					login = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}


}













