package javax0.blog.demo.throwable.v2;

import javax0.blog.demo.throwable.Counter;
import javax0.blog.demo.throwable.LineWtfCounter;

import java.io.FileNotFoundException;

public class FileWtfCounter implements Counter {
    private final FileReader fileReader;

    public FileWtfCounter(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public int count() throws FileNotFoundException {
        final var lines = fileReader.list();
        int sum = 0;
        int lineNr = 1;
        for (final var line : lines) {
            try {
                sum += new LineWtfCounter(line).count();
            }catch(LineEmpty le){
                throw new NumberedLineEmpty(lineNr,le);
            }
            lineNr ++;
        }
        return sum;
    }

}
