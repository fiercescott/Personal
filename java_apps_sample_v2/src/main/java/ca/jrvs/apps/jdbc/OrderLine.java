package ca.jrvs.apps.jdbc;

import java.math.BigDecimal;

public class OrderLine {

  private int quanitity;
  private String productCode;
  private String productName;
  private int produceSize;
  private String productVariety;
  private BigDecimal productPrice;

  public int getQuanitity() {
    return quanitity;
  }

  public void setQuanitity(int quanitity) {
    this.quanitity = quanitity;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getProduceSize() {
    return produceSize;
  }

  public void setProduceSize(int produceSize) {
    this.produceSize = produceSize;
  }

  public String getProductVariety() {
    return productVariety;
  }

  public void setProductVariety(String productVariety) {
    this.productVariety = productVariety;
  }

  public BigDecimal getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(BigDecimal productPrice) {
    this.productPrice = productPrice;
  }

  @Override
  public String toString() {
    return "OrderLine{" +
        "quanitity=" + quanitity +
        ", productCode='" + productCode + '\'' +
        ", productName='" + productName + '\'' +
        ", produceSize=" + produceSize +
        ", productVariety='" + productVariety + '\'' +
        ", productPrice=" + productPrice +
        '}';
  }
}
