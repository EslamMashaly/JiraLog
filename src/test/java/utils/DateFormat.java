package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public static String DateFormatter(String date1) throws ParseException {

        // Parse input date string
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = inputFormat.parse(date1);

        // Format date into the desired format
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(date);


    }
}
