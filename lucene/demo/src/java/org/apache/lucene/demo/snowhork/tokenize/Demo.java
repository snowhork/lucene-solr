package org.apache.lucene.demo.snowhork.tokenize;

import org.apache.lucene.analysis.standard.StandardTokenizer;

import java.io.IOException;
import java.io.StringReader;

public class Demo {
    public static void main(String[] args) throws IOException {
        var demo = new Demo();

        demo.tokenize();
    }

    private void tokenize() throws IOException {
        var tokenizer = new StandardTokenizer();
        tokenizer.setReader(new StringReader("I love you."));

        tokenizer.reset();
        while (tokenizer.incrementToken()) {
            System.out.println(tokenizer);
        }
    }
}
