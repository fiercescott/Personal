package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;

public class TwitterCLIApp {

  public static void main(String[] args) {
    //Create components
    HttpHelper httpHelper = new ApacheHttpHelper();
    CrdDao dao = new TwitterRestDao(httpHelper);
    TwitterService service = new TwitterServiceImp(dao);

    //Create Runner
    TwitterCLIRunner runner = new TwitterCLIRunner(service);

    //Run Application
    runner.run(args);
  }
}
