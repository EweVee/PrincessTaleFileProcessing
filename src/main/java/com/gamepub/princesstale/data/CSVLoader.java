package com.gamepub.princesstale.data;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.input.BOMInputStream;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CSVLoader {
    public static <T> List<T> loadCSV(Class clazz, String resource) {
        try {
            final Reader reader = getReaderFromStream(clazz, resource);
            final CsvToBean<T> csvReader = new CsvToBeanBuilder(reader)
                    .withType(clazz)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

            final List<T> p = csvReader.parse();
            return p.subList(1, p.size());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static Reader getReaderFromStream(Class clazz, String resourcePath) {
        final InputStream stream = clazz.getResourceAsStream(resourcePath);
        final BOMInputStream bomInputStream = new BOMInputStream(stream);
        final Reader reader = new BufferedReader(new InputStreamReader(bomInputStream));
        return reader;
    }
}
