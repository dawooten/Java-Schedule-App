package utilities;

import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the UsersQuery class that holds queries and various methods for the Users controllers.
 * */
public class UsersQuery {

    /**
     * convertUserIDToUserName method. This method takes a userID and converts it to a userName.
     * @param userIDToUserName userID that needs to be converted to an userName.
     * @return userName userName of the userID.
     * */
    @FXML
    public static String convertUserIDToUserName(int userIDToUserName) {

        String userName = null;
        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT User_Name FROM users WHERE User_ID = " + "'" + userIDToUserName + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                userName = resultSet.getString("User_Name");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userName;
    }
}