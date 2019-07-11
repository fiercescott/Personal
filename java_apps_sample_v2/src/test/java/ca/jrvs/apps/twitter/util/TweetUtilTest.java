package ca.jrvs.apps.twitter.util;

import static ca.jrvs.apps.twitter.util.TweetUtil.validId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TweetUtilTest {

  @Test
  public void validId() {
    String id = "123";
    assertTrue(validId.test(id));

    id = "ab";
    assertFalse(validId.test(id));

    id = "";
    assertFalse(validId.test(id));

    id = "123a";
    assertFalse(validId.test(id));
  }

  @Test
  public void validatePostTweet() {
    String hashTag = "#abc";
    String text = "@someone sometext " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    //sad path
    //Invalid lat
    lat = -91d;
    try {
      TweetUtil.validatePostTweet(text, lon, lat);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    //Invalid lon
    lat = 1d;
    lon = -191d;
    try {
      TweetUtil.validatePostTweet(text, lon, lat);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    //empty text
    lat = 1d;
    lon = -1d;
    try {
      TweetUtil.validatePostTweet("", lon, lat);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    //141 char
    String longStr = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    try {
      TweetUtil.validatePostTweet(longStr, lon, lat);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void validatePostTweet1() {
  }

  @Test
  public void buildTweet() {
  }
}