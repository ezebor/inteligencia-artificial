package com.inteligenciaartificial.maven;

import java.math.BigDecimal;

public class SummaryManager {

    public static String getSummary(int maxTimesOfEvolves, int populationSize, int maxQuantityOfProduct, double maxPrice, int maxIdOfProduct){
        try{
            String summary;
            FittestChromosomeAnswer answer = new MaximumDiscountCalculator().calculateMaximumDiscount(maxTimesOfEvolves,
                    populationSize, maxQuantityOfProduct, maxPrice, maxIdOfProduct);
            summary = "Cromosoma elegido es:\n";

            Double totalPrice1 = answer.getPriceTimesQuantity(answer.getPrice1(), answer.getQuantity1());
            Double totalPrice2 = answer.getPriceTimesQuantity(answer.getPrice2(), answer.getQuantity2());
            Double totalPrice3 = answer.getPriceTimesQuantity(answer.getPrice3(), answer.getQuantity3());
            Double totalPrice4 = answer.getPriceTimesQuantity(answer.getPrice4(), answer.getQuantity4());
            Double totalPrice5 = answer.getPriceTimesQuantity(answer.getPrice5(), answer.getQuantity5());
            Double subtotal = answer.roundToTwoDecimals(new BigDecimal(totalPrice1 + totalPrice2 + totalPrice3 + totalPrice4 + totalPrice5));
            Double totalDiscount = answer.roundToTwoDecimals(answer.getDiscount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            Double total = answer.roundToTwoDecimals(new BigDecimal(subtotal - totalDiscount));

            summary += "\nProducto :" + answer.getId1() + " - cantidad: " + answer.getQuantity1().longValue() + " - precio unitario: $" + answer.roundToTwoDecimals(answer.getPrice1()) + " - precio total: $" + totalPrice1;
            summary += "\nProducto :" + answer.getId2() + " - cantidad: " + answer.getQuantity2().longValue() + " - precio unitario: $" + answer.roundToTwoDecimals(answer.getPrice2()) + " - precio total: $" + totalPrice2;
            summary += "\nProducto :" + answer.getId3() + " - cantidad: " + answer.getQuantity3().longValue() + " - precio unitario: $" + answer.roundToTwoDecimals(answer.getPrice3()) + " - precio total: $" + totalPrice3;
            summary += "\nProducto :" + answer.getId4() + " - cantidad: " + answer.getQuantity4().longValue() + " - precio unitario: $" + answer.roundToTwoDecimals(answer.getPrice4()) + " - precio total: $" + totalPrice4;
            summary += "\nProducto :" + answer.getId5() + " - cantidad: " + answer.getQuantity5().longValue() + " - precio unitario: $" + answer.roundToTwoDecimals(answer.getPrice5()) + " - precio total: $" + totalPrice5;
            summary += "\n\nSubtotal: $" + subtotal + "\n";
            summary += "\nTotal descuento: $" + totalDiscount + " (promos " + answer.getPromo1() + " y " + answer.getPromo2() + ")\n";
            summary += "\nTotal: $" + total;

            return summary;
        }catch (Exception e){
            return "An error occurred: " + e.getMessage();
        }
    }
}
