package com.inteligenciaartificial.maven;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.VerticalAlignment;

public class Plotter extends ApplicationFrame {

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
    }
}