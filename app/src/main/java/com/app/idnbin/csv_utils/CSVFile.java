/*     IMPORTANT NOTICE:  This software and source code is owned
 * and licensed by Fintatech B.V., https://fintatech.com
 * Downloading, installing or otherwise using this software or source
 * code shall be made only under Fintatech License agreement. If you
 * do not granted Fintatech License agreement, you must promptly
 * delete the software, source code and all associated downloadable
 * materials and you must not use the software for any purpose
 * whatsoever. */
package com.app.idnbin.csv_utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CSVFile {
    private InputStream mInputStream;

    public CSVFile(InputStream inputStream) {
        mInputStream = inputStream;
    }

    public List<List<String>> read() {
        List<List<String>> resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                ArrayList<String> rowList = new ArrayList<>();
                for (int i = 0; i < row.length; i++) {
                    rowList.add(i, row[i]);
                }
                resultList.add(rowList);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } finally {
            try {
                mInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return resultList;
    }
}
