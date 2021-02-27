package generalfunctionality.mysqlwordpressdbjdbc;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class WordpressDbDataSource {
    private DataSource wordpressDbDataSource;

    public DataSource getWordPressDbDataSource() {
        return wordpressDbDataSource;
    }

    private void setWordPressDbDataSource(DataSource wordPressDbDataSource) {
        this.wordpressDbDataSource = wordPressDbDataSource;
    }

    public void setUpDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:8083/wordpress" +
                "?zeroDateTimeBehavior=convertToNull");
        dataSource.setUsername("wordpress");
        dataSource.setPassword("wordpress");
        setWordPressDbDataSource(dataSource);
    }
}
