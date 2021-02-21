package generalfunctionality.mysqlwordpressdbjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WpCommentsMapper implements RowMapper<WpComments> {
    public WpComments mapRow(ResultSet rs, int rowNum) throws SQLException {
        WpComments wpComments = new WpComments();
        wpComments.setCommentContent(rs.getString("comment_content"));
        wpComments.setCommentType(rs.getString("comment_type"));
        wpComments.setCommentParent(rs.getInt("comment_parent"));
        wpComments.setUserId(rs.getInt("user_id"));
        wpComments.setCommentAuthorEmail(rs
                .getString("comment_author_email"));
        return wpComments;
    }
}
