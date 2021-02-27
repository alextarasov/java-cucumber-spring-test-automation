package generalfunctionality.mysqlwordpressdbjdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WordpressDbJdbcTemplate implements WordpressDbDAO {
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public List<Map<String, Object>> getWpComments() {
        String SQL = "SELECT * FROM wp_comments ORDER By comment_ID DESC";
        return jdbcTemplateObject.queryForList(SQL);
    }

    public Map<String, String> getFirstWpCommentAsAString() {
        return getWpComments().get(0).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().toString()));
    }

    public List<Map<String, Object>> getWpPosts() {
        String SQL = "SELECT * FROM wp_posts ORDER By ID DESC";
        return jdbcTemplateObject.queryForList(SQL);
    }

    public Map<String, String> getFirstWpPostAsAString() {
        return getWpPosts().get(0).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().toString()));
    }

    public int deleteByCommentAuthorEmail(String CommentAuthorEmail) {
        String SQL = "DELETE FROM wp_comments where comment_author_email = ?";
        return jdbcTemplateObject.update(SQL, CommentAuthorEmail);
    }
}
