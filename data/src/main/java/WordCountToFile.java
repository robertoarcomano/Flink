import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class WordCountToFile {

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.err.println("Uso: WordCountToFile <input-file>");
            return;
        }

        String inputPath = args[0];
        String outputPath = "/data/output_file.txt";

        // ambiente di esecuzione
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(2);

        // sorgente: file di testo
        DataSet<String> text = env.readTextFile(inputPath);

        // trasformazioni: wordcount
        DataSet<Tuple2<String, Integer>> counts = text
                .flatMap(new Tokenizer())
                .groupBy(0)
                .sum(1);

        // sink: scrittura su file
        counts.writeAsCsv(outputPath, "\n", " ");

        // esecuzione
        env.execute("Flink WordCount to File");
    }

    // FlatMap per tokenizzare le righe in parole
    public static final class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            // split su non-lettere
            String[] tokens = value.toLowerCase().split("\\W+");

            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<>(token, 1));
                }
            }
        }
    }
}
