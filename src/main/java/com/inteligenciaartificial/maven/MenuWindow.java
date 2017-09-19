package com.inteligenciaartificial.maven;

import org.jfree.ui.RefineryUtilities;
import org.jgap.Configuration;

import javax.swing.*;
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

    private JLabel summary;
    private JButton getFittestChromosomeButton;

    private Plotter plotter = null;

    public MenuWindow() {
        super();                    // usamos el contructor de la clase padre JFrame
        setWindow();                // configuramos la ventana
        initializeComponents();     // inicializamos los atributos o componentes
        setVisible(true);
    }

    private void setWindow() {
        this.setTitle("Inteligencia Artificial - Algoritmo genético - grupo 7");                   // colocamos titulo a la ventana
        this.setSize(650, 550);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }

    private void initializeComponents() {
        // Labels
        populationSizeTag = new JLabel();
        timesToEvolveTag = new JLabel();
        maxIdOfProductTag = new JLabel();
        maxPriceTag = new JLabel();
        maxQuantityPerProductTag = new JLabel();
        summary = new JLabel();

        populationSizeTag.setText("Ingrese tamaño de la población");    // colocamos un texto a la etiqueta
        populationSizeTag.setBounds(50, 50, 400, 25);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        this.add(populationSizeTag);

        timesToEvolveTag.setText("Ingrese el número de evoluciones (generaciones)");    // colocamos un texto a la etiqueta
        timesToEvolveTag.setBounds(50, 80, 400, 25);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        this.add(timesToEvolveTag);

        maxPriceTag.setText("Ingrese el máximo precio de productos");    // colocamos un texto a la etiqueta
        maxPriceTag.setBounds(50, 110, 400, 25);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        this.add(maxPriceTag);

        maxQuantityPerProductTag.setText("Ingrese la máxima cantidad de items por productos");    // colocamos un texto a la etiqueta
        maxQuantityPerProductTag.setBounds(50, 140, 400, 25);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        this.add(maxQuantityPerProductTag);

        maxIdOfProductTag.setText("Ingrese el máximo id de un producto");    // colocamos un texto a la etiqueta
        maxIdOfProductTag.setBounds(50, 170, 400, 25);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        this.add(maxIdOfProductTag);

        summary.setBounds(50, 120, 700, 500);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        this.add(summary);

        // Texts
        populationSizeText = new JTextField("20000");
        timesToEvolveText = new JTextField("23");
        maxIdOfProductText = new JTextField("1000");
        maxPriceText = new JTextField("1500");
        maxQuantityPerProductText = new JTextField("5");

        populationSizeText.setBounds(500, 50, 100, 25);   // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        this.add(populationSizeText);

        timesToEvolveText.setBounds(500, 80, 100, 25);   // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        this.add(timesToEvolveText);

        maxPriceText.setBounds(500, 110, 100, 25);   // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        this.add(maxPriceText);

        maxQuantityPerProductText.setBounds(500, 140, 100, 25);   // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        this.add(maxQuantityPerProductText);

        maxIdOfProductText.setBounds(500, 170, 100, 25);   // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        this.add(maxIdOfProductText);

        // Button
        getFittestChromosomeButton = new JButton();
        getFittestChromosomeButton.setText("Calcular máximo descuento");   // colocamos un texto al boton
        getFittestChromosomeButton.setBounds(50, 220, 550, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
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

        int populationSize = Integer.parseInt(populationSizeText.getText());
        int timesToEvolve = Integer.parseInt(timesToEvolveText.getText());
        double maxPrice = Double.parseDouble(maxPriceText.getText());
        int maxQuantityPerProduct = Integer.parseInt(maxQuantityPerProductText.getText());
        int maxIdOfProduct = Integer.parseInt(maxIdOfProductText.getText());

        String answer = SummaryManager.getSummary(timesToEvolve, populationSize, maxQuantityPerProduct, maxPrice, maxIdOfProduct);
        summary.setText("<html>" + answer.replaceAll("\n", "<br>"));

        plotter = new Plotter("Mejor individuo en cada evolución");
        plotter.pack();
        RefineryUtilities.centerFrameOnScreen(plotter);
    }
}