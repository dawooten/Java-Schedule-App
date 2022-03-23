package utilities;

import java.time.*;

/**
 * This is the TimeManager class that holds all of the timezone conversions and business hours conversion.
 * */
public class TimeManager {

    /**
     * LocalTimeToEastCoastTimeZone method. This method takes a local time and converts it to East Coast Time.
     * @param conversionTime time to be converted
     * @return currentEasternTime.toLocalDateTime() converted East Coast Time
     * */
    public static LocalDateTime LocalTimeToEastCoastTimeZone (LocalDateTime conversionTime) {

        ZoneId easternTime = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();
        LocalDateTime localTime = conversionTime;
        ZonedDateTime currentLocalTime = localTime.atZone(localZone);
        ZonedDateTime currentEasternTime = currentLocalTime.withZoneSameInstant(easternTime);

        return currentEasternTime.toLocalDateTime();
    }

    /**
     * EastCoastTimeToUTCTimeZone method. This method takes East Coast time and converts it to UTC.
     * @param conversionTime time to be converted
     * @return utcZDT.toLocalDateTime() converted UTC
     * */
    public static LocalDateTime EastCoastTimeToUTCTimeZone (LocalDateTime conversionTime) {

        ZoneId eastCoastZoneID = ZoneId.of("America/New_York");
        ZoneId utcZoneID = ZoneId.of("UTC");
        ZonedDateTime eastCoastZDT = ZonedDateTime.of(conversionTime, eastCoastZoneID);
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(eastCoastZDT.toInstant(), utcZoneID);
        return utcZDT.toLocalDateTime();
    }

    /**
     * UTCTimeToLocalTimeZone method. This method takes UTC and converts to a Local Time.
     * @param conversionTime time to be converted
     * @return myZDT.toLocalDateTime() converted Local Time
     * */
    public static LocalDateTime UTCTimeToLocalTimeZone (LocalDateTime conversionTime) {

        ZoneId myZoneID = ZoneId.systemDefault();
        ZoneId utcZoneID = ZoneId.of("UTC");
        ZonedDateTime myZDT = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), myZoneID);
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneID);
        myZDT = ZonedDateTime.ofInstant(utcZDT.toInstant(), myZoneID);
        return myZDT.toLocalDateTime();
    }

    /**
     * businessHours method. This method takes a time and verifies that against the business' start and close
     * East Coast times.
     * @param time time to be checked
     * @return compare.isAfter(LocalDateTime.from(estStartTime)) and compare.isBefore(LocalDateTime.from(estEndTime))
     * checked time against business hours; true/false
     * */
    public static boolean businessHours(LocalDateTime time) {

        LocalTime estStart = LocalTime.of(7, 59); //appts starting at 0800, use 0759 to check 0800 appts
        ZoneId estZoneID = ZoneId.of("America/New_York");
        ZonedDateTime estStartTime = ZonedDateTime.of(time.toLocalDate(), estStart, estZoneID);
        System.out.println("Business EST Start Time: " + estStartTime + " " + estStartTime.toLocalDateTime());

        LocalTime estEnd = LocalTime.of(22, 00); //2200 valid, closed at 2200
        ZonedDateTime estEndTime = ZonedDateTime.of(time.toLocalDate(), estEnd, estZoneID);
        System.out.println("Business EST Close Time: " + estEndTime + " " + estEndTime.toLocalDateTime());

        LocalDateTime compare = LocalTimeToEastCoastTimeZone(time);
        System.out.println("Time Difference: " + compare);
        return compare.isAfter(LocalDateTime.from(estStartTime)) && compare.isBefore(LocalDateTime.from(estEndTime));
    }
}