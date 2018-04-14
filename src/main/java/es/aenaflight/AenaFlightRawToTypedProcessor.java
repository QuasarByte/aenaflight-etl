package es.aenaflight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AenaFlightRawToTypedProcessor implements ItemProcessor<AenaFlightRaw, AenaFlightTyped> {

    private static final Logger log = LoggerFactory.getLogger(AenaFlightRawToTypedProcessor.class);

    private static final LocalDate START_UNIX_DATE = LocalDate.of(1970, 1,1);
    private static final LocalTime START_UNIX_TIME = LocalTime.MIN;

    @Override
    public AenaFlightTyped process(AenaFlightRaw item) throws Exception {

        //'DD/MM/YY HH24:MI'
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uu HH:mm");

        //'DD/MM/YY'
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uu");

        //'DD/MM/YY'
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        AenaFlightTyped aenaFlightTyped = new AenaFlightTyped();

        aenaFlightTyped.setId(item.getId());

        try {
            aenaFlightTyped.setActArrDateTimeLt(LocalDateTime.parse(item.getActArrDateTimeLt(), dateTimeFormatter));
        } catch (DateTimeParseException dateTimeParseException) {
            aenaFlightTyped.setActArrDateTimeLt(null);
        }

        aenaFlightTyped.setAircraftNameScheduled(item.getAircraftNameScheduled());
        aenaFlightTyped.setArrAptNameEs(item.getArrAptNameEs());
        aenaFlightTyped.setArrAptCodeIata(item.getArrAptCodeIata());
        aenaFlightTyped.setBaggageInfo(item.getBaggageInfo());
        aenaFlightTyped.setCarrierAirlineNameEn(item.getCarrierAirlineNameEn());
        aenaFlightTyped.setCarrierIcaoCode(item.getCarrierIcaoCode());
        aenaFlightTyped.setCarrierNumber(item.getCarrierNumber());
        aenaFlightTyped.setCounter(item.getCounter());
        aenaFlightTyped.setDepAptNameEs(item.getDepAptNameEs());
        aenaFlightTyped.setDepAptCodeIata(item.getDepAptCodeIata());

        try {
            aenaFlightTyped.setEstArrDateTimeLt(LocalDateTime.parse(item.getEstArrDateTimeLt(), dateTimeFormatter));
        } catch (DateTimeParseException dateTimeParseException) {
            aenaFlightTyped.setEstArrDateTimeLt(null);
        }

        try {
            aenaFlightTyped.setEstDepDateTimeLt(LocalDateTime.parse(item.getEstDepDateTimeLt(), dateTimeFormatter));
        } catch (DateTimeParseException dateTimeParseException) {
            aenaFlightTyped.setEstDepDateTimeLt(null);
        }

        aenaFlightTyped.setFlightAirlineNameEn(item.getFlightAirlineNameEn());
        aenaFlightTyped.setFlightAirlineName(item.getFlightAirlineName());
        aenaFlightTyped.setFlightIcaoCode(item.getFlightIcaoCode());
        aenaFlightTyped.setFlightNumber(item.getFlightNumber());

        try {
            aenaFlightTyped.setFltLegSeqNo(Integer.valueOf(item.getFltLegSeqNo()));
        } catch (NumberFormatException e) {
            aenaFlightTyped.setFltLegSeqNo(null);
        }

        aenaFlightTyped.setGateInfo(item.getGateInfo());
        aenaFlightTyped.setLoungeInfo(item.getLoungeInfo());

        try {
            aenaFlightTyped.setSchdArrOnlyDateLt(LocalDate.parse(item.getSchdArrOnlyDateLt(), dateFormatter));
        } catch (DateTimeParseException dateTimeParseException) {
            aenaFlightTyped.setSchdArrOnlyDateLt(null);
        }

        try {
            aenaFlightTyped.setSchdArrOnlyTimeLt(LocalTime.parse(item.getSchdArrOnlyTimeLt(), timeFormatter));
        } catch (DateTimeParseException dateTimeParseException) {
            aenaFlightTyped.setSchdArrOnlyTimeLt(null);
        }

        if (aenaFlightTyped.getSchdArrOnlyDateLt() != null || aenaFlightTyped.getSchdArrOnlyTimeLt() != null) {
            aenaFlightTyped.setSchdArrLt(LocalDateTime.of(aenaFlightTyped.getSchdArrOnlyDateLt() != null ? aenaFlightTyped.getSchdArrOnlyDateLt() : START_UNIX_DATE,
                    aenaFlightTyped.getSchdArrOnlyTimeLt() != null ? aenaFlightTyped.getSchdArrOnlyTimeLt() : START_UNIX_TIME));
        }

        aenaFlightTyped.setSourceData(item.getSourceData());
        aenaFlightTyped.setStatusInfo(item.getStatusInfo());
        aenaFlightTyped.setTerminalInfo(item.getTerminalInfo());
        aenaFlightTyped.setArrTerminalInfo(item.getArrTerminalInfo());

        try {
            aenaFlightTyped.setCreatedAt(LocalDateTime.ofInstant(Instant.ofEpochSecond(item.getCreatedAt()), ZoneOffset.UTC));
        } catch (DateTimeException e) {
            aenaFlightTyped.setCreatedAt(null);
        }

        try {
            aenaFlightTyped.setActDepDateTimeLt(LocalDateTime.parse(item.getActDepDateTimeLt(), dateTimeFormatter));
        } catch (DateTimeParseException dateTimeParseException) {
            aenaFlightTyped.setActDepDateTimeLt(null);
        }

        try {
            aenaFlightTyped.setSchdDepOnlyDateLt(LocalDate.parse(item.getSchdDepOnlyDateLt(), dateFormatter));
        } catch (DateTimeParseException dateTimeParseException) {
            aenaFlightTyped.setSchdDepOnlyDateLt(null);
        }

        try {
            aenaFlightTyped.setSchdDepOnlyTimeLt(LocalTime.parse(item.getSchdDepOnlyTimeLt()));
        } catch (DateTimeParseException dateTimeParseException) {
            aenaFlightTyped.setSchdDepOnlyTimeLt(null);
        }

        if (aenaFlightTyped.getSchdDepOnlyDateLt() != null || aenaFlightTyped.getSchdDepOnlyTimeLt() != null) {
            aenaFlightTyped.setSchdDepLt(LocalDateTime.of(aenaFlightTyped.getSchdDepOnlyDateLt() != null ? aenaFlightTyped.getSchdDepOnlyDateLt() : START_UNIX_DATE,
                    aenaFlightTyped.getSchdDepOnlyTimeLt() != null ? aenaFlightTyped.getSchdDepOnlyTimeLt() : START_UNIX_TIME));
        }

        return aenaFlightTyped;
    }

}