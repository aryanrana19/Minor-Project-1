package org.apache.maven.minor;


import com.opencsv.CSVReader;
import java.io.FileReader;

import java.util.*;
import java.util.Arrays;

import com.opencsv.CSVReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

import java.io.IOException;

public class Main{


    static ArrayList<Object> header ;

    static ArrayList<ArrayList<Object>>   csv_data = new ArrayList<>();

    public static void csv_file() throws Exception{

        String csv_file = "C:\\Users\\Piyush Gupta\\OneDrive\\Desktop\\Feedback_minor\\src\\main\\java\\org\\apache\\maven\\minor\\College_Feedback.csv";
        CSVReader file = new CSVReader(new FileReader(csv_file));

        String[] line;



        if((line = file.readNext())!=null){
             header = new ArrayList<>(Arrays.asList(line));
             csv_data.add(header);
        }

        while((line = file.readNext())!=null){
            ArrayList<Object> row = new ArrayList<>(Arrays.asList(line));
            csv_data.add(row);

        }

        int row_count = csv_data.size();
        int column_count = csv_data.get(1).size();

        System.out.println(csv_data.get(0));

    }

    public static void main(String[] args) {

    try{
        csv_file();
    }
    catch (Exception e){
        System.out.println("A Exception is Found " + e);
    }


    }



//    public static ArrayList<String> removeStopWords(ArrayList<String> inputList) {
//
//        List<String> stopWord = new ArrayList<>(Arrays.asList("a", "an", "am", "i", "my", "the", "and", "or", "but", "is", "are", "was", "were", "in", "on", "at", "to", "with", "for", "of", "by", "about", "as", "this", "that", "these", "those", "from", "."));
//
//
//        ArrayList<String> result = new ArrayList<>();
//
//
//        for (String input : inputList) {
//            String[] inputSplit = input.split(" ");
//            ArrayList<String> finalString = new ArrayList<>();
//
//
//            for (String word : inputSplit) {
//                String lower = word.toLowerCase();
//                if (!stopWord.contains(lower)) {
//                    finalString.add(lower);
//                }
//            }
//
//
//            result.add(String.join(" ", finalString));
//        }
//
//        return result;
//    }


}