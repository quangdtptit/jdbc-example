package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        System.out.println("Them student");
        Connection connection = null;
        try {
            // tạo kết nối csdl
            String url = "jdbc:mysql://10.0.16.72:3306/school_management";
            String username = "root";
            String password = "123456aA@";
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            // System.out.println(connection); // test

            // truy vấn
            // SELECT
            String keyEnter = "STD0007 OR 1=1"; // nguoi dung nhap
            String sql = "SELECT s.student_id, s.name, s.code, s.gender, s.birthday FROM student s " +
                    "WHERE s.code = ?";
            System.out.println(sql);
            // Statement statement = connection.createStatement(); // => not recommend, statement => DDL
            PreparedStatement preparedStatement = connection.prepareStatement(sql); // DML
            preparedStatement.setString(1, keyEnter);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long studentId = resultSet.getLong("student_id");
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                String gender = resultSet.getString("gender");
                Date birthday = resultSet.getDate("birthday");
                Student student = new Student(studentId, name, code, gender, birthday);
                System.out.println(student);
            }

            // INSERT , UPDATE, DELETE
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
//            System.out.println("Loi ket noi csdl " + ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {

            }
        }
    }
}

class Student {
    Long studentId;
    String name;
    String code;
    String gender;
    Date birthday;

    public Student(Long studentId, String name, String code, String gender, Date birthday) {
        this.studentId = studentId;
        this.name = name;
        this.code = code;
        this.gender = gender;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
