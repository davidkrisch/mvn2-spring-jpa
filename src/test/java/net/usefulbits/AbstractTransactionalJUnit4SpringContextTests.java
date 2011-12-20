package net.usefulbits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * This class takes the place of the the same class within Spring.  It is
 * necessary because the Spring 3.1 class uses SimpleJdbcTemplate which
 * is deprecated.
 */
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AbstractTransactionalJUnit4SpringContextTests extends AbstractJUnit4SpringContextTests {

    protected JdbcTemplate jdbcTemplate;

    /**
     * Set the DataSource, typically provided via Dependency Injection.
     * @param dataSource the database for the JdbcTemplate
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Count the rows in the given table.
     * @param tableName table name to count rows in
     * @return the number of rows in the table
     */
    protected int countRowsInTable(String tableName) {
        return jdbcTemplate.queryForInt("SELECT COUNT(0) FROM " + tableName);
    }
}
