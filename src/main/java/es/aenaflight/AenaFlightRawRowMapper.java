package es.aenaflight;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AenaFlightRawRowMapper implements RowMapper<AenaFlightRaw> {

    private static final String ID_COLUMN = "id";
    private static final String ACT_ARR_DATE_TIME_LT_COLUMN = "act_arr_date_time_lt";
    private static final String AIRCRAFT_NAME_SCHEDULED_COLUMN = "aircraft_name_scheduled";
    private static final String ARR_APT_NAME_ES_COLUMN = "arr_apt_name_es";
    private static final String ARR_APT_CODE_IATA_COLUMN = "arr_apt_code_iata";
    private static final String BAGGAGE_INFO_COLUMN = "baggage_info";
    private static final String CARRIER_AIRLINE_NAME_EN_COLUMN = "carrier_airline_name_en";
    private static final String CARRIER_ICAO_CODE_COLUMN = "carrier_icao_code";
    private static final String CARRIER_NUMBER_COLUMN = "carrier_number";
    private static final String COUNTER_COLUMN = "counter";
    private static final String DEP_APT_NAME_ES_COLUMN = "dep_apt_name_es";
    private static final String DEP_APT_CODE_IATA_COLUMN = "dep_apt_code_iata";
    private static final String EST_ARR_DATE_TIME_LT_COLUMN = "est_arr_date_time_lt";
    private static final String EST_DEP_DATE_TIME_LT_COLUMN = "est_dep_date_time_lt";
    private static final String FLIGHT_AIRLINE_NAME_EN_COLUMN = "flight_airline_name_en";
    private static final String FLIGHT_AIRLINE_NAME_COLUMN = "flight_airline_name";
    private static final String FLIGHT_ICAO_CODE_COLUMN = "flight_icao_code";
    private static final String FLIGHT_NUMBER_COLUMN = "flight_number";
    private static final String FLT_LEG_SEQ_NO_COLUMN = "flt_leg_seq_no";
    private static final String GATE_INFO_COLUMN = "gate_info";
    private static final String LOUNGE_INFO_COLUMN = "lounge_info";
    private static final String SCHD_ARR_ONLY_DATE_LT_COLUMN = "schd_arr_only_date_lt";
    private static final String SCHD_ARR_ONLY_TIME_LT_COLUMN = "schd_arr_only_time_lt";
    private static final String SOURCE_DATA_COLUMN = "source_data";
    private static final String STATUS_INFO_COLUMN = "status_info";
    private static final String TERMINAL_INFO_COLUMN = "terminal_info";
    private static final String ARR_TERMINAL_INFO_COLUMN = "arr_terminal_info";
    private static final String CREATED_AT_COLUMN = "created_at";
    private static final String ACT_DEP_DATE_TIME_LT_COLUMN = "act_dep_date_time_lt";
    private static final String SCHD_DEP_ONLY_DATE_LT_COLUMN = "schd_dep_only_date_lt";
    private static final String SCHD_DEP_ONLY_TIME_LT_COLUMN = "schd_dep_only_time_lt";

    public AenaFlightRaw mapRow(ResultSet rs, int rowNum) throws SQLException {
        AenaFlightRaw customerCredit = new AenaFlightRaw();

        customerCredit.setId(rs.getLong(ID_COLUMN));

        customerCredit.setActArrDateTimeLt(rs.getString(ACT_ARR_DATE_TIME_LT_COLUMN));
        customerCredit.setAircraftNameScheduled(rs.getString(AIRCRAFT_NAME_SCHEDULED_COLUMN));
        customerCredit.setArrAptNameEs(rs.getString(ARR_APT_NAME_ES_COLUMN));
        customerCredit.setArrAptCodeIata(rs.getString(ARR_APT_CODE_IATA_COLUMN));
        customerCredit.setBaggageInfo(rs.getString(BAGGAGE_INFO_COLUMN));
        customerCredit.setCarrierAirlineNameEn(rs.getString(CARRIER_AIRLINE_NAME_EN_COLUMN));
        customerCredit.setCarrierIcaoCode(rs.getString(CARRIER_ICAO_CODE_COLUMN));
        customerCredit.setCarrierNumber(rs.getString(CARRIER_NUMBER_COLUMN));
        customerCredit.setCounter(rs.getString(COUNTER_COLUMN));
        customerCredit.setDepAptNameEs(rs.getString(DEP_APT_NAME_ES_COLUMN));
        customerCredit.setDepAptCodeIata(rs.getString(DEP_APT_CODE_IATA_COLUMN));
        customerCredit.setEstArrDateTimeLt(rs.getString(EST_ARR_DATE_TIME_LT_COLUMN));
        customerCredit.setEstDepDateTimeLt(rs.getString(EST_DEP_DATE_TIME_LT_COLUMN));
        customerCredit.setFlightAirlineNameEn(rs.getString(FLIGHT_AIRLINE_NAME_EN_COLUMN));
        customerCredit.setFlightAirlineName(rs.getString(FLIGHT_AIRLINE_NAME_COLUMN));
        customerCredit.setFlightIcaoCode(rs.getString(FLIGHT_ICAO_CODE_COLUMN));
        customerCredit.setFlightNumber(rs.getString(FLIGHT_NUMBER_COLUMN));
        customerCredit.setFltLegSeqNo(rs.getString(FLT_LEG_SEQ_NO_COLUMN));
        customerCredit.setGateInfo(rs.getString(GATE_INFO_COLUMN));
        customerCredit.setLoungeInfo(rs.getString(LOUNGE_INFO_COLUMN));
        customerCredit.setSchdArrOnlyDateLt(rs.getString(SCHD_ARR_ONLY_DATE_LT_COLUMN));
        customerCredit.setSchdArrOnlyTimeLt(rs.getString(SCHD_ARR_ONLY_TIME_LT_COLUMN));
        customerCredit.setSourceData(rs.getString(SOURCE_DATA_COLUMN));
        customerCredit.setStatusInfo(rs.getString(STATUS_INFO_COLUMN));
        customerCredit.setTerminalInfo(rs.getString(TERMINAL_INFO_COLUMN));
        customerCredit.setArrTerminalInfo(rs.getString(ARR_TERMINAL_INFO_COLUMN));
        customerCredit.setCreatedAt(rs.getLong(CREATED_AT_COLUMN));
        customerCredit.setActDepDateTimeLt(rs.getString(ACT_DEP_DATE_TIME_LT_COLUMN));
        customerCredit.setSchdDepOnlyDateLt(rs.getString(SCHD_DEP_ONLY_DATE_LT_COLUMN));
        customerCredit.setSchdDepOnlyTimeLt(rs.getString(SCHD_DEP_ONLY_TIME_LT_COLUMN));

        return customerCredit;
    }
}