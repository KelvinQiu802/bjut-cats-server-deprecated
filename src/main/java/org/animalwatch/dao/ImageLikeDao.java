package org.animalwatch.dao;

import org.animalwatch.model.ImageLike;
import org.animalwatch.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageLikeDao {
    private final Logger logger = LoggerFactory.getLogger(ImageLikeDao.class);

    public void addLike(ImageLike imageLike) {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO likes VALUES (?, ?);")
        ) {
            st.setString(1, imageLike.openId());
            st.setString(2, imageLike.imageUrl());
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not connect to DB OR add like", e);
        }
    }

    public List<ImageLike> getLikesByOpenId(String openId) {
        List<ImageLike> likes = new ArrayList<>();
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM likes WHERE openId = ?;");
        ) {
            st.setString(1, openId);
            try (
                    ResultSet rs = st.executeQuery()
            ) {
                while (rs.next()) {
                    likes.add(new ImageLike(rs.getString("openId"), rs.getString("imageUrl")));
                }
            } catch (SQLException e) {
                logger.error("Can not do the query", e);
            }
        } catch (SQLException e) {
            logger.error("Can not connect to DB", e);
        }
        return likes;
    }

    public List<ImageLike> getLikesByImageUrl(String imageUrl) {
        List<ImageLike> likes = new ArrayList<>();
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM likes WHERE imageUrl = ?;");
        ) {
            st.setString(1, imageUrl);
            try (
                    ResultSet rs = st.executeQuery()
            ) {
                while (rs.next()) {
                    likes.add(new ImageLike(rs.getString("openId"), rs.getString("imageUrl")));
                }
            } catch (SQLException e) {
                logger.error("Can not do the query", e);
            }
        } catch (SQLException e) {
            logger.error("Can not connect to DB", e);
        }
        return likes;
    }
}
