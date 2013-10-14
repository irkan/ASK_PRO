package az.neuron.ask.util;

import az.neuron.ask.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 11/7/12
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class DateHelper {
    public static Date StrToDate(String strDate) {
        DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String DateToStr(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static Date StrToDateWithTime(String strDate) {
        DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String DateToStrWithTime(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
