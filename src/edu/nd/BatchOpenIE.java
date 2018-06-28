package edu.nd;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.simple.*;

import javax.print.DocFlavor;
import java.io.*;


public class BatchOpenIE {

    public static void main(String[] args) throws IOException {
        // write your code here
        if (args.length != 2) {
            String msg = '\n' + "Usage: java -jar BatchOpenIE [input file] [output file] " + '\n';
            throw new IOException(msg);
        }else{
            String in_file = args[0];
            String out_file = args[1];
            String line = "";

            try {
                BufferedReader in = new BufferedReader(new FileReader(in_file));
                line = in.readLine();
                int i = 1;
                BufferedWriter out = new BufferedWriter(new FileWriter(out_file));

                while (line != null) {
//                    System.out.println(line);

                    Document doc = new Document(line);

                    // Iterate over the sentences in the document
                    for (Sentence sent : doc.sentences()) {
                        // Iterate over the triples in the sentence
                        for (RelationTriple triple : sent.openieTriples()) {
                            // Print the triple
                            out.write(i + "\t" + triple.confidence + "\t" +
                                    triple.subjectLemmaGloss() + "\t" +
                                    triple.relationLemmaGloss() + "\t" +
                                    triple.objectLemmaGloss() + "\n");
//                            System.out.println(triple.confidence + "\t" +
//                                    triple.subjectLemmaGloss() + "\t" +
//                                    triple.relationLemmaGloss() + "\t" +
//                                    triple.objectLemmaGloss());
                        }
                    }
                    line = in.readLine();
                    i += 1;
                }
                out.close();
                System.out.println("OpenIE batch processing finished!");
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}