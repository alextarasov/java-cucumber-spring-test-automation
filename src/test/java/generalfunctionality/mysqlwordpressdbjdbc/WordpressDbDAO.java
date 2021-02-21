package generalfunctionality.mysqlwordpressdbjdbc;

import javax.sql.DataSource;

public interface WordpressDbDAO {
    void setDataSource(DataSource ds);
}
