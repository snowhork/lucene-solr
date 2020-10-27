package org.apache.lucene.demo.snowhork.query;

import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class Demo {
    public static void main(String[] args) throws IOException {
        var demo = new Demo();
        demo.TermQuery("people");
        demo.TermQuery("notfound");
    }

    IndexReader reader;
    IndexSearcher searcher;

    Demo() throws IOException {
        reader = DirectoryReader.open(FSDirectory.open(Paths.get("./data/index")));
        searcher = new IndexSearcher(reader);
    }

    void TermQuery(String text) throws IOException {
        System.out.println("----" + text + "----");

        TermQuery q = new TermQuery(new Term("body", text));
        System.out.println(q);

        Weight weight = q.createWeight(searcher, ScoreMode.COMPLETE, 1f);

        for (LeafReaderContext leaf: reader.leaves()) {
            var scorer = weight.scorer(leaf);

            if (scorer == null) {
                System.out.println("No hit");
            } else {
                var it = scorer.iterator();
                while(it.nextDoc() != DocIdSetIterator.NO_MORE_DOCS) {
                    System.out.println("Hit: " + scorer.score());
                }
            }
        }

        var topdocs = searcher.search(q, 10);
        for (ScoreDoc scoreDoc : topdocs.scoreDocs) {
            System.out.println("Search: " + scoreDoc.score);
        }
    }
}
