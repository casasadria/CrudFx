package com.adria.crud.demo.demo;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DataBaseMDAO {

    ObservableList<teacherModel> selectTeachers(ObservableList<teacherModel> listView);

    public teacherModel selectTeacher(String text);

    public void insertTeacher(teacherModel professor);

    public void updateTeacher(teacherModel professor);

    public void deleteTeacher(String text);

    public ObservableList<moduleModel> selectModules(ObservableList<moduleModel> listView);

    public moduleModel selectModule(String text);

    public void insertModule(moduleModel module);

    public void updateModule(moduleModel module);

    public void deleteModule(String text);
}
