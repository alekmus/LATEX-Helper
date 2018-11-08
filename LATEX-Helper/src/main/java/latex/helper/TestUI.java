/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latex.helper;

import java.util.ArrayList;
import java.util.Arrays;
import latex.helper.domain.Header;
import latex.helper.domain.LTXCodeDoc;
import latex.helper.domain.ParagraphParser;

/**
 *
 * @author Aleksi
 */
public class TestUI {
    public static void main(String[] args){
        
        String txt = "It was the best of times, it was the worst of times, \n"
                + "it was the age of wisdom, it was the age of foolishness, \n"
                + "it was the epoch of belief, it was the epoch of incredulity, \n"
                + "it was the season of Light, it was the season of Darkness, \n"
                + "it was the spring of hope, it was the winter of despair, \n\t"
                + "we had everything before us, we had nothing before us, \n"
                + "we were all going direct to Heaven, we were all going direct \n"
                + "the other way—in short, the period was so far like \n"
                + "the present period, that some of its noisiest authorities \n"
                + "insisted on its being received, for good or for evil, \n"
                + "in the superlative degree of comparison only.";
        
        
        
        ArrayList<String> lista = new ArrayList();
        lista.add("\\documentclass[a4paper,12pt]{article}");
        Header head = new Header(lista);
        LTXCodeDoc parser = new LTXCodeDoc(head);
        parser.setText(txt);
        ParagraphParser pp = new ParagraphParser();
        parser.parse(Arrays.asList(pp));
        System.out.println(parser);
   
    }
}
