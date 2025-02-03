package lk.ijse.gym_management.Utill;

import lk.ijse.gym_management.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDUtill {
    public static <T> T execute(String sql, Object... obj) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
            pst.setObject((i + 1), obj[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")) {
            ResultSet resultSet = pst.executeQuery();
            return (T) resultSet;
        } else {
            int i = pst.executeUpdate();
            boolean isDone = i > 0;
            return (T) ((Boolean) isDone);
        }
    }
}
