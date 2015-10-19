package controllers;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import data.CassandraClient;
import forms.AppointmentForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Iterator;

/**
 * Created by David on 2015-10-12.
 */
public class ServiceAppointmentController extends Controller {

    public Result appointmentSave() {

        // pull out the JSON and show it
        // write to the table from the JSON
        // no validation yet on the JSON??

        System.err.println("appointmentSave1");
        System.err.println("request().body() --- BEFORE");
        System.err.println(request().body());
        System.err.println("request().body() --- AFTER");
        AppointmentForm appointmentForm = Form.form(AppointmentForm.class).bindFromRequest().get();
        System.err.println(appointmentForm);
        System.err.println("appointmentSave2");

        CassandraClient cassandraClient = CassandraClient.getClient();
        System.err.println("appointmentSave3");
        try {
            ResultSet resultSet = cassandraClient.execute("select * from ute.service_requests where partitionid in ('1', '2') and recordtimestamp < dateof(now());");

            Iterator<Row> rows = resultSet.iterator();
            while (rows.hasNext()) {
                System.err.println(rows.next());
            }

        } finally {
            cassandraClient.close();
        }

        return ok("Saved appointment.");
    }
}
