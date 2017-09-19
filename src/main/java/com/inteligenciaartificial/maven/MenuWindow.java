package com.inteligenciaartificial.maven;

import org.jfree.ui.RefineryUtilities;
import org.jgap.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame implements ActionListener {

    private JLabel timesToEvolveTag;
    private JTextField timesToEvolveText;
    private JLabel maxIdOfProductTag;
    private JTextField maxIdOfProductText;
    private JLabel maxPriceTag;
    private JTextField maxPriceText;
    private JLabel maxQuantityPerProductTag;
    private JTextField maxQuantityPerProductText;
    private JLabel populationSizeTag;
    private JTextField populationSizeText;

    private JTextArea summary;
    private JButton getFittestChromosomeButton;

    private Plotter plotter = null;

    public MenuWindow() {
        super();
        setWindow();
        initializeComponents();
        setVisible(true);
    }

    private void setWindow() {
        this.setTitle("Inteligencia Artificial - Algoritmo genético - grupo 7");
        this.setSize(650, 580);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {
        // Labels
        populationSizeTag = new JLabel();
        timesToEvolveTag = new JLabel();
        maxIdOfProductTag = new JLabel();
        maxPriceTag = new JLabel();
        maxQuantityPerProductTag = new JLabel();
        summary = new JTextArea(15, 4);

        populationSizeTag.setText("Ingrese tamaño de la población (compras posibles)");
        populationSizeTag.setBounds(50, 20, 400, 25);
        this.add(populationSizeTag);

        timesToEvolveTag.setText("Ingrese el número de evoluciones (generaciones)");
        timesToEvolveTag.setBounds(50, 50, 400, 25);
        this.add(timesToEvolveTag);

        maxPriceTag.setText("Ingrese el máximo precio de un producto");
        maxPriceTag.setBounds(50, 80, 400, 25);
        this.add(maxPriceTag);

        maxQuantityPerProductTag.setText("Ingrese la máxima cantidad de items por producto");
        maxQuantityPerProductTag.setBounds(50, 110, 400, 25);
        this.add(maxQuantityPerProductTag);

        maxIdOfProductTag.setText("Ingrese la máxima cantidad de productos distintos");
        maxIdOfProductTag.setBounds(50, 140, 400, 25);
        this.add(maxIdOfProductTag);

        // Texts
        populationSizeText = new JTextField("20000");
        timesToEvolveText = new JTextField("23");
        maxIdOfProductText = new JTextField("1000");
        maxPriceText = new JTextField("1500");
        maxQuantityPerProductText = new JTextField("5");

        populationSizeText.setBounds(500, 20, 100, 25);
        this.add(populationSizeText);

        timesToEvolveText.setBounds(500, 50, 100, 25);
        this.add(timesToEvolveText);


        maxPriceText.setBounds(500, 80, 100, 25);
        this.add(maxPriceText);

        maxQuantityPerProductText.setBounds(500, 110, 100, 25);
        this.add(maxQuantityPerProductText);

        maxIdOfProductText.setBounds(500, 140, 100, 25);
        this.add(maxIdOfProductText);

        summary.setBounds(50, 240, 550, 300);
        summary.setEditable(false);
        summary.setLineWrap(true);
        summary.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(summary);

        // Button
        getFittestChromosomeButton = new JButton();
        getFittestChromosomeButton.setText("Calcular máximo descuento");
        getFittestChromosomeButton.setBounds(50, 190, 550, 30);
        getFittestChromosomeButton.addActionListener(this);
        this.add(getFittestChromosomeButton);
    }

    public void actionPerformed(ActionEvent e) {
        // Initialize
        Configuration.reset();
        summary.setText("");
        if(plotter != null){
            plotter.setVisible(false);
        }

        try{
            int populationSize = Integer.parseInt(populationSizeText.getText());
            int timesToEvolve = Integer.parseInt(timesToEvolveText.getText());
            double maxPrice = Double.parseDouble(maxPriceText.getText());
            int maxQuantityPerProduct = Integer.parseInt(maxQuantityPerProductText.getText());
            int maxIdOfProduct = Integer.parseInt(maxIdOfProductText.getText());

            String answer = SummaryManager.getSummary(timesToEvolve, populationSize, maxQuantityPerProduct, maxPrice, maxIdOfProduct);
            summary.append(answer);

            plotter = new Plotter("Mejor individuo en cada evolución");
            plotter.pack();
            RefineryUtilities.centerFrameOnScreen(plotter);
        }catch (Exception ex){
            summary.setText("Verifique que los valores ingresados son numéricos");
        }
    }
}