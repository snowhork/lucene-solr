package org.apache.lucene.demo.snowhork.filter;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import java.io.IOException;
import java.io.StringReader;

public class Demo {
    public static void main(String[] args) throws IOException {
        var demo = new Demo();

        demo.filter();
    }

    private void filter() throws IOException {
        var tokenizer = new StandardTokenizer();
        tokenizer.setReader(new StringReader("I love you."));
        var filter = new LowerCaseFilter(tokenizer);

        filter.reset();
        while (filter.incrementToken()) {
            System.out.println(tokenizer);
        }
    }
}
