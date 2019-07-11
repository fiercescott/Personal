package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.net.URI;
import org.junit.Before;
import org.junit.Test;

public class TwitterRestDaoIntTest {

  private HttpHelper helper;

  private TwitterRestDao dao;

  @Before
  public void setup() {
    helper = new ApacheHttpHelper();
    dao = new TwitterRestDao(helper);
  }

  @Test
  public void getPostUri() throws Exception {
    String expectedUri = "https://api.twitter.com/1.1/statuses/update.json?status=%40abc+text+%23hashtag+2&long=-2.0&lat=1.2";
    URI rUri = dao.getPostUri(TweetUtil.buildTweet("@abc text #hashtag 2", -2.0d, 1.2d));
    assertEquals(expectedUri, rUri.toString());
  }

  @Test
  public void getPost() throws Exception {
    String hashTag = "#abc";
    String text = "@someone sometext " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    Tweet tweet = dao.create(postTweet);
    System.out.println(JsonUtil.toPrettyJson(tweet));

    assertEquals(text, tweet.getText());

    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().size());
    assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));

    assertTrue(hashTag.contains(tweet.getEntities().getHashtags().get(0).getText()));

  }

  @Test
  public void getShowUri() throws Exception {
    String expectedUri = "https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672";
    URI rUri = dao.getShowUri("210462857140252672");
    assertEquals(expectedUri, rUri.toString());
  }

  @Test
  public void showTweet() throws Exception {
    String id = "210462857140252672";
    Tweet tweet = dao.findById(id);
    assertEquals(id, tweet.getIdStr());
    assertNotNull(tweet.getText());
  }

  @Test
  public void getDeleteUri() throws Exception {
    String expectedStr = "https://api.twitter.com/1.1/statuses/destroy/240854986559455234.json";
    URI rUri = dao.getDeleteUri("240854986559455234");
    assertEquals(expectedStr, rUri.toString());
  }

  @Test
  public void deleteTweet() throws Exception {
    Tweet postTweet = TweetUtil.buildTweet("@abc hello world #abc", 1d, -1d);
    Tweet tweet = dao.create(postTweet);
    String id = tweet.getIdStr();
    Tweet deleteTweet = dao.deleteById(id);
    assertEquals(id, deleteTweet.getIdStr());
  }

}