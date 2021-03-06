package es.aenaflight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AenaFlightTyped {

    //id
    private Long id;
    //act_arr_date_time_lt
    private LocalDateTime actArrDateTimeLt;
    //aircraft_name_scheduled
    private String aircraftNameScheduled;
    //arr_apt_name_es
    private String arrAptNameEs;
    //arr_apt_code_iata
    private String arrAptCodeIata;
    //baggage_info
    private String baggageInfo;
    //carrier_airline_name_en
    private String carrierAirlineNameEn;
    //carrier_icao_code
    private String carrierIcaoCode;
    //carrier_number
    private String carrierNumber;
    //counter
    private String counter;
    //dep_apt_name_es
    private String depAptNameEs;
    //dep_apt_code_iata
    private String depAptCodeIata;
    //est_arr_date_time_lt
    private LocalDateTime estArrDateTimeLt;
    //est_dep_date_time_lt
    private LocalDateTime estDepDateTimeLt;
    //flight_airline_name_en
    private String flightAirlineNameEn;
    //flight_airline_name
    private String flightAirlineName;
    //flight_icao_code
    private String flightIcaoCode;
    //flight_number
    private String flightNumber;
    //flt_leg_seq_no
    private Integer fltLegSeqNo;
    //gate_info
    private String gateInfo;
    //lounge_info
    private String loungeInfo;
    //schd_arr_only_date_lt
    private LocalDate schdArrOnlyDateLt;
    //schd_arr_only_time_lt
    private LocalTime schdArrOnlyTimeLt;
    //schd_arr_lt
    private LocalDateTime schdArrLt;
    //source_data
    private String sourceData;
    //status_info
    private String statusInfo;
    //terminal_info
    private String terminalInfo;
    //arr_terminal_info
    private String arrTerminalInfo;
    //created_at
    private LocalDateTime createdAt;
    //act_dep_date_time_lt
    private LocalDateTime actDepDateTimeLt;
    //schd_dep_only_date_lt
    private LocalDate schdDepOnlyDateLt;
    //schd_dep_only_time_lt
    private LocalTime schdDepOnlyTimeLt;
    //schd_dep_lt
    private LocalDateTime schdDepLt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getActArrDateTimeLt() {
        return actArrDateTimeLt;
    }

    public void setActArrDateTimeLt(LocalDateTime actArrDateTimeLt) {
        this.actArrDateTimeLt = actArrDateTimeLt;
    }

    public String getAircraftNameScheduled() {
        return aircraftNameScheduled;
    }

    public void setAircraftNameScheduled(String aircraftNameScheduled) {
        this.aircraftNameScheduled = aircraftNameScheduled;
    }

    public String getArrAptNameEs() {
        return arrAptNameEs;
    }

    public void setArrAptNameEs(String arrAptNameEs) {
        this.arrAptNameEs = arrAptNameEs;
    }

    public String getArrAptCodeIata() {
        return arrAptCodeIata;
    }

    public void setArrAptCodeIata(String arrAptCodeIata) {
        this.arrAptCodeIata = arrAptCodeIata;
    }

    public String getBaggageInfo() {
        return baggageInfo;
    }

    public void setBaggageInfo(String baggageInfo) {
        this.baggageInfo = baggageInfo;
    }

    public String getCarrierAirlineNameEn() {
        return carrierAirlineNameEn;
    }

    public void setCarrierAirlineNameEn(String carrierAirlineNameEn) {
        this.carrierAirlineNameEn = carrierAirlineNameEn;
    }

    public String getCarrierIcaoCode() {
        return carrierIcaoCode;
    }

    public void setCarrierIcaoCode(String carrierIcaoCode) {
        this.carrierIcaoCode = carrierIcaoCode;
    }

    public String getCarrierNumber() {
        return carrierNumber;
    }

    public void setCarrierNumber(String carrierNumber) {
        this.carrierNumber = carrierNumber;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getDepAptNameEs() {
        return depAptNameEs;
    }

    public void setDepAptNameEs(String depAptNameEs) {
        this.depAptNameEs = depAptNameEs;
    }

    public String getDepAptCodeIata() {
        return depAptCodeIata;
    }

    public void setDepAptCodeIata(String depAptCodeIata) {
        this.depAptCodeIata = depAptCodeIata;
    }

    public LocalDateTime getEstArrDateTimeLt() {
        return estArrDateTimeLt;
    }

    public void setEstArrDateTimeLt(LocalDateTime estArrDateTimeLt) {
        this.estArrDateTimeLt = estArrDateTimeLt;
    }

    public LocalDateTime getEstDepDateTimeLt() {
        return estDepDateTimeLt;
    }

    public void setEstDepDateTimeLt(LocalDateTime estDepDateTimeLt) {
        this.estDepDateTimeLt = estDepDateTimeLt;
    }

    public String getFlightAirlineNameEn() {
        return flightAirlineNameEn;
    }

    public void setFlightAirlineNameEn(String flightAirlineNameEn) {
        this.flightAirlineNameEn = flightAirlineNameEn;
    }

    public String getFlightAirlineName() {
        return flightAirlineName;
    }

    public void setFlightAirlineName(String flightAirlineName) {
        this.flightAirlineName = flightAirlineName;
    }

    public String getFlightIcaoCode() {
        return flightIcaoCode;
    }

    public void setFlightIcaoCode(String flightIcaoCode) {
        this.flightIcaoCode = flightIcaoCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Integer getFltLegSeqNo() {
        return fltLegSeqNo;
    }

    public void setFltLegSeqNo(Integer fltLegSeqNo) {
        this.fltLegSeqNo = fltLegSeqNo;
    }

    public String getGateInfo() {
        return gateInfo;
    }

    public void setGateInfo(String gateInfo) {
        this.gateInfo = gateInfo;
    }

    public String getLoungeInfo() {
        return loungeInfo;
    }

    public void setLoungeInfo(String loungeInfo) {
        this.loungeInfo = loungeInfo;
    }

    public LocalDate getSchdArrOnlyDateLt() {
        return schdArrOnlyDateLt;
    }

    public void setSchdArrOnlyDateLt(LocalDate schdArrOnlyDateLt) {
        this.schdArrOnlyDateLt = schdArrOnlyDateLt;
    }

    public LocalTime getSchdArrOnlyTimeLt() {
        return schdArrOnlyTimeLt;
    }

    public void setSchdArrOnlyTimeLt(LocalTime schdArrOnlyTimeLt) {
        this.schdArrOnlyTimeLt = schdArrOnlyTimeLt;
    }

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getTerminalInfo() {
        return terminalInfo;
    }

    public void setTerminalInfo(String terminalInfo) {
        this.terminalInfo = terminalInfo;
    }

    public String getArrTerminalInfo() {
        return arrTerminalInfo;
    }

    public void setArrTerminalInfo(String arrTerminalInfo) {
        this.arrTerminalInfo = arrTerminalInfo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getActDepDateTimeLt() {
        return actDepDateTimeLt;
    }

    public void setActDepDateTimeLt(LocalDateTime actDepDateTimeLt) {
        this.actDepDateTimeLt = actDepDateTimeLt;
    }

    public LocalDate getSchdDepOnlyDateLt() {
        return schdDepOnlyDateLt;
    }

    public void setSchdDepOnlyDateLt(LocalDate schdDepOnlyDateLt) {
        this.schdDepOnlyDateLt = schdDepOnlyDateLt;
    }

    public LocalTime getSchdDepOnlyTimeLt() {
        return schdDepOnlyTimeLt;
    }

    public void setSchdDepOnlyTimeLt(LocalTime schdDepOnlyTimeLt) {
        this.schdDepOnlyTimeLt = schdDepOnlyTimeLt;
    }

    public LocalDateTime getSchdArrLt() {
        return schdArrLt;
    }

    public void setSchdArrLt(LocalDateTime schdArrLt) {
        this.schdArrLt = schdArrLt;
    }

    public LocalDateTime getSchdDepLt() {
        return schdDepLt;
    }

    public void setSchdDepLt(LocalDateTime schdDepLt) {
        this.schdDepLt = schdDepLt;
    }
}
