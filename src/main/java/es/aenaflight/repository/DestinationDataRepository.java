package es.aenaflight.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:sql-postgresql.xml")
public class DestinationDataRepository {

    @Value("${truncateDestinationData}")
    private String truncateDestinationDataSQL;

    @Value("${agregateAenaFlightVariant01}")
    private String agregateAenaFlightSQL;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DestinationDataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void truncateDestinationData() {
        jdbcTemplate.update(truncateDestinationDataSQL);
    }

    public void agregateAenaFlight() {
        jdbcTemplate.update(agregateAenaFlightSQL);
    }
}
