package org.apache.maven.minor;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.ling.CoreAnnotations;

import edu.stanford.nlp.pipeline.CoreSentence;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JFrame;


public class Test extends JFrame{

    static ArrayList<Object> header;
    static ArrayList<ArrayList<Object>> csv_data = new ArrayList<>();

    public static void csv_file() throws Exception {
        String csv_file = "C:\\Users\\Piyush Gupta\\OneDrive\\Desktop\\Feedback_minor\\src\\main\\java\\org\\apache\\maven\\minor\\College_Feedback.csv";
        CSVReader file = new CSVReader(new FileReader(csv_file));
        String[] line;

        if ((line = file.readNext()) != null) {
            header = new ArrayList<>(Arrays.asList(line));
            csv_data.add(header);
        }

        while ((line = file.readNext()) != null) {
            ArrayList<Object> row = new ArrayList<>(Arrays.asList(line));
            csv_data.add(row);
        }

//        int row_count = csv_data.size();
//        int column_count = csv_data.get(1).size();

        //        System.out.println(csv_data);

//        for (ArrayList<Object> row : csv_data) {
//            for (Object data : row) {
//                System.out.print(data + "\t");
//            }
//            System.out.println();
//        }

    }

//     Method to analyze sentiment using Stanford CoreNLP

    public static String analyzeSentiment(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,parse,sentiment");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);

        String sentiment = "";
        for (CoreSentence sentence : document.sentences()) {
            sentiment = sentence.sentiment();
            System.out.println("Sentiment of the sentence: " + sentiment);
        }

        return sentiment;
    }

    public static void analyzeFeedback() {
        int positiveCount = 0, negativeCount = 0, neutralCount = 0;

        for (int i = 1; i < csv_data.size(); i++) {
            ArrayList<Object> row = csv_data.get(i);
            String feedback = row.get(0).toString(); // Assuming feedback is in the second column

            String sentiment = analyzeSentiment(feedback);

            switch (sentiment) {
                case "Positive":
                    positiveCount++;
                    break;

                case "Very positive":
                    positiveCount++;
                    break;
                case "Negative":
                    negativeCount++;
                    break;
                case "Very negative":
                    negativeCount++;
                    break;
                case "Neutral":
                    neutralCount++;
                    break;
            }
        }

        System.out.println("Positive Feedback: " + positiveCount);
        System.out.println("Negative Feedback: " + negativeCount);
        System.out.println("Neutral Feedback: " + neutralCount);
    }

    public Test(String title){
        super(title);

        DefaultCategoryDataset dataset  = new DefaultCategoryDataset();

        int[] count = new int[5];

        for(int i =0; i<csv_data.size();i++){
            ArrayList<Object> row = csv_data.get(i);
            String temp = row.get(1).toString();

            if(temp.equals("1")){
                count[0]++;
            }
            else if(temp.equals("2")){
                count[1]++;
            }
            else if(temp.equals("3")){
                count[2]++;
            }
            else if(temp.equals("4")){
                count[3]++;
            }
            else if(temp.equals("5")){
                count[4]++;
            }
        }

        dataset.addValue(count[0],"feedback", "1");
        dataset.addValue(count[1],"feedback", "2");
        dataset.addValue(count[2],"feedback", "3");
        dataset.addValue(count[3],"feedback", "4");
        dataset.addValue(count[4],"feedback", "5");

        JFreeChart chart = ChartFactory.createBarChart( (String)header.get(1),"Range Of Feedback", "Number", dataset, PlotOrientation.VERTICAL,true,true,false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }



    public static void main(String[] args) {
        try {
            csv_file();
//            analyzeFeedback(); // Call method to analyze feedback sentiment
        } catch (Exception e) {
            System.out.println("An exception is found: " + e);
        }

        analyzeFeedback();

        Test second_column_graph = new Test("Item Count Bar Chart");
        second_column_graph .setSize(800, 600);
        second_column_graph .setLocationRelativeTo(null);
        second_column_graph .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        second_column_graph .setVisible(true);

    }
}