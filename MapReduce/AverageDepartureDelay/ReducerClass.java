import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    private final DoubleWritable result = new DoubleWritable();

    @Override
    public void reduce(Text key, Iterable<DoubleWritable> values, Context context)
            throws IOException, InterruptedException {

        double sum = 0;
        int count = 0;

        for (DoubleWritable value : values) {
            sum += value.get();
            count++;
        }

        if (count > 0) {
            result.set(sum / count);
            context.write(key, result);
        }
    }
}