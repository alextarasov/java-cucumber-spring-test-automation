package generalfunctionality.mysqlwordpressdbjdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class WordpressDbJdbcTemplate implements WordpressDbDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public List<WpComments> listEverything() {
        String SQL = "SELECT * FROM wp_comments ORDER By comment_ID DESC";
        List<WpComments> entries = jdbcTemplateObject
                .query(SQL, new WpCommentsMapper());
        return entries;
    }

    public int deleteByCommentAuthorEmail(String CommentAuthorEmail) {
        String SQL = "DELETE FROM wp_comments where comment_author_email = ?";
        int result = jdbcTemplateObject.update(SQL, CommentAuthorEmail);
        return result;
    }

    public List<WpPosts> getPostCommentAndTitle() {
        String SQL = "SELECT * FROM wp_posts ORDER By ID DESC";
        List<WpPosts> entries = jdbcTemplateObject
                .query(SQL, new WpPostsMapper());
        return entries;
    }
}
