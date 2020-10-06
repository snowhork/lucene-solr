package org.apache.lucene.demo.snowhork.analyze;

import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        var demo = new Demo();

        demo.analyze();
    }

    private void analyze() throws IOException {
        var analyzer = new StandardAnalyzer();
        var stream = analyzer.tokenStream("name", "I love you.");
        stream.reset();

        while (stream.incrementToken()) {
            System.out.println(stream);
        }
    }
}
