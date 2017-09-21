package com.inteligenciaartificial.maven;

import java.math.BigDecimal;

public class SummaryManager {

    public static String getSummary(int maxTimesOfEvolves, int populationSize, int maxQuantityOfProduct, double maxPrice, int maxIdOfProduct, String naturalSelectorItem){
        try{
            String summary;
            FittestChromosomeAnswer answer = new MaximumDiscountCalculator().calculateMaximumDiscount(maxTimesOfEvolves,
                    populationSize, maxQuantityOfProduct, maxPrice, maxIdOfProduct, naturalSelectorItem);
            summary = "El cromosoma elegido fue:\n";

            Double totalPrice1 = answer.getPriceTimesQuantity(answer.getPrice1(), answer.getQuantity1());
            Double totalPrice2 = answer.getPriceTimesQuantity(answer.getPrice2(), answer.getQuantity2());
            Double totalPrice3 = answer.getPriceTimesQuantity(answer.getPrice3(), answer.getQuantity3());
            Double totalPrice4 = answer.getPriceTimesQuantity(answer.getPrice4(), answer.getQuantity4());
            Double totalPrice5 = answer.getPriceTimesQuantity(answer.getPrice5(), answer.getQuantity5());
            Double subtotal = answer.roundToTwoDecimals(new BigDecimal(totalPrice1 + totalPrice2 + totalPrice3 + totalPrice4 + totalPrice5));
            Double totalDiscount = answer.roundToTwoDecimals(answer.getDiscount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            Double total = answer.roundToTwoDecimals(new BigDecimal(subtotal - totalDiscount));

            summary += "\nProducto\t\tCantidad\t\tPrecio Unitario\n";
            summary += "\n"+answer.getId1()+"\t\t"+answer.getQuantity1().longValue()+"\t\t$"+answer.roundToTwoDecimals(answer.getPrice1());
            summary += "\n"+answer.getId2()+"\t\t"+answer.getQuantity2().longValue()+"\t\t$"+answer.roundToTwoDecimals(answer.getPrice2());
            summary += "\n"+answer.getId3()+"\t\t"+answer.getQuantity3().longValue()+"\t\t$"+answer.roundToTwoDecimals(answer.getPrice3());
            summary += "\n"+answer.getId4()+"\t\t"+answer.getQuantity4().longValue()+"\t\t$"+answer.roundToTwoDecimals(answer.getPrice4());
            summary += "\n"+answer.getId5()+"\t\t"+answer.getQuantity5().longValue()+"\t\t$"+answer.roundToTwoDecimals(answer.getPrice5());
            summary += "\n\n**********************************************************************************";
            summary += "\nSubtotal: $" + subtotal + "\n";
            summary += "\nPromoción 1: " + answer.getPromo1();
            summary += "\nPromoción 2: " + answer.getPromo2();
            summary += "\nTotal descuento: $" + totalDiscount + "\n";
            summary += "\n**********************************************************************************";
            summary += "\nTotal: $" + total;

            return summary;
        }catch (Exception e){
            return "An error occurred: " + e.getMessage();
        }
    }
}
