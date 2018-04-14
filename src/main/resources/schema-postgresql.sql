-- classpath:/org/springframework/batch/core/schema-drop-postgresql.sql
DROP TABLE  IF EXISTS BATCH_STEP_EXECUTION_CONTEXT;
DROP TABLE  IF EXISTS BATCH_JOB_EXECUTION_CONTEXT;
DROP TABLE  IF EXISTS BATCH_STEP_EXECUTION;
DROP TABLE  IF EXISTS BATCH_JOB_EXECUTION_PARAMS;
DROP TABLE  IF EXISTS BATCH_JOB_EXECUTION;
DROP TABLE  IF EXISTS BATCH_JOB_INSTANCE;

DROP SEQUENCE  IF EXISTS BATCH_STEP_EXECUTION_SEQ ;
DROP SEQUENCE  IF EXISTS BATCH_JOB_EXECUTION_SEQ ;
DROP SEQUENCE  IF EXISTS BATCH_JOB_SEQ ;

-- Aena-Flights DDL

DROP TABLE IF EXISTS destination_data;

CREATE TABLE destination_data
(
  id BIGSERIAL NOT NULL PRIMARY KEY,
  adep CHARACTER VARYING(8) NOT NULL,
  ades CHARACTER VARYING(8) NOT NULL,
  flight_code CHARACTER VARYING(8) NOT NULL,
  flight_number CHARACTER VARYING(8) NOT NULL,
  carrier_code CHARACTER VARYING(8),
  carrier_number CHARACTER VARYING(8),
  status_info CHARACTER VARYING(256) NOT NULL,
  schd_dep_lt TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  schd_arr_lt TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  est_dep_lt TIMESTAMP WITHOUT TIME ZONE,
  est_arr_lt TIMESTAMP WITHOUT TIME ZONE,
  act_dep_lt TIMESTAMP WITHOUT TIME ZONE,
  act_arr_lt TIMESTAMP WITHOUT TIME ZONE,
  flt_leg_seq_no INTEGER NOT NULL,
  aircraft_name_scheduled text,
  baggage_info TEXT,
  counter TEXT,
  gate_info TEXT,
  lounge_info TEXT,
  terminal_info TEXT,
  arr_terminal_info TEXT,
  source_data TEXT,
  created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

DROP INDEX IF EXISTS aenaflight_2017_01_typed_group_01;

DROP TABLE IF EXISTS aenaflight_2017_01_typed;

CREATE TABLE aenaflight_2017_01_typed
(
    id bigint NOT NULL,
    act_arr_date_time_lt TIMESTAMP WITHOUT TIME ZONE,
    aircraft_name_scheduled TEXT,
    arr_apt_name_es CHARACTER VARYING(128),
    arr_apt_code_iata character varying(8),
    baggage_info CHARACTER VARYING(128),
    carrier_airline_name_en CHARACTER VARYING(128),
    carrier_icao_code CHARACTER VARYING(8),
    carrier_number CHARACTER VARYING(8),
    counter CHARACTER VARYING(64),
    dep_apt_name_es CHARACTER VARYING(128),
    dep_apt_code_iata CHARACTER VARYING(8),
    est_arr_date_time_lt TIMESTAMP WITHOUT TIME ZONE,
    est_dep_date_time_lt TIMESTAMP WITHOUT TIME ZONE,
    flight_airline_name_en CHARACTER VARYING(128),
    flight_airline_name CHARACTER VARYING(128),
    flight_icao_code CHARACTER VARYING(8),
    flight_number CHARACTER VARYING(8),
    flt_leg_seq_no INT,
    gate_info CHARACTER VARYING(128),
    lounge_info CHARACTER VARYING(128),
    schd_arr_only_date_lt DATE,
    schd_arr_only_time_lt TIME,
    schd_arr_lt TIMESTAMP WITHOUT TIME ZONE,
	  source_data TEXT,
    status_info CHARACTER VARYING(128),
    terminal_info CHARACTER VARYING(128),
    arr_terminal_info CHARACTER VARYING(128),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    act_dep_date_time_lt TIMESTAMP WITHOUT TIME ZONE,
    schd_dep_only_date_lt DATE,
    schd_dep_only_time_lt TIME,
    schd_dep_lt TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_aenaflight_2017_01_typed PRIMARY KEY (id)
);

CREATE INDEX aenaflight_2017_01_typed_group_01
    ON aenaflight_2017_01_typed USING btree
    (arr_apt_code_iata ASC NULLS LAST,
	 dep_apt_code_iata ASC NULLS LAST,
	 flight_icao_code ASC NULLS LAST,
	 flight_number ASC NULLS LAST,
	 schd_dep_only_date_lt ASC NULLS LAST,
	 schd_dep_only_time_lt ASC NULLS LAST);
