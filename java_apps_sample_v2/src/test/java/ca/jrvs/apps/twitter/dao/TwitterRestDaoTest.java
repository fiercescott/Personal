package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwitterRestDaoTest extends Object {

  private Tweet expectedTweet;
  private CrdDao dao;
  private String id;

  @Before
  public void setup() {
    System.out.println("Setup unit test");
    //setup a expectedTweet
    String tweetStr = "this is a test tweet" + System.currentTimeMillis();
    this.expectedTweet = new Tweet();
    expectedTweet.setText(tweetStr);

    //setup dao
    HttpHelper httpHelper = new ApacheHttpHelper();
    this.dao = new TwitterRestDao(httpHelper);
  }

  @After
  public void cleanup() {
    System.out.println("Deleting " + this.id);
    //remove tweet
    dao.deleteById(this.id);
  }

  @Test
  public void create() throws Exception {
    //prepare tweet text
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(Arrays.asList(50.0, 50.0));
    coordinates.setType("Point");
    expectedTweet.setCoordinates(coordinates);
    System.out.println(JsonUtil.toPrettyJson(expectedTweet));

    //call create method
    Tweet createTweet = (Tweet) dao.create(expectedTweet);
    System.out.println(JsonUtil.toPrettyJson(createTweet));
    //validate tweet object
    assertTweets(expectedTweet, createTweet);
    this.id = createTweet.getIdStr();

    Tweet showTweet = (Tweet) dao.findById(this.id);
    assertTweets(expectedTweet, showTweet);
  }

  public void assertTweets(Tweet expected, Tweet actual) {
    assertNotNull(actual);
    assertNotNull(actual.getIdStr());
    assertEquals(expected.getText(), actual.getText());
    assertEquals(expected.getCoordinates(), actual.getCoordinates());
  }
}