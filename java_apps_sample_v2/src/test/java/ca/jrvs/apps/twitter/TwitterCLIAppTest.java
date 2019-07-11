package ca.jrvs.apps.twitter;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doNothing;

import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TwitterCLIAppTest {

  private static final String USAGE = "USAGE";

  @Mock
  TwitterService service;

  @InjectMocks
  TwitterCLIRunner cli;


  @Test
  public void postTweet() {
    //happy path
    doNothing().when(service).postTweet(isA(String.class), isA(Double.class), isA(Double.class));
    String[] args = {"post", "text", "-1:1"};
    cli.postTweet(args);

    //sad path
    //missing location
    args = new String[]{"post", "text"};
    try {
      cli.postTweet(args);
      fail();
    } catch (RuntimeException e) {
      assertTrue(e.getMessage().contains(USAGE));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

    //invalid location
    args = new String[]{"post", "text", "abc"};
    try {
      cli.postTweet(args);
      fail();
    } catch (RuntimeException e) {
      assertTrue(e.getMessage().contains(USAGE));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

    //invalid location
    args = new String[]{"post", "text", "abc:123"};
    try {
      cli.postTweet(args);
      fail();
    } catch (RuntimeException e) {
      assertTrue(e.getMessage().contains(USAGE));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void showTweet() {
    //happy path
    doNothing().when(service).showTweet(isNotNull(), isNotNull());
    String[] args = {"show", "t_id", "abc"};
    cli.showTweet(args);

    args = new String[]{"show", "t_id", "abc,123"};
    cli.showTweet(args);

    args = new String[]{"show", "t_id"};
    cli.showTweet(args);

    //missing args
    args = new String[]{"show"};
    try {
      cli.postTweet(args);
      fail();
    } catch (RuntimeException e) {
      assertTrue(e.getMessage().contains(USAGE));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

    //invalid arg
    args = new String[]{"show", ""};
    try {
      cli.postTweet(args);
      fail();
    } catch (RuntimeException e) {
      assertTrue(e.getMessage().contains(USAGE));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

  }

  @Test
  public void deleteTweet() {
    //happy path
    doNothing().when(service).deleteTweets(isNotNull());
    String[] args = {"deleteTweets", "id1"};
    cli.deleteTweet(args);

    args = new String[]{"deleteTweets", "id1,id2"};
    cli.deleteTweet(args);

    //missing arg
    args = new String[]{"show", ""};
    try {
      cli.postTweet(args);
      fail();
    } catch (RuntimeException e) {
      assertTrue(e.getMessage().contains(USAGE));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }
}