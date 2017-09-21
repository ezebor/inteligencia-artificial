package com.inteligenciaartificial.maven;

import org.jgap.*;
import org.jgap.impl.*;

import java.io.File;
import java.io.PrintWriter;

public class MaximumDiscountCalculator {

    private static final int MAX_ID_OF_PROMOTION = 4;

    public FittestChromosomeAnswer calculateMaximumDiscount(int maxTimesOfEvolves, int populationSize,
                                                            int maxQuantityOfProduct, double maxPrice,
                                                            int maxIdOfProduct, String naturalSelectorItem) throws Exception{

        Configuration configuration = new DefaultConfiguration();
        configuration.setPreservFittestIndividual(true);

        FitnessFunction calculateDiscount = new DiscountCalculatorFitnessFunction();
        configuration.setFitnessFunction(calculateDiscount);

        Gene[] genes = new Gene[17];
        for(int index = 0 ; index <= 12 ; index += 3){
            genes = generateProductGene(genes, configuration, index, maxQuantityOfProduct, maxPrice, maxIdOfProduct);
        }

        // Promo 1
        genes = generatePromotionGene(genes, configuration, 15);

        // Promo 2
        genes = generatePromotionGene(genes, configuration, 16);

        IChromosome chromosome = new Chromosome(configuration, genes);
        configuration.setSampleChromosome(chromosome);

        if(naturalSelectorItem.equals("Ruleta")){
            configuration.addNaturalSelector(new WeightedRouletteSelector(configuration), true);
        }else{
            configuration.addNaturalSelector(new TournamentSelector(configuration, 5, 0.3), false);
        }

        configuration.addGeneticOperator(new MutationOperator(configuration, 15));
        configuration.setPopulationSize(populationSize);
        Genotype population = Genotype.randomInitialGenotype(configuration);
        int i;
        PrintWriter writer = new PrintWriter(new File(CSVPopulationManager.CSV_FILE_NAME));
        CSVPopulationManager.writeHeaders(writer);
        for(i = 1; i <= maxTimesOfEvolves; i++){
            System.out.println("Número de evolución: " + i + " de " + maxTimesOfEvolves);
            population.evolve();
            CSVPopulationManager.saveBestChromosomeIntoCSV(i, population, writer);
        }
        writer.close();

        return new FittestChromosomeAnswer(population.getFittestChromosome());
    }

    private static Gene[] generateProductGene(Gene[] genes, Configuration configuration, int initialIndex, int maxQuantityOfProduct, double maxPrice, int maxIdOfProduct) throws Exception{
        genes[initialIndex] = new IntegerGene(configuration, 0, maxIdOfProduct);
        genes[initialIndex + 1] = new DoubleGene(configuration, 0, maxQuantityOfProduct);
        genes[initialIndex + 2] = new DoubleGene(configuration, 1, maxPrice);
        return genes;
    }

    private static Gene[] generatePromotionGene(Gene[] genes, Configuration configuration, int index) throws Exception{
        genes[index] = new IntegerGene(configuration, 0, MAX_ID_OF_PROMOTION);
        return genes;
    }
}
