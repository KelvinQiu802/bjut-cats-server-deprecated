package org.animalwatch.dao;

import org.animalwatch.model.Campus;
import org.animalwatch.model.CatImage;
import org.animalwatch.model.ImageState;
import org.animalwatch.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatImageDao {
    private final Logger logger = LoggerFactory.getLogger(CatImageDao.class);

    public void addImage(CatImage image) {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO images (openId, imageUrl, state, campus, " +
                        "catName) VALUES (?, ?, ?, ?, ?);");
        ) {
            st.setString(1, image.openId());
            st.setString(2, image.imageUrl());
            st.setString(3, image.state().toString());
            st.setString(4, image.campus().toString());
            st.setString(5, image.catName());
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not connect to db OR operation failed", e);
        }
    }

    public List<CatImage> getImagesByState(String state) {
        List<CatImage> images = new ArrayList<>();
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM images WHERE state = ?");
        ) {
            st.setString(1, state);
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    images.add(constructCatImage(rs));
                }
            } catch (SQLException e) {
                logger.error("Can not do the operation", e);
            }
        } catch (SQLException e) {
            logger.error("Can not connect to DB", e);
        }
        return images;
    }

    public void updateImage(CatImage image) {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("UPDATE images SET state = ?, campus = ?, catName = ? " +
                        "WHERE imageUrl = ?;");
        ) {
            st.setString(1, image.state().toString());
            st.setString(2, image.campus().toString());
            st.setString(3, image.catName());
            st.setString(4, image.imageUrl());
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not connect to db OR do the operation");
        }
    }

    private CatImage constructCatImage(ResultSet rs) throws SQLException {
        return new CatImage(rs.getString("openId"),
                rs.getString("imageUrl"),
                ImageState.valueOf(rs.getString("state")),
                Campus.valueOf(rs.getString("campus")),
                rs.getString("catName"));
    }
}
