package org.animalwatch.dao;

import org.animalwatch.model.CatImage;
import org.animalwatch.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CatImageDao {
    private final Logger logger = LoggerFactory.getLogger(CatImageDao.class);

    public void addImage(CatImage image) {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO images (openId, imageUrl, state) VALUES (?," +
                        " ?, ?)");
        ) {
            st.setString(1, image.openId());
            st.setString(2, image.imageUrl());
            st.setString(3, image.state().toString());
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not connect to db OR operation failed", e);
        }
    }
}
