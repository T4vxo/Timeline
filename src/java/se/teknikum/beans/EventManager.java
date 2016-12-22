/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.teknikum.beans;

import com.mysql.jdbc.Connection;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import se.teknikum.utilities.ConnectionFactory;

/**
 *
 * @author DanLun2
 */
@Stateless
public class EventManager {

    public JsonArray getEvents() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM events";
            ResultSet data = stmt.executeQuery(sql);
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            while (data.next()) {
                jsonArrayBuilder.add(Json.createObjectBuilder()
                        .add("id", data.getInt("id"))
                        .add("title", data.getString("title"))
                        .add("text", data.getString("text"))
                        .add("image", data.getString("image"))
                        .add("year", data.getInt("year")).build());
            }
            connection.close();
            return jsonArrayBuilder.build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public JsonArray getEventFromDecade(int year) {
        int min = (year / 10) * 10;
        int max = min + 9;
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String sql = String.format("SELECT * FROM events WHERE year >= %d AND year <= %d", min, max);
            ResultSet data = stmt.executeQuery(sql);
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            while (data.next()) {
                jsonArrayBuilder.add(Json.createObjectBuilder()
                        .add("id", data.getInt("id"))
                        .add("title", data.getString("title"))
                        .add("text", data.getString("text"))
                        .add("image", data.getString("image"))
                        .add("year", data.getInt("year")).build());
            }
            connection.close();
            return jsonArrayBuilder.build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    //SELECT DISTINCT year FROM events ORDER by year

    public JsonArray getDecades() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "SELECT DISTINCT year FROM events ORDER by year";
            ResultSet data = stmt.executeQuery(sql);
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

            int activeDecade = -1;
            while (data.next()) {

                int year = (data.getInt("year") / 10) * 10; //get decade
                if(year > activeDecade){
                jsonArrayBuilder.add(Json.createObjectBuilder()
                        .add("decade",year).build());
                activeDecade = year; 
               }
                
            }
            connection.close();
            return jsonArrayBuilder.build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
