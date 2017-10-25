package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

/**
 * Created by M4800 on 17-Apr-17.
 */
public class DisplayQueryResults extends JFrame{
    //JDBC Driver
    String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //database URL
    String server = "DESKTOP-VTRPVEB";//Server name
    int port = 1433;                  //TCP Port
    String user = "quan", pass = "sa", database = "ApressFinancial";
    String DATABASE_URL = "jdbc:sqlserver://" + server + ": " + port + ";DatabseName = " + database + ";User = " + user + ";Password = " + pass;
    //default query retrieves all data from Customers table
    String DEFAULT_QUERY = "SELECT * FROM CustomerDetails.Customers";

    private ResultSetTableModel tableModel;
    private JTextArea queryArea;

    //create ResultSetTableModel and GUI
    public DisplayQueryResults(){
        super("Displaying Query Results");

        //create ResultSetTableModel and display database table
        try {
            //create TableModel for results of query SELECT * FROM authors
            tableModel = new ResultSetTableModel(JDBC_DRIVER, DATABASE_URL, DEFAULT_QUERY);
            //setup JTextArea in which user types queries
            queryArea = new JTextArea(DEFAULT_QUERY, 3, 150);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);

            JScrollPane scrollPane = new JScrollPane(queryArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants. HORIZONTAL_SCROLLBAR_NEVER);
            //setup JButton for submitting queries
            JButton submitButton = new JButton("Submit Query");

            //create Box to manage placement of queryArea and submitButton in GUI
            Box box = Box.createHorizontalBox();
            box.add(scrollPane);
            box.add(submitButton);

            //create JTable delegate for tableModel
            JTable resultTable = new JTable(tableModel);

            //place GUI components on content pane
            Container c = getContentPane();
            c.add(box, BorderLayout.NORTH);
            c.add(new JScrollPane(resultTable), BorderLayout.CENTER);

            //create event listener for submitButton
            submitButton.addActionListener(new ActionListener() {
                //pass query to table model
                @Override
                public void actionPerformed(ActionEvent e) {
                    //perform a new query
                    try{
                        tableModel.setQuery(queryArea.getText());
                    }catch (SQLException sqlException){     //catch SQLException when performing a new query
                        JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
                        //try to recover from invalid user query by executing default query
                        try{
                            tableModel.setQuery(DEFAULT_QUERY);
                            queryArea.setText(DEFAULT_QUERY);
                        }catch (SQLException sqlException2){        //catch SQLException when performing default query
                            JOptionPane.showMessageDialog(null, sqlException2.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
                            //ensure database connection is closed
                            tableModel.disconnectFromDatabase();
                            System.exit(1);
                        }//end inner catch
                    }//end outer catch
                }//end actionPerformed
            });//end call to addActionListener

            //set window size and display window
            setSize(500, 250);
            setVisible(true);
        }catch (ClassNotFoundException classNotFound){      //catch ClassNotFoundException thrown by ResultSetTableModel if database driver not found
            JOptionPane.showMessageDialog(null, "Cloudscape driver not found", "Driver not found", JOptionPane.ERROR_MESSAGE);
            tableModel.disconnectFromDatabase();
            System.exit(1);
        } catch (SQLException sqlException) {   //catch SQLException thrown by ResultSetTableModel if problems occur while setting up database connection and querying database
            JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
            tableModel.disconnectFromDatabase();
            System.exit(1);
        }

        //dispose of window when user quits application (this overrides the default of HIDE_ON_CLOSE)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //ensure database connection is closed when the user quits application
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                tableModel.disconnectFromDatabase();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args){new DisplayQueryResults();}
}
