package com.inteligenciaartificial.maven;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Plotter extends JFrame {

    public Plotter(final String title) {
        super(title);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            BufferedReader in = new BufferedReader(new FileReader(CSVPopulationManager.CSV_FILE_NAME));
            in.readLine();
            String s;
            int i = 0;
            while ((s = in.readLine()) != null) {
                String[] a = s.split(",");
                dataset.addValue(Double.valueOf(a[8].trim()), "", (Comparable) i++);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        PlotOrientation orientation = PlotOrientation.VERTICAL;
        JFreeChart chart = ChartFactory.createBarChart(title, "Evoluciones", "Aptitudes", dataset, orientation, false, false, false);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        chartPanel.setEnforceFileExtensions(false);
        setContentPane(chartPanel);

        this.setDefaultCloseOperation(Plotter.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}