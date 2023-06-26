package org.animalwatch.dao;

import org.animalwatch.model.*;
import org.animalwatch.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatsDao {
    private Logger logger = LoggerFactory.getLogger(CatsDao.class);

    public List<Cat> getAllCats() {
        List<Cat> cats = new ArrayList<>();
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM cats;")
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    cats.add(constructCat(rs));
                }
            } catch (SQLException e) {
                logger.error("Can not construct cat", e);
            }
        } catch (SQLException e) {
            logger.error("Unable to connect to the database or operation failed.", e);
        }
        return cats;
    }

    public void removeAllCats() {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("DELETE FROM cats;");
        ) {
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to connect to the database or operation failed.", e);
        }
    }

    private Cat constructCat(ResultSet rs) throws SQLException {
        return new Cat(rs.getInt("id"),
                rs.getString("name"),
                Campus.valueOf(rs.getString("campus")),
                rs.getString("avatar"),
                Gender.valueOf(rs.getString("gender")),
                rs.getString("color"),
                rs.getString("hair"),
                Neutered.valueOf(rs.getString("neutered")),
                CatState.valueOf(rs.getString("state")),
                rs.getString("description"),
                rs.getString("birthday"),
                rs.getString("adoptionDay"),
                rs.getString("position"),
                rs.getDouble("longitude"),
                rs.getDouble("latitude"),
                rs.getInt("orderWeight"));
    }
}
