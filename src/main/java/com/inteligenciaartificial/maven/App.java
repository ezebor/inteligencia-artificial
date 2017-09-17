package com.inteligenciaartificial.maven;

import java.math.BigDecimal;

public class App {
    public static final int MAX_TIMES_OF_EVOLVES = 23;
    private static final int MAX_ID_OF_PRODUCT = 1000;
    private static final double MAX_PRICE = 1500;
    private static final int MAX_QUANTITY_OF_PRODUCT = 5;
    private static final int POPULATION_SIZE = 20000;

    public static void main( String[] args ) {
        try{
            FittestChromosomeAnswer answer = new MaximumDiscountCalculator().calculateMaximumDiscount(MAX_TIMES_OF_EVOLVES,
                    POPULATION_SIZE, MAX_QUANTITY_OF_PRODUCT, MAX_PRICE, MAX_ID_OF_PRODUCT);
            System.out.println("\n*************************************************************************");
            System.out.println("\nCromosoma elegido es:\n");

            Double totalPrice1 = answer.getPriceTimesQuantity(answer.getPrice1(), answer.getQuantity1());
            Double totalPrice2 = answer.getPriceTimesQuantity(answer.getPrice2(), answer.getQuantity2());
            Double totalPrice3 = answer.getPriceTimesQuantity(answer.getPrice3(), answer.getQuantity3());
            Double totalPrice4 = answer.getPriceTimesQuantity(answer.getPrice4(), answer.getQuantity4());
            Double totalPrice5 = answer.getPriceTimesQuantity(answer.getPrice5(), answer.getQuantity5());
            Double subtotal = answer.roundToTwoDecimals(new BigDecimal(totalPrice1 + totalPrice2 + totalPrice3 + totalPrice4 + totalPrice5));
            Double totalDiscount = answer.roundToTwoDecimals(answer.getDiscount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            Double total = answer.roundToTwoDecimals(new BigDecimal(subtotal - totalDiscount));

            System.out.println("Producto :" + answer.getId1() + " - cantidad: " + answer.getQuantity1().longValue() + " - precio unitario: $" + answer.roundToTwoDecimals(answer.getPrice1()) + " - precio total: $" + totalPrice1);
            System.out.println("Producto :" + answer.getId2() + " - cantidad: " + answer.getQuantity2().longValue() + " - precio unitario: $" + answer.roundToTwoDecimals(answer.getPrice2()) + " - precio total: $" + totalPrice2);
            System.out.println("Producto :" + answer.getId3() + " - cantidad: " + answer.getQuantity3().longValue() + " - precio unitario: $" + answer.roundToTwoDecimals(answer.getPrice3()) + " - precio total: $" + totalPrice3);
            System.out.println("Producto :" + answer.getId4() + " - cantidad: " + answer.getQuantity4().longValue() + " - precio unitario: $" + answer.roundToTwoDecimals(answer.getPrice4()) + " - precio total: $" + totalPrice4);
            System.out.println("Producto :" + answer.getId5() + " - cantidad: " + answer.getQuantity5().longValue() + " - precio unitario: $" + answer.roundToTwoDecimals(answer.getPrice5()) + " - precio total: $" + totalPrice5);
            System.out.println("\nSubtotal: $" + subtotal + "\n");
            System.out.println("Total descuento: $" + totalDiscount + " (promos " + answer.getPromo1() + " y " + answer.getPromo2() + ")\n");
            System.out.println("Total: $" + total);
        }catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
