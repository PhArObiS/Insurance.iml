import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTable {

    private static final String DATABASE_URL = "jdbc:sqlite:C:\\SQLite\\insurance.db";


    private Connection connection;

    public DatabaseTable() {
        connect();
    }

    // Connect to the SQLite database
    private void connect() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            if (connection != null) {
                String createTable = "CREATE TABLE IF NOT EXISTS InsuranceAgreement (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "clientName TEXT, " +
                        "yearlyFee REAL, " +
                        "risk TEXT, " +
                        "insuranceAmount REAL);";
                try (Statement statement = connection.createStatement()) {
                    statement.execute(createTable);
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e.getMessage());
        }
    }


    // Insert an InsuranceAgreement into the database
    public void insertAgreement(InsuranceAgreement agreement) {
        String insertAgreement = "INSERT INTO InsuranceAgreement(clientName, yearlyFee, risk, insuranceAmount) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAgreement);
            preparedStatement.setString(1, agreement.getClient().getName());
            preparedStatement.setDouble(2, agreement.getYearlyFee());
            preparedStatement.setString(3, agreement.getRisk().name());
            preparedStatement.setDouble(4, agreement.getInsuranceAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Get all agreements from the database
    public void getAllAgreements() {
        String getAllAgreements = "SELECT * FROM InsuranceAgreement;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllAgreements);
            while (resultSet.next()) {
                String clientName = resultSet.getString("clientName");
                double yearlyFee = resultSet.getDouble("yearlyFee");
                Risk risk = Risk.valueOf(resultSet.getString("risk"));
                double insuranceAmount = resultSet.getDouble("insuranceAmount");
                System.out.println("Client: " + clientName + ", Yearly Fee: " + yearlyFee + ", Risk: " + risk.name() + ", Insurance Amount: " + insuranceAmount);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete an agreement from the database by id
    public void deleteAgreement(int id) {
        String deleteAgreement = "DELETE FROM InsuranceAgreement WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteAgreement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
