package org.animalwatch.dao;

import org.animalwatch.model.Cat;
import org.animalwatch.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatsDao {
    private Logger logger = LoggerFactory.getLogger(CatsDao.class);

    public List<Cat> getAllCats() {
        List<Cat> cats = new ArrayList<>();
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
}
