package sample;


import org.apache.camel.Body;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class Inserter {


    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_SQL = "insert into batch.sample(data) values(?)";

    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public void insert(@Body String msg)  {
        Object[] params = new Object[] { msg };
        int[] types = new int[] {Types.VARCHAR} ;
        int row = jdbcTemplate.update(INSERT_SQL, params, types);
        System.out.println(row + " row inserted.");

    }
}
