package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "entities",
    "coordinates",
    "retweet_count",
    "favorite_count",
    "favorited",
    "retweeted"
})
public class Tweet {

  /**
   * {
   *    "created_at":"Mon Feb 18 21:24:39 +0000 2019",
   *    "id":1097607853932564480,
   *    "id_str":"1097607853932564480",
   *    "text":"test with loc223",
   *    "entities":{
   *       "hashtags":[
   *          {
   *             "text":"documentation",
   *             "indices":[
   *                211,
   *                225
   *             ]
   *          },
   *          {
   *             "text":"parsingJSON",
   *             "indices":[
   *                226,
   *                238
   *             ]
   *          },
   *          {
   *             "text":"GeoTagged",
   *             "indices":[
   *                239,
   *                249
   *             ]
   *          }
   *       ],
   *       "user_mentions":[
   *          {
   *             "name":"Twitter API",
   *             "indices":[
   *                4,
   *                15
   *             ],
   *             "screen_name":"twitterapi",
   *             "id":6253282,
   *             "id_str":"6253282"
   *          }
   *       ]
   *    },
   *    "coordinates":{
   *       "coordinates":[
   *          -75.14310264,
   *          40.05701649
   *       ],
   *       "type":"Point"
   *    },
   *    "retweet_count":0,
   *    "favorite_count":0,
   *    "favorited":false,
   *    "retweeted":false
   * }
   */

  @JsonProperty("created_at")
  private String createdAt;
  @JsonProperty("id")
  private Long id;
  @JsonProperty("id_str")
  private String idStr;
  @JsonProperty("text")
  private String text;
  @JsonProperty("entities")
  private Entities entities;
  @JsonProperty("coordinates")
  private Coordinates coordinates;
  @JsonProperty("retweet_count")
  private Long retweetCount;
  @JsonProperty("favorite_count")
  private Long favoriteCount;
  @JsonProperty("favorited")
  private Boolean favorited;
  @JsonProperty("retweeted")
  private Boolean retweeted;

  @JsonProperty("created_at")
  public String getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("created_at")
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Long id) {
    this.id = id;
  }

  @JsonProperty("id_str")
  public String getIdStr() {
    return idStr;
  }

  @JsonProperty("id_str")
  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  @JsonProperty("entities")
  public Entities getEntities() {
    return entities;
  }

  @JsonProperty("entities")
  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  @JsonProperty("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }

  @JsonProperty("coordinates")
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  @JsonProperty("retweet_count")
  public Long getRetweetCount() {
    return retweetCount;
  }

  @JsonProperty("retweet_count")
  public void setRetweetCount(Long retweetCount) {
    this.retweetCount = retweetCount;
  }

  @JsonProperty("favorite_count")
  public Long getFavoriteCount() {
    return favoriteCount;
  }

  @JsonProperty("favorite_count")
  public void setFavoriteCount(Long favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  @JsonProperty("favorited")
  public Boolean getFavorited() {
    return favorited;
  }

  @JsonProperty("favorited")
  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  @JsonProperty("retweeted")
  public Boolean getRetweeted() {
    return retweeted;
  }

  @JsonProperty("retweeted")
  public void setRetweeted(Boolean retweeted) {
    this.retweeted = retweeted;
  }

}
