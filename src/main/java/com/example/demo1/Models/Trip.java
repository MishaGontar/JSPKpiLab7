package com.example.demo1.Models;

import com.example.demo1.DB.DbConnection;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Trip {
    private int id;
    private String from;
    private String to;
    private int train;
    private String departure;
    private String arrival;
    private static Statement stm;
    private int time;

    private Trip(int id, String from, String to, int train, String departure, String arrival, int time) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.train = train;
        this.departure = departure;
        this.arrival = arrival;
        this.time = time;
    }

    public static ArrayList<Trip> getTripByName(String name) {
        try {
            Connection con = DbConnection.getConnection();
            ArrayList<Trip> list = new ArrayList<>();

            PreparedStatement preparedStatement = con.prepareStatement("Select id from settlements where name = ?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                int i = rs.getInt("id");
                PreparedStatement preparedStatement2 = con.prepareStatement("Select * from trip where froms = ? or tos = ?");
                preparedStatement2.setInt(1, i);
                preparedStatement2.setInt(2, i);
                ResultSet rs2 = preparedStatement2.executeQuery();

                while (rs2.next()) {

                    int id = rs2.getInt("id");
                    String from = getSettlement(rs2.getInt("froms"), con);
                    String to = getSettlement(rs2.getInt("tos"), con);
                    int train = rs2.getInt("train");
                    String departure = rs2.getString("departure");
                    String arrival = rs2.getString("arrival");

                    list.add(new Trip(id, from, to, train, departure, arrival, findDifference(departure, arrival)));
                }
                return list;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Trip> getAllTrip() {
        try {
            Connection con = DbConnection.getConnection();
            ArrayList<Trip> list = new ArrayList<>();
            PreparedStatement preparedStatement = con.prepareStatement("Select * from trip");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String from = getSettlement(rs.getInt("froms"), con);
                String to = getSettlement(rs.getInt("tos"), con);
                int train = rs.getInt("train");
                String departure = rs.getString("departure");
                String arrival = rs.getString("arrival");

                list.add(new Trip(id, from, to, train, departure, arrival, findDifference(departure, arrival)));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addTrip(int from, int tos, int train) {
        try {
            Connection con = DbConnection.getConnection();
            String sql = "Insert into trip (froms, tos, train, departure, arrival) values(?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, tos);
            preparedStatement.setInt(3, train);

            String a = RandomDate();
            String b = RandomDate();
            System.out.println();
            if (findDifference(a, b) > 0) {
                preparedStatement.setString(4, a);
                preparedStatement.setString(5, b);
                System.out.println(a + "  " + b);
                System.out.println("Norm");
            } else {
                preparedStatement.setString(4, b);
                preparedStatement.setString(5, a);
                System.out.println(b + "  " + a);
                System.out.println("No norm");
            }
            preparedStatement.executeUpdate();
            System.out.println("It`s okay");
        } catch (Exception e) {
        }
    }

    private static String getSettlement(int id, Connection con) throws SQLException {
        String sql = "Select * from settlements where id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getString("name");
        }
        return null;
    }

    static int findDifference(String start_date, String end_date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);
            long difference_In_Time = d2.getTime() - d1.getTime();
            long difference_In_Seconds = (difference_In_Time / 1000) % 60;
            long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
            long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
            long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
//            System.out.println(
//                    difference_In_Years
//                            + " years, "
//                            + difference_In_Days
//                            + " days, "
//                            + difference_In_Hours
//                            + " hours, "
//                            + difference_In_Minutes
//                            + " minutes, "
//                            + difference_In_Seconds
//                            + " seconds");
            return (int) difference_In_Hours;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static String RandomDate() {
        LocalDateTime time = LocalDateTime.now();
        int random_number1 = (int) (Math.random() * 10);
        int random_number2 = (int) (Math.random() * 10);
        String c_time = time.toLocalTime().getHour() + random_number1 - random_number2 + ":" + time.toLocalTime().getMinute() + ":00";
        return time.toLocalDate() + " " + c_time;
    }


    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getTrain() {
        return train;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public static Statement getStm() {
        return stm;
    }

    public int getTime() {
        return time;
    }
}
