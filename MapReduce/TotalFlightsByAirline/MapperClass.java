import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final Text airline = new Text();
    private final IntWritable one = new IntWritable(1);

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString().trim();

        // Skip CSV header
        if (line.startsWith("year,month,day_of_month")) {
            return;
        }

        String[] fields = parseCSVLine(line);

        // Airline column = column 6 = index 5
        if (fields.length > 5) {
            airline.set(fields[5].trim());
            context.write(airline, one);
        }
    }

    // CSV parser that handles commas inside quotation marks
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