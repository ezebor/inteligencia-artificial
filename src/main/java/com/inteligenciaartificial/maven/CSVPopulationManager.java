package com.inteligenciaartificial.maven;

import org.jgap.Genotype;

import java.io.PrintWriter;

public class CSVPopulationManager {

    public static final String CSV_FILE_NAME = "MejoresIndividuos.csv";

    public static void writeHeaders(PrintWriter writer){
        StringBuilder sb = new StringBuilder();
        sb.append("Número de evolución");
        sb.append(',');
        sb.append("Producto 1");
        sb.append(',');
        sb.append("Producto 2");
        sb.append(',');
        sb.append("Producto 3");
        sb.append(',');
        sb.append("Producto 4");
        sb.append(',');
        sb.append("Producto 5");
        sb.append(',');
        sb.append("Promoción 1");
        sb.append(',');
        sb.append("Promoción 2");
        sb.append(',');
        sb.append("Aptitud del cromosoma");
        sb.append('\n');
        writer.write(sb.toString());
    }

    public static void saveBestChromosomeIntoCSV(Integer numberOfEvolution, Genotype population, PrintWriter writer) throws Exception{
        FittestChromosomeAnswer fittestChromosomeAnswer = new FittestChromosomeAnswer(population.getFittestChromosome());
        StringBuilder sb = new StringBuilder();

        sb.append(numberOfEvolution);
        sb.append(',');
        sb.append("Id: " + fittestChromosomeAnswer.getId1() + " - cantidad: " + fittestChromosomeAnswer.getQuantity1().longValue() + " - precio: " + fittestChromosomeAnswer.getPrice1().longValue());
        sb.append(',');
        sb.append("Id: " + fittestChromosomeAnswer.getId2() + " - cantidad: " + fittestChromosomeAnswer.getQuantity2().longValue() + " - precio: " + fittestChromosomeAnswer.getPrice2().longValue());
        sb.append(',');
        sb.append("Id: " + fittestChromosomeAnswer.getId3() + " - cantidad: " + fittestChromosomeAnswer.getQuantity3().longValue() + " - precio: " + fittestChromosomeAnswer.getPrice3().longValue());
        sb.append(',');
        sb.append("Id: " + fittestChromosomeAnswer.getId4() + " - cantidad: " + fittestChromosomeAnswer.getQuantity4().longValue() + " - precio: " + fittestChromosomeAnswer.getPrice4().longValue());
        sb.append(',');
        sb.append("Id: " + fittestChromosomeAnswer.getId5() + " - cantidad: " + fittestChromosomeAnswer.getQuantity5().longValue() + " - precio: " + fittestChromosomeAnswer.getPrice5().longValue());
        sb.append(',');
        sb.append(fittestChromosomeAnswer.getPromo1());
        sb.append(',');
        sb.append(fittestChromosomeAnswer.getPromo2());
        sb.append(',');
        sb.append(fittestChromosomeAnswer.getDiscount().doubleValue());
        sb.append('\n');

        writer.write(sb.toString());
    }
}
