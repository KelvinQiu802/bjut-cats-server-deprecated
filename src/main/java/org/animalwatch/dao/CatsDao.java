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
    private final Logger logger = LoggerFactory.getLogger(CatsDao.class);

    public void insertCat(Cat cat) {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO cats VALUES (?, ? ,? ,? ,?, ? ,? ,? ,?, ? ," +
                        "?, ?, ?, ?, ?, ?);")
        ) {
            st.setInt(1, cat.id());
            st.setString(2, cat.name());
            st.setString(3, cat.campus().toString());
            st.setString(4, cat.avatar());
            st.setString(5, cat.gender().toString());
            st.setString(6, cat.color());
            st.setString(7, cat.hair());
            st.setString(8, cat.neutered().toString());
            st.setString(9, cat.state().toString());
            st.setString(10, cat.description());
            st.setString(11, cat.birthday());
            st.setString(12, cat.adoptionDay());
            st.setString(13, cat.position());
            st.setDouble(14, cat.longitude());
            st.setDouble(15, cat.latitude());
            st.setInt(16, cat.orderWeight());
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to connect to the database or operation failed.", e);
        }
    }

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
