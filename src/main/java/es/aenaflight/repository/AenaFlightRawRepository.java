package es.aenaflight.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:sql-postgresql.xml")
public class AenaFlightRawRepository {

    @Value("${aenaFlightRawLoadSkippedExists}")
    private String aenaFlightRawLoadSkippedExists;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AenaFlightRawRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isAenaFlightRawLoadSkippedExists() {
        return jdbcTemplate.queryForObject(aenaFlightRawLoadSkippedExists, Integer.class).equals(1);
    }
}
