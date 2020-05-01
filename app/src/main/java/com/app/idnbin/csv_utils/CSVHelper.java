/*     IMPORTANT NOTICE:  This software and source code is owned
 * and licensed by Fintatech B.V., https://fintatech.com
 * Downloading, installing or otherwise using this software or source
 * code shall be made only under Fintatech License agreement. If you
 * do not granted Fintatech License agreement, you must promptly
 * delete the software, source code and all associated downloadable
 * materials and you must not use the software for any purpose
 * whatsoever. */
package com.app.idnbin.csv_utils;

import android.content.Context;

import com.app.idnbin.R;
import com.fintatech.tasdk.misc.TABar;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    public static List<TABar> getBars(Context context) {
        return getListBars(getDataFromRaw(context));
    }

    private static List<List<String>> getDataFromRaw(Context context) {

        InputStream inputStream = context.getResources().openRawResource(R.raw.aapl_history);
        CSVFile csvFile = new CSVFile(inputStream);
        return csvFile.read();
    }

    private static List<TABar> getListBars(List<List<String>> list) {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        ArrayList<TABar> taBars = new ArrayList<>();
        for (int i = list.size() -1; i >= 0; i--) { // Reverse data for AAPL symbol
            List<String> strings = list.get(i);
            TABar taBar = new TABar(
                    Float.valueOf(strings.get(1)),
                    Float.valueOf(strings.get(2)),
                    Float.valueOf(strings.get(3)),
                    Float.valueOf(strings.get(4)),
                    Long.valueOf(strings.get(5)));
            DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
            taBar.setDate(DateTime.parse(strings.get(0), fmt).toLocalDateTime().toDateTime());
            taBars.add(taBar);
        }
        return taBars;
    }
}
