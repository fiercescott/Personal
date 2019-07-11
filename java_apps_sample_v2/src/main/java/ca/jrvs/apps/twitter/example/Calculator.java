package ca.jrvs.apps.twitter.example;

public class Calculator {

  public int id = 0;

  public Calculator() {
    id += 1;
    System.out.println("Creating a new calculator " + id);
  }

  public int evaluate(String expression) {
    int sum = 0;
    for (String summand : expression.split("\\+")) {
      sum += Integer.valueOf(summand);
    }
    return sum;
  }
}
