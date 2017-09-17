package com.inteligenciaartificial.maven;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class DiscountCalculatorFitnessFunction extends FitnessFunction {

    private Double discount;

    public double evaluate(IChromosome chromosome){
        this.discount = 0.0;

        Integer promotion1 = (Integer) chromosome.getGene(15).getAllele();
        Integer promotion2 = (Integer) chromosome.getGene(16).getAllele();

        // The only combination allowed is 20% off, with 2x1 XOR 3x2
        if((promotion1.equals(0) && promotion2.equals(0))
                || promotion1.equals(promotion2)
                || (promotion1.equals(1) && promotion2.equals(2))
                || (promotion1.equals(2) && promotion2.equals(1))){
            return 0;
        }

        Map<Integer, List<Double>> pricesAndQuantitiesByProductId = this.getPrices(chromosome);
        this.applyPromotion(pricesAndQuantitiesByProductId, promotion1);
        this.applyPromotion(pricesAndQuantitiesByProductId, promotion2);

        return this.discount;
    }

    private Map<Integer, List<Double>> getPrices(IChromosome chromosome){
        Map<Integer, List<Double>> pricesAndQuantitiesByProductId = newHashMap();
        int id;
        for(int i = 0 ; i <= 12 ; i+=3){
            id = (Integer) chromosome.getGene(i).getAllele();
            // Only take prices from existing products (id not zero)
            if(id != 0){
                pricesAndQuantitiesByProductId.put(id, newArrayList((Double) chromosome.getGene(i + 1).getAllele(), (Double) chromosome.getGene(i + 2).getAllele()));
            }
        }
        return pricesAndQuantitiesByProductId;
    }

    private Map<Integer, List<Double>> applyPromotion(Map<Integer, List<Double>> pricesAndQuantitiesByProductId, Integer promotion){
        switch (promotion){
            case 1:
                // 2x1
                return this.applyPayNMinus1TakeN(pricesAndQuantitiesByProductId, 2);
            case 2:
                // 3x2
                return this.applyPayNMinus1TakeN(pricesAndQuantitiesByProductId, 3);
            case 3:
                // 20% off
                return this.apply20PercentOff(pricesAndQuantitiesByProductId);
            default:
                // Invalid promotion id: don't apply any promotion
                return pricesAndQuantitiesByProductId;
        }
    }

    private Map<Integer, List<Double>> applyPayNMinus1TakeN(Map<Integer, List<Double>> pricesAndQuantitiesByProductIdExpired, Integer n){
        Map<Integer, List<Double>> pricesAndQuantitiesByProductIdNew = newHashMap();
        for (Integer id : pricesAndQuantitiesByProductIdExpired.keySet()) {
            List<Double> priceAndQuantity = pricesAndQuantitiesByProductIdExpired.get(id);
            Double quantity = priceAndQuantity.get(0);
            Double quantityToDiscount = quantity % n == 0 ? (quantity / n) : (quantity - (quantity % n)) / n;
            Double price = priceAndQuantity.get(1);
            this.discount += price * quantityToDiscount.longValue();
            pricesAndQuantitiesByProductIdNew.put(id, newArrayList(quantity, price));
        }
        return pricesAndQuantitiesByProductIdNew;
    }

    private Map<Integer, List<Double>> apply20PercentOff(Map<Integer, List<Double>> pricesAndQuantitiesByProductIdExpired){
        Map<Integer, List<Double>> pricesAndQuantitiesByProductIdNew = newHashMap();
        for (Integer id : pricesAndQuantitiesByProductIdExpired.keySet()) {
            List<Double> priceAndQuantity = pricesAndQuantitiesByProductIdExpired.get(id);
            Double quantity = priceAndQuantity.get(0);
            Double price = priceAndQuantity.get(1);
            this.discount += price * quantity * 0.2;
            pricesAndQuantitiesByProductIdNew.put(id, newArrayList(quantity, price));
        }
        return pricesAndQuantitiesByProductIdNew;
    }
}
