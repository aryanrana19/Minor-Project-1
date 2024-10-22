import java.io.*;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

class CSVReader {

    // Method to read CSV and create dataset for chart
    public static DefaultCategoryDataset createDataset(String path) {
        String line = "";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Map to store crime count per date
        Map<String, Integer> crimeData = new HashMap<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            // Skip the header row (if any)
            br.readLine(); 

            while((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Extracting Date and Crime Description
                String date = values[0]; // Assuming the date is in the first column
                String crimeDescription = values[5]; // Assuming the crime description is in the 6th column

                // Counting crimes per date
                crimeData.put(date, crimeData.getOrDefault(date, 0) + 1);
            }

            // Add data to the dataset
            for (Map.Entry<String, Integer> entry : crimeData.entrySet()) {
                dataset.addValue(entry.getValue(), "Crimes", entry.getKey());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return dataset;
    }

    // Method to create and display the chart
    public static void createChart(DefaultCategoryDataset dataset) {
        // Create a bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Crimes by Date",    // Chart title
                "Date",              // X-axis Label
                "Number of Crimes",  // Y-axis Label
                dataset              // Dataset
        );

        // Display the chart in a JFrame
        JFrame frame = new JFrame("Crime Data Chart");
        ChartPanel chartPanel = new ChartPanel(barChart);
        frame.setContentPane(chartPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        String path = "D:/5th Sem/Minor Project/Java Prac/CSV TO Java/SacramentocrimeJanuary2006.csv";
        
        // Create the dataset
        DefaultCategoryDataset dataset = createDataset(path);
        
        // Create and display the chart
        createChart(dataset);
    }
}