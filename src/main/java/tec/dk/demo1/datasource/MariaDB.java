package tec.dk.demo1.datasource;

import tec.dk.demo1.models.Person;

import java.sql.*;
import java.util.ArrayList;

public class MariaDB {
    private String connectionString = "jdbc:mysql://localhost/persondb";
    private String userName = "persondbuser";
    private String userPassword = "Kage1234";
    private Connection connection;

    public MariaDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(connectionString, userName, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Connected");
        }
    }

    private void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public ArrayList<Person> getAllPersons() {
        connect();
        ArrayList<Person> personList = new ArrayList<>();

        String sql = "SELECT * FROM person";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    Person person = new Person();
                    person.setId(resultSet.getInt("id"));
                    person.setName(resultSet.getString("name"));
                    person.setEmail(resultSet.getString("email"));
                    person.setNote(resultSet.getString("note"));

                    personList.add(person);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return personList;
    }

    public Person getPerson(int personId) {
        Person person = null;
        connect();

        String sql = "SELECT * FROM PERSON WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, personId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setNote(rs.getString("note"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        close();
        return person;
    }

    public int postPerson(Person person) {
        connect();
        String sql = "INSERT INTO PERSON (name, email, note)" + " values (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setString(3, person.getNote());

            preparedStatement.execute();

            close();
            return 1;

        } catch (SQLException e) {
            System.out.println("Got an exception!" + e.getMessage());
            e.printStackTrace();

            close();
            return 0;
        }
    }

    public int putPerson(Person person) {
        connect();
        String sql = "UPDATE `person` SET name = ?, email = ?, note = ? WHERE id = ?";

        try {
            System.out.println(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setString(3, person.getNote());
            preparedStatement.setInt(4, person.getId());
            preparedStatement.executeUpdate();

            close();
            return 1;

        } catch (SQLException e) {
            System.out.println("Got an exception!");
            e.printStackTrace();

            close();
            return 0;
        }
    }

    public int deletePerson(int personId) {
        connect();
        String sql = "DELETE FROM person WHERE ID = ?";

        try {
            System.out.println(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, personId);
            preparedStatement.execute();
            close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            close();
            return 0;
        }
    }
}