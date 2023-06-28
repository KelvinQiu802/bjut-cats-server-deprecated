package org.animalwatch.dao;

import org.animalwatch.model.Role;
import org.animalwatch.model.User;
import org.animalwatch.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public User getUserByOpenId(String openId) {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE openId = ?;");
        ) {
            st.setString(1, openId);
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    return new User(rs.getString("openId"), rs.getString("userName"),
                            Role.valueOf(rs.getString("role")));
                }
                return null;
            } catch (SQLException e) {
                logger.error("Unable execute the query", e);
            }
        } catch (SQLException e) {
            logger.error("Unable connect to db", e);
        }
        return null;
    }

    public void createUser(User user) {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?);")
        ) {
            st.setString(1, user.openId());
            st.setString(2, user.userName());
            st.setString(3, user.role().toString());
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable connect to db OR execute the update", e);
        }
    }
}
