package com.inteligenciaartificial.maven;

import org.jgap.IChromosome;

import java.math.BigDecimal;

public class FittestChromosomeAnswer {

    private Integer id1;
    private BigDecimal quantity1;
    private BigDecimal price1;
    private Integer id2;
    private BigDecimal quantity2;
    private BigDecimal price2;
    private Integer id3;
    private BigDecimal quantity3;
    private BigDecimal price3;
    private Integer id4;
    private BigDecimal quantity4;
    private BigDecimal price4;
    private Integer id5;
    private BigDecimal quantity5;
    private BigDecimal price5;
    private String promo1;
    private String promo2;

    private BigDecimal discount;

    public FittestChromosomeAnswer(IChromosome fittestChromosome){
        this.setId1((Integer) fittestChromosome.getGene(0).getAllele());
        this.setQuantity1(getDoubleGene(fittestChromosome, 1));
        this.setPrice1(getDoubleGene(fittestChromosome, 2));

        this.setId2((Integer) fittestChromosome.getGene(3).getAllele());
        this.setQuantity2(getDoubleGene(fittestChromosome, 4));
        this.setPrice2(getDoubleGene(fittestChromosome, 5));

        this.setId3((Integer) fittestChromosome.getGene(6).getAllele());
        this.setQuantity3(getDoubleGene(fittestChromosome, 7));
        this.setPrice3(getDoubleGene(fittestChromosome, 8));

        this.setId4((Integer) fittestChromosome.getGene(9).getAllele());
        this.setQuantity4(getDoubleGene(fittestChromosome, 10));
        this.setPrice4(getDoubleGene(fittestChromosome, 11));

        this.setId5((Integer) fittestChromosome.getGene(12).getAllele());
        this.setQuantity5(getDoubleGene(fittestChromosome, 13));
        this.setPrice5(getDoubleGene(fittestChromosome, 14));

        this.setPromo1(this.getPromotionName((Integer) fittestChromosome.getGene(15).getAllele()));
        this.setPromo2(this.getPromotionName((Integer) fittestChromosome.getGene(16).getAllele()));

        this.setDiscount(roundToTwoDecimals((new DiscountCalculatorFitnessFunction()).evaluate(fittestChromosome)));
    }

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public BigDecimal getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(BigDecimal quantity1) {
        this.quantity1 = quantity1;
    }

    public BigDecimal getPrice1() {
        return price1;
    }

    public void setPrice1(BigDecimal price1) {
        this.price1 = price1;
    }

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    public BigDecimal getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(BigDecimal quantity2) {
        this.quantity2 = quantity2;
    }

    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal price2) {
        this.price2 = price2;
    }

    public Integer getId3() {
        return id3;
    }

    public void setId3(Integer id3) {
        this.id3 = id3;
    }

    public BigDecimal getQuantity3() {
        return quantity3;
    }

    public void setQuantity3(BigDecimal quantity3) {
        this.quantity3 = quantity3;
    }

    public BigDecimal getPrice3() {
        return price3;
    }

    public void setPrice3(BigDecimal price3) {
        this.price3 = price3;
    }

    public Integer getId4() {
        return id4;
    }

    public void setId4(Integer id4) {
        this.id4 = id4;
    }

    public BigDecimal getQuantity4() {
        return quantity4;
    }

    public void setQuantity4(BigDecimal quantity4) {
        this.quantity4 = quantity4;
    }

    public BigDecimal getPrice4() {
        return price4;
    }

    public void setPrice4(BigDecimal price4) {
        this.price4 = price4;
    }

    public Integer getId5() {
        return id5;
    }

    public void setId5(Integer id5) {
        this.id5 = id5;
    }

    public BigDecimal getQuantity5() {
        return quantity5;
    }

    public void setQuantity5(BigDecimal quantity5) {
        this.quantity5 = quantity5;
    }

    public BigDecimal getPrice5() {
        return price5;
    }

    public void setPrice5(BigDecimal price5) {
        this.price5 = price5;
    }

    public String getPromo1() {
        return promo1;
    }

    public void setPromo1(String promo1) {
        this.promo1 = promo1;
    }

    public String getPromo2() {
        return promo2;
    }

    public void setPromo2(String promo2) {
        this.promo2 = promo2;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    private String getPromotionName(Integer promotion){
        String promotionName;
        switch (promotion){
            case 0:
                promotionName = "sin promo";
                break;
            case 1:
                promotionName = "2x1";
                break;
            case 2:
                promotionName = "3x2";
                break;
            case 3:
                promotionName = "20% off";
                break;
            default:
                promotionName = "";
                break;
        }
        return promotionName;
    }

    private BigDecimal roundToTwoDecimals(Double val){
        BigDecimal newVal = new BigDecimal(val);
        newVal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        return newVal;
    }

    private BigDecimal getDoubleGene(IChromosome fittestChromosome, Integer index){
        return roundToTwoDecimals((Double) fittestChromosome.getGene(index).getAllele());
    }

    public Double getPriceTimesQuantity(BigDecimal price, BigDecimal quantity){
        return roundToTwoDecimals(price.multiply(quantity));
    }

    public Double roundToTwoDecimals(BigDecimal val){
        return val.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }
}
