package generalfunctionality.mysqlwordpressdbjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WpPostsMapper implements RowMapper<WpPosts> {
    public WpPosts mapRow(ResultSet rs, int rowNum) throws SQLException {
        WpPosts wpPosts = new WpPosts();
        wpPosts.setPostTitle(rs.getString("post_title"));
        wpPosts.setPostContent(rs.getString("post_content"));
        return wpPosts;
    }
}
