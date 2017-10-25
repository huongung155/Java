package com.company;

import javax.swing.table.AbstractTableModel;
import java.sql.*;

/**
 * Created by M4800 on 17-Apr-17.
 */
public class ResultSetTableModel extends AbstractTableModel {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    //keep track of database connection status
    private boolean connectedToDatabase = false;

    //initialize resultSet and obtain connection object
    //determine number of rows
    public ResultSetTableModel(String driver, String url, String query) throws SQLException, ClassNotFoundException{
        //load database driver class
        Class.forName(driver);
        //connect to database
        connection = DriverManager.getConnection(url);
        //create Statement to query database
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //update database connection status
        connectedToDatabase = true;
        //set query and execute
        setQuery(query);
    }

    //get class that represents column type
    public Class getColumnClass(int column) throws IllegalStateException{
        //ensure database connection is available
        if(connectedToDatabase == false){
            throw new IllegalStateException("Not Connected to Database");
        }
        //determine Java class column
        try {
            String classname = metaData.getColumnClassName(column + 1);
            //return Class object that represents className
            return Class.forName(classname);
        }catch (Exception exception){       //catch SQLException and ClassNotFoundException
            exception.printStackTrace();
        }
        //If problems occur above, assume type Object
        return Object.class;
    }

    //get number of columns in ResultSet
    @Override
    public int getColumnCount() throws IllegalStateException{
        //ensure database connection is available
        if(connectedToDatabase == false){
            throw new IllegalStateException("Not Connected to Database");
        }
        //determine number of columns
        try {
            return metaData.getColumnCount();
        }catch (SQLException sqlException){     //catch SQLException and print error message
            sqlException.printStackTrace();
        }
        //if problems occur above, return 0 for number of columns
        return 0;
    }

    //get name of a particular column in ResultSet
    @Override
    public String getColumnName(int column) throws IllegalStateException{
        //ensure database connection is available
        if(connectedToDatabase == false){
            throw new IllegalStateException("Not Connected to Database");
        }
        //determine column name
        try{
            return metaData.getColumnName(column + 1);
        }catch(SQLException sqlException){      //catch SQLException and print error message
            sqlException.printStackTrace();
        }
        //if problems, return empty string for column name
        return "";
    }

    //get number of rows in ResultSet
    @Override
    public int getRowCount() throws IllegalStateException{
        //ensure database connection is available
        if(connectedToDatabase == false){
            throw new IllegalStateException("Not Connected to Database");
        }
        return numberOfRows;
    }

    //obtain value in particular row and column
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) throws IllegalStateException{
        //ensure database connection is available
        if(connectedToDatabase == false){
            throw new IllegalStateException("Not Connected to Database");
        }
        //obtain a value at specified ResultSet row and column
        try {
            resultSet.absolute(rowIndex + 1);
            return resultSet.getObject(columnIndex + 1);
        }catch(SQLException sqlException){      //catch SQLException and print error message
            sqlException.printStackTrace();
        }
        //if problems, return empty string object
        return "";
    }

    public void setQuery(String query) throws SQLException{
        //ensure database connection is available
        if(connectedToDatabase == false){
            throw new IllegalStateException("Not Connected to Database");
        }
        //specify query and execute it
        resultSet = statement.executeQuery(query);
        //obtain meta data for ResultSet
        metaData = resultSet.getMetaData();
        //determine number of rows in ResultSet
        resultSet.last();                       //move to last row
        numberOfRows = resultSet.getRow();      //get row number

        //notify JTable that model has changed
        fireTableStructureChanged();
    }

    //close Statement and Connection
    public void disconnectFromDatabase(){
        //close Statement and Connection
        try {
            statement.close();
            connection.close();
        }catch (SQLException sqlException){     //catch SQLException and print error message
            sqlException.printStackTrace();
        }finally {          //update database connection status
            connectedToDatabase = false;
        }
    }
}
