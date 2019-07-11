package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceImpTest {

  private final String tweetStr = "{\n"
      + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
      + "   \"id\":1097607853932564480,\n"
      + "   \"id_str\":\"1097607853932564480\",\n"
      + "   \"text\":\"test with loc223\",\n"
      + "   \"entities\":{\n"
      + "      \"hashtags\":[\n"
      + "         {\n"
      + "            \"text\":\"documentation\",\n"
      + "            \"indices\":[\n"
      + "               211,\n"
      + "               225\n"
      + "            ]\n"
      + "         },\n"
      + "         {\n"
      + "            \"text\":\"parsingJSON\",\n"
      + "            \"indices\":[\n"
      + "               226,\n"
      + "               238\n"
      + "            ]\n"
      + "         },\n"
      + "         {\n"
      + "            \"text\":\"GeoTagged\",\n"
      + "            \"indices\":[\n"
      + "               239,\n"
      + "               249\n"
      + "            ]\n"
      + "         }\n"
      + "      ],\n"
      + "      \"user_mentions\":[\n"
      + "         {\n"
      + "            \"name\":\"Twitter API\",\n"
      + "            \"indices\":[\n"
      + "               4,\n"
      + "               15\n"
      + "            ],\n"
      + "            \"screen_name\":\"twitterapi\",\n"
      + "            \"id\":6253282,\n"
      + "            \"id_str\":\"6253282\"\n"
      + "         }\n"
      + "      ]\n"
      + "   },\n"
      + "   \"coordinates\":{\n"
      + "      \"coordinates\":[\n"
      + "         -75.14310264,\n"
      + "         40.05701649\n"
      + "      ],\n"
      + "      \"type\":\"Point\"\n"
      + "   },\n"
      + "   \"retweet_count\":0,\n"
      + "   \"favorite_count\":0,\n"
      + "   \"favorited\":false,\n"
      + "   \"retweeted\":false\n"
      + "}";

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterServiceImp service;

  @Test
  public void selectFields() throws Exception {
    Tweet tweet = JsonUtil.toObjectFromJson(tweetStr, Tweet.class);
    Tweet rTweet = service.selectFields(tweet, new String[]{"created_at", "id"});
    assertNotNull(rTweet.getCreatedAt());
    assertNotNull(rTweet.getId());
    assertNull(rTweet.getText());
    //original tweet object should not be changed. Thanks to deep copy.
    assertNotNull(tweet.getText());

    //test args with space e.g. "created_at, id"
    rTweet = service.selectFields(tweet, new String[]{"created_at", " id"});

    //sad path; invalid field
    try {
      service.selectFields(tweet, new String[]{"created_at", "id", "fake"});
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }
  }

  @Test
  public void postTweet() {
    when(dao.create(any())).thenReturn(new Tweet());
    service.postTweet("test", 50.0, 0.0);

  }

  @Test
  public void showTweet() {
  }

  @Test
  public void deleteTweets() {
  }
}