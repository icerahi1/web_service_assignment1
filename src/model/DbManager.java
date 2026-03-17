package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DbManager {
    private static final String DB_URL ="jdbc:sqlite:studentDB.db";

    public void setupDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                + "id INTEGER PRIMARY KEY, "
                + "name TEXT, "
                + "age INTEGER, "
                + "gpa REAL)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Database and table ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveStudent(Student student) {
        // SQL command to insert data
        String insertSQL = "INSERT INTO students (id, name, age, gpa) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.setDouble(4, student.getGpa());

            pstmt.executeUpdate();
            System.out.println("Student saved to database successfully!");
        } catch (Exception e) {
            System.out.println("Student ID already exists or database error.");
        }
    }
}
