package org.animalwatch.dao;

import org.animalwatch.model.Article;
import org.animalwatch.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    private final Logger logger = LoggerFactory.getLogger(ArticleDao.class);

    public List<Article> getAllArticles() {
        List<Article> result = new ArrayList<>();
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM articles;");
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    Article article = new Article(rs.getString("title"),
                            rs.getString("subTitle"),
                            rs.getString("image"),
                            rs.getString("link"));
                    result.add(article);
                }
            } catch (SQLException e) {
                logger.error("Can not get all the articles");
            }
        } catch (SQLException e) {
            logger.error("Can not connect to DB", e);
        }
        return result;
    }
}
