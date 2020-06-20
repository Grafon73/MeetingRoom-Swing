package com.grafon.test.eventsDB;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class EventsDB {

     Map<String[], String[]> eventList = new HashMap<>();



    public Map<String[], String[]> getEventList() {
        return eventList;
    }

    public void addEventList(String date, String hour, String name, String message) {

        eventList.put(new String[]{date, hour}, new String[]{name, message});

    }


    public void ReadSQL() {
        eventList.clear();
        try {
            String query = "select EventDate, EventHour, EventCreatorName, EventMessage from eventdata";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_password?serverTimezone=UTC",
                    "Grafon", "1q2w3e");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String date = rs.getString(1);
                String hour = rs.getString(2);
                String name = rs.getString(3);
                String message = rs.getString(4);

                addEventList(date, hour, name, message);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("READ SQL DATA Error");
        }

    }

    public void WriteSQL(String day,String hour,String username,String message) {
        try {
            String query = String.format("INSERT INTO eventdata (EventDate, EventHour, EventCreatorName, EventMessage) \n" +
                    " VALUES ('%s', %s , '%s', '%s!');", day,hour,username,message);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_password?serverTimezone=UTC",
                    "Grafon", "1q2w3e");
            Statement stmt = con.createStatement();

            stmt.executeUpdate(query);

            ReadSQL();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("WRITE SQL DATA Error");
        }
    }
    public void DeleteSQL(String day,String hour) {
        try {
            String query = String.format("DELETE FROM eventdata \n WHERE EventDate='%s' AND EventHour = '%s'", day, hour);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_password?serverTimezone=UTC",
                    "Grafon", "1q2w3e");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("DELETE SQL DATA Error");
        }

    }
}