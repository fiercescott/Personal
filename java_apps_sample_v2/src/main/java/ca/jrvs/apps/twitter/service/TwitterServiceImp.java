package ca.jrvs.apps.twitter.service;

import static ca.jrvs.apps.twitter.util.TweetUtil.buildTweet;
import static ca.jrvs.apps.twitter.util.TweetUtil.validId;
import static ca.jrvs.apps.twitter.util.TweetUtil.validatePostTweet;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitterServiceImp implements TwitterService {

  private CrdDao dao;

  @Autowired
  public TwitterServiceImp(CrdDao dao) {
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(String text, Double latitude, Double longitude) {
    //Build request tweet
    Tweet postTweet = buildTweet(text, longitude, latitude);
    //Validate Tweet (can be combined with build Tweet method
    validatePostTweet(postTweet);
    try {
      Tweet responseTweet = (Tweet) dao.create(postTweet);
      printTweet(responseTweet);
    } catch (Exception e) {
      throw new RuntimeException("Failed to post tweet");
    }
    return postTweet;
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    Tweet tweet;

    //Validate id
    if (!validId.test(id)) {
      throw new IllegalArgumentException("ID must be number");
    }

    //Make Get Tweet and print
    try {
      tweet = (Tweet) dao.findById(id);
      printTweet(selectFields(tweet, fields));
    } catch (IOException e) {
      throw new RuntimeException("Failed to show tweet");
    }
    return tweet;
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> tweets = new ArrayList<>();
    for (String id : ids) {
      validId.test(id);
      Tweet tweet = (Tweet) dao.deleteById(id);
      printTweet(tweet);
      tweets.add(tweet);
    }
    return tweets;
  }

  private void printTweet(Tweet tweet) {
    try {
      System.out.println(JsonUtil.toPrettyJson(tweet));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert tweet object to string", e);
    }
  }

  /**
   *
   *   Write unit test first
   *
   *   Sample setter in Tweet Class:
   *   `@JsonProperty("created_at")`
   *   `public void setCreatedAt(String createdAt)`
   *
   *   First approach
   *   for(method: tweet.getAllMethod)
   *     if method is a setter
   *       pValue = method.jsonProperty.value
   *       if pValue in fields
   *           skip
   *       else
   *         invoke setter with null
   *
   *   Issue1: invalid fields
   *   Issue2: original tweet object is changed
   *
   *   Better approach:
   *   rTweet = deep copy of tweet
   *
   *   //Make as hashSet for faster lookup and removal
   *   //hashSet is also used to check invalid fields
   *   fieldsSet = new HashSet(fields)
   *
   *   for(method: Tweet.getMethods)
   *     if method.name.prefix == "set"
   *       if fieldSet contains JsonProperty.value
   *         remove field from fieldSet
   *       else
   *         invoke rTweet setter with null argument
   *
   *   if fieldSet not empty
   *     throw invalid fields exception
   *
   *   return rTweet
   */

  /**
   * Select fields in tweet object by setting other fields to null
   *
   * @param tweet tweet object
   * @param fields fields to be selected
   */
  protected Tweet selectFields(Tweet tweet, String[] fields) throws IOException {
    if (fields == null || fields.length == 0) {
      return tweet;
    }
    //rTweet = deep copy of tweet
    Tweet rTweet = JsonUtil.toObjectFromJson(JsonUtil.toPrettyJson(tweet), Tweet.class);

    //helper lambda function to remove leading and trailing spaces
    Function<String[], String[]> trimStrArray = (items) -> Arrays.stream(items).map(String::trim)
        .toArray(String[]::new);
    //Make fieldSet for fast lookup and removal
    Set<String> fieldSet = new HashSet<>(Arrays.asList(trimStrArray.apply(fields)));

    /**
     * for(method: Tweet.getMethods)
     *   if method.name.prefix == "set"
     *     if fieldSet contains JsonProperty.value
     *       remove field from fieldSet
     *     else
     *       invoke rTweet setter with null argument
     */
    Predicate<Method> isSetter = (method) -> method.getName().startsWith("set");
    Arrays.stream(Tweet.class.getMethods())
        .filter(isSetter)
        .forEach(setter ->
        {
          JsonProperty jsonProperty = setter.getDeclaredAnnotation(JsonProperty.class);
          if (jsonProperty == null || StringUtil.isEmpty(jsonProperty.value())) {
            throw new RuntimeException(
                "@JsonProperty is not defined for method" + setter.getName());
          }
          String value = jsonProperty.value();
          if (fieldSet.contains(value)) {
            fieldSet.remove(value);
          } else {
            try {
              setter.invoke(rTweet, new Object[]{null});
            } catch (IllegalAccessException | InvocationTargetException e) {
              throw new RuntimeException("unable to set setter:" + setter.getName(), e);
            }
          }
        });

    /**
     * if fieldSet not empty
     *   throw invalid fields exception
     */
    if (!fieldSet.isEmpty()) {
      String invalidFields = String.join(",", fieldSet);
      throw new RuntimeException("Found invalid select field(s):" + invalidFields);
    }
    return rTweet;
  }
}
