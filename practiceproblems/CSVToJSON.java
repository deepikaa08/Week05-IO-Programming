import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CSVToJSON {
    public static void main(String[] args) {
        try {
            File csvFile = new File("sample.csv");

            CsvMapper csvMapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();

            MappingIterator<Map<String, String>> it = csvMapper
                    .readerFor(Map.class)
                    .with(schema)
                    .readValues(csvFile);

            List<Map<String, String>> data = it.readAll();

            ObjectMapper jsonMapper = new ObjectMapper();
            String json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);

            System.out.println("Converted JSON:");
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
