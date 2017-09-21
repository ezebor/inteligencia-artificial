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

    private JLabel naturalSelectorTag;
    private JComboBox naturalSelector;

    private Plotter plotter = null;

    public MenuWindow() {
        super();
        setWindow();
        initializeComponents();
        setVisible(true);
    }

    private void setWindow() {
        this.setTitle("Inteligencia Artificial - Algoritmo genético - grupo 7");
        this.setSize(650, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {
        // Labels
        populationSizeTag = this.initializeLabel("Ingrese tamaño de la población (compras posibles)", 50, 20, 400, 25);
        timesToEvolveTag = this.initializeLabel("Ingrese el número de evoluciones (generaciones)", 50, 50, 400, 25);
        maxPriceTag = this.initializeLabel("Ingrese el máximo precio de un producto", 50, 80, 400, 25);
        maxQuantityPerProductTag = this.initializeLabel("Ingrese la máxima cantidad de items por producto", 50, 110, 400, 25);
        maxIdOfProductTag = this.initializeLabel("Ingrese la máxima cantidad de productos distintos", 50, 140, 400, 25);
        naturalSelectorTag = this.initializeLabel("Ingrese el método de selección", 50, 170, 400, 25);

        // Texts
        populationSizeText = this.initializeText("10000", 500, 20, 100, 25);
        timesToEvolveText = this.initializeText("10", 500, 50, 100, 25);
        maxPriceText = this.initializeText("1500", 500, 80, 100, 25);
        maxQuantityPerProductText = this.initializeText("5", 500, 110, 100, 25);
        maxIdOfProductText = this.initializeText("1000", 500, 140, 100, 25);

        summary = new JTextArea(15, 4);
        summary.setBounds(50, 260, 550, 300);
        summary.setEditable(false);
        summary.setLineWrap(true);
        summary.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(summary);

        // Combo
        naturalSelector = new JComboBox();
        naturalSelector.setBounds(500,170,100,25);
        this.add(naturalSelector);
        naturalSelector.addItem("Ruleta");
        naturalSelector.addItem("Torneo");

        // Button
        getFittestChromosomeButton = new JButton();
        getFittestChromosomeButton.setText("Calcular máximo descuento");
        getFittestChromosomeButton.setBounds(50, 210, 550, 30);
        getFittestChromosomeButton.addActionListener(this);
        this.add(getFittestChromosomeButton);
    }

    private JLabel initializeLabel(String content, int x, int y, int width, int height){
        JLabel label = new JLabel(content);
        label.setBounds(x, y, width, height);
        this.add(label);
        return label;
    }

    private JTextField initializeText(String content, int x, int y, int width, int height){
        JTextField text = new JTextField(content);
        text.setBounds(x, y, width, height);
        this.add(text);
        return text;
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
            String naturalSelectorItem = (String) naturalSelector.getSelectedItem();

            String answer = SummaryManager.getSummary(timesToEvolve, populationSize, maxQuantityPerProduct, maxPrice, maxIdOfProduct, naturalSelectorItem);
            summary.append(answer);

            plotter = new Plotter("Mejor individuo en cada evolución");
            plotter.pack();
            RefineryUtilities.centerFrameOnScreen(plotter);
        }catch (Exception ex){
            summary.setText("Verifique que los valores ingresados son numéricos");
        }
    }
}