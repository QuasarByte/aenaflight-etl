package es.aenaflight.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:sql-postgresql.xml")
public class AenaFlightTypedRepository {

    @Value("${aenaFlightTypedMaxId}")
    private String aenaFlightTypedMaxId;

    @Value("${insertIntoAenaFlightTypedFromAenaFlightRawSkeppedOnDBSide}")
    private String insertIntoAenaFlightTypedFromAenaFlightRawSkeppedOnDBSide;

    @Value("${insertIntoAenaFlightTypedFromAenaFlightRawDeltaOnDBSide}")
    private String insertIntoAenaFlightTypedFromAenaFlightRawDeltaOnDBSide;

    @Value("${insertIntoAenaFlightTypedFromAenaFlightRawFullOnDBSide}")
    private String insertIntoAenaFlightTypedFromAenaFlightRawFullOnDBSide;

    @Value("${aenaFlightRawAndTypedMaxAreEquals}")
    private String aenaFlightRawAndTypedMaxAreEquals;

    @Value("${truncateAenaFlightTyped}")
    private String truncateAenaFlightTypedSQL;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AenaFlightTypedRepository(JdbcTemplate jdbcTemplate,
                                     NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long getAenaFlightTypedMaxId() {
        return jdbcTemplate.queryForObject(aenaFlightTypedMaxId, Long.class);
    }

    public int insertIntoAenaFlightTypedFromAenaFlightRawSkeppedOnDBSide(Long limit) {
        SqlParameterSource limitParameter = new MapSqlParameterSource("limit", limit);
        return namedParameterJdbcTemplate.update(insertIntoAenaFlightTypedFromAenaFlightRawSkeppedOnDBSide, limitParameter);
    }

    public int insertIntoAenaFlightTypedFromAenaFlightRawDeltaOnDBSide(Long limit) {
        SqlParameterSource limitParameter = new MapSqlParameterSource("limit", limit);
        return namedParameterJdbcTemplate.update(insertIntoAenaFlightTypedFromAenaFlightRawDeltaOnDBSide, limitParameter);
    }

    public int insertIntoAenaFlightTypedFromAenaFlightRawFullOnDBSide() {
        return jdbcTemplate.update(insertIntoAenaFlightTypedFromAenaFlightRawFullOnDBSide);
    }

    public boolean isAenaFlightRawAndTypedMaxAreEquals() {
        return jdbcTemplate.queryForObject(aenaFlightRawAndTypedMaxAreEquals, Integer.class).equals(1);
    }

    public void truncateAenaFlightTyped() {
        jdbcTemplate.update(truncateAenaFlightTypedSQL);
    }
}

