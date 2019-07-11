package ca.jrvs.apps.twitter.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@Configuration
//@ComponentScan(value = "ca.jrvs.apps.twitter")
public class TwitterCLIComponentScan {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        TwitterCLIComponentScan.class);
    //TwitterCLIRunner runner = context.getBean(TwitterCLIRunner.class);
    //runner.run(args);
  }
}
