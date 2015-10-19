package forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by David on 2015-10-12.
 */
public class AppointmentForm  {

    public long accountNumber;
    public long callbackNumber;
    public String name;
    // yyyy-MM-dd'T'HH:mm:ss.SSSZ
    //
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    public Date startTime;

    @Override
    public String toString() {

        System.err.println(new Date());
        return "AppointmentForm{" +
                "accountNumber=" + accountNumber +
                ", callbackNumber=" + callbackNumber +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
