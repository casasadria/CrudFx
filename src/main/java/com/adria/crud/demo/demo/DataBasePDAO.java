package com.adria.crud.demo.demo;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public interface DataBasePDAO {
    public boolean validateLogin(String username, String password) throws SQLException;

    public boolean inserirAlumnes(pupilModel pupilModel);

    ObservableList<pupilModel> selectAlumnes(ObservableList<pupilModel> listView);

    public boolean deletePupil(String text);
    public boolean updatePupil(pupilModel pupilModel);

    public pupilModel selectAlumne(String text);
}
