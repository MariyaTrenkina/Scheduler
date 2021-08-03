
package classes;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import static java.time.ZoneOffset.UTC;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *TimeConvert Class
 * @author Mariya.Trenkina
 */
public class TimeConvert {
    ZoneId LocalComputerTimeZone = ZoneId.systemDefault();
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * convert To EST time String
     * @param localDateTime localDateTime
     * @return convert To EST time String
     */
    public String convertToESTString(String localDateTime){
    LocalDateTime dateTime = LocalDateTime.parse(localDateTime, formatter);
    ZonedDateTime zonedDateTime = dateTime.atZone(LocalComputerTimeZone);
    Instant instantZonedDateTime = zonedDateTime.toInstant();
    LocalDateTime ESTDateTime = LocalDateTime.ofInstant(instantZonedDateTime, ZoneOffset.ofHours(-5));
    String ESTDateTimeFormatted = ESTDateTime.format(formatter);
        return ESTDateTimeFormatted;
    
    }

    /**
     *convert To UTC time String
     * @param localDateTime localDateTime
     * @return convert To UTC time String
     */
    public String convertToUTCString(String localDateTime){
    LocalDateTime dateTime = LocalDateTime.parse(localDateTime, formatter);
    ZonedDateTime zonedDateTime = dateTime.atZone(LocalComputerTimeZone);
    Instant instantZonedDateTime = zonedDateTime.toInstant();
    LocalDateTime UTCDateTime = LocalDateTime.ofInstant(instantZonedDateTime, ZoneOffset.UTC);
    String UTCDateTimeFormatted = UTCDateTime.format(formatter);
        return UTCDateTimeFormatted;
    }

    /**
     *convert To Local Time String
     * @param localDateTime localDateTime
     * @return convert To Local Time String
     */
    public String convertToLocalTimeString(String localDateTime){
    LocalDateTime dateTime = LocalDateTime.parse(localDateTime, formatter);
    ZonedDateTime zonedDateTime = dateTime.atZone(UTC);
    Instant instantZonedDateTime = zonedDateTime.toInstant();
    LocalDateTime UTCDateTime = LocalDateTime.ofInstant(instantZonedDateTime, ZoneOffset.systemDefault());
    String UTCDateTimeFormatted = UTCDateTime.format(formatter);
        return UTCDateTimeFormatted;
    }

    /**
     *convert localDateTime To Local Time String
     * @param localDateTime localDateTime
     * @return convert localDateTime To Local Time String
     */
    public String convertLDTToLocalTimeString(LocalDateTime localDateTime){
    ZonedDateTime zonedDateTime = localDateTime.atZone(UTC);
    Instant instantZonedDateTime = zonedDateTime.toInstant();
    LocalDateTime UTCDateTime = LocalDateTime.ofInstant(instantZonedDateTime, ZoneOffset.systemDefault());
    String UTCDateTimeFormatted = UTCDateTime.format(formatter);
        return UTCDateTimeFormatted;
    }

    /**
     *convert To UTC LocalDateTime
     * @param localDateTime localDateTime
     * @return convert To UTC LocalDateTime
     */
    public LocalDateTime convertToUTCLocalDateTime(String localDateTime){
    LocalDateTime dateTime = LocalDateTime.parse(localDateTime, formatter);
    ZonedDateTime zonedDateTime = dateTime.atZone(LocalComputerTimeZone);
    Instant instantZonedDateTime = zonedDateTime.toInstant();
    LocalDateTime UTCDateTime = LocalDateTime.ofInstant(instantZonedDateTime, ZoneOffset.UTC);
    
        return UTCDateTime;
    }

    /**
     *convert To EST LocalDateTime
     * @param localDateTime localDateTime
     * @return convert To EST LocalDateTime
     */
    public LocalDateTime convertToESTLocalDateTime(String localDateTime){
    LocalDateTime dateTime = LocalDateTime.parse(localDateTime, formatter);
    ZonedDateTime zonedDateTime = dateTime.atZone(LocalComputerTimeZone);
    Instant instantZonedDateTime = zonedDateTime.toInstant();
    LocalDateTime ESTDateTime = LocalDateTime.ofInstant(instantZonedDateTime, ZoneOffset.ofHours(-5));
    
        return ESTDateTime;
    
    }

    /**
     *convert To LocalDateTime to EST LocalDateTime
     * @param localDateTime localDateTime
     * @return convert To LocalDateTime to EST LocalDateTime
     */
    public LocalDateTime convertToLDTtoESTLocalDateTime(LocalDateTime localDateTime){
    
    ZonedDateTime zonedDateTime = localDateTime.atZone(LocalComputerTimeZone);
    Instant instantZonedDateTime = zonedDateTime.toInstant();
    LocalDateTime ESTDateTime = LocalDateTime.ofInstant(instantZonedDateTime, ZoneOffset.ofHours(-5));
    
        return ESTDateTime;
    
    }
    
}
