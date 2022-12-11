package com.adria.crud.demo.demo;

import javafx.collections.ObservableList;

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

	@Override
	public ObservableList<teacherModel> selectTeachers(ObservableList<teacherModel> listView) {
		String sql = "SELECT * FROM professors";
try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			String id,nom, cognoms;

			while (rs.next()) {
				id = rs.getString("id");
				nom = rs.getString("nom");
				cognoms = rs.getString("cognoms");
				listView.add(new teacherModel(id,nom,cognoms));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listView;
	}

	@Override
	public teacherModel selectTeacher(String text) {
		String sql = "SELECT * FROM professors WHERE id = " + text;
		teacherModel teacherModel = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			String id,nom, cognoms;

			while (rs.next()) {
				id = rs.getString("id");
				nom = rs.getString("nom");
				cognoms = rs.getString("cognoms");
				teacherModel = new teacherModel(id,nom,cognoms);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherModel;
	}

	@Override
	public void insertTeacher(teacherModel professor) {
		String sql = "INSERT INTO professors (nom, cognoms) VALUES ('"+ professor.getNom() +"','"+ professor.getCognoms() +"')";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTeacher(teacherModel professor) {
		String sql = "UPDATE professors SET nom = '"+ professor.getNom() +"', cognoms = '"+ professor.getCognoms() +"' WHERE id = "+ professor.getId();
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTeacher(String text) {
		String sql = "DELETE FROM professors WHERE id = " + text;
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ObservableList<moduleModel> selectModules(ObservableList<moduleModel> listView) {
		String sql = "SELECT m.id, m.nom, m.id_professor, concat(p.nom,\" \",p.cognoms) as professor  FROM moduls m inner join professors p on id_professor = p.id;";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			String id,nom, id_professor,professor;

			while (rs.next()) {
				id = rs.getString("id");
				nom = rs.getString("nom");
				id_professor = rs.getString("id_professor");
				professor = rs.getString("professor");
				listView.add(new moduleModel(id,nom,id_professor,professor));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listView;
	}

	@Override
	public moduleModel selectModule(String text) {
		String sql = "SELECT * FROM moduls WHERE id = " + text;
		moduleModel moduleModel = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			String id,nom, id_professor;

			while (rs.next()) {
				id = rs.getString("id");
				nom = rs.getString("nom");
				id_professor = rs.getString("id_professor");
				moduleModel = new moduleModel(id,nom,id_professor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return moduleModel;
	}

	@Override
	public void insertModule(moduleModel module) {
		String sql = "INSERT INTO moduls (nom, id_professor) VALUES ('"+ module.getNom() +"','"+ module.getId_professor() +"')";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateModule(moduleModel module) {
		String sql = "UPDATE moduls SET nom = '"+ module.getNom() +"', id_professor = '"+ module.getId_professor() +"' WHERE id = "+ module.getId();
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteModule(String text) {
		String sql = "DELETE FROM moduls WHERE id = " + text;
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}













