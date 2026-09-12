import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperClass extends Mapper<Object, Text, Text, DoubleWritable> {

    private final Text airline = new Text();
    private final DoubleWritable delay = new DoubleWritable();

    @Override
    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString().trim();

        // Skip header
        if (line.startsWith("Year,")) {
            return;
        }

        String[] fields = parseCSVLine(line);

        // Airline = column 6 (index 5)
        // DepDelay = column 15 (index 14)
        if (fields.length > 14) {

            try {
                String airlineName = fields[5].trim();
                String delayValue = fields[14].trim();

                if (!airlineName.isEmpty() && !delayValue.isEmpty()) {
                    airline.set(airlineName);
                    delay.set(Double.parseDouble(delayValue));

                    context.write(airline, delay);
                }

            } catch (NumberFormatException e) {
                // Ignore invalid delay values
            }
        }
    }

    private String[] parseCSVLine(String line) {

        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (c == '"') {
                insideQuotes = !insideQuotes;
            } 
            else if (c == ',' && !insideQuotes) {
                result.add(current.toString());
                current.setLength(0);
            } 
            else {
                current.append(c);
            }
        }

        result.add(current.toString());

        return result.toArray(new String[0]);
    }
}