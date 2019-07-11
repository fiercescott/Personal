package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

public class ApacheHttpHelperIntTest {

  private HttpHelper httpHelper;

  @Before
  public void setup() {
    httpHelper = new ApacheHttpHelper();
  }

  @Test
  public void httpPost() throws Exception {
    String uri =
        "https://api.twitter.com/1.1/statuses/update.json?status=%40JustinTrudeau+Hello+world"
            + System.currentTimeMillis() + "+%23MeanwhileInCanada";
    HttpResponse response = httpHelper.httpPost(new URI(uri));
    assertNotNull(response);
    assertEquals(200, response.getStatusLine().getStatusCode());
  }

  @Test
  public void httpGet() throws Exception {
    String uri = "https://api.twitter.com/1.1/users/search.json?q=realDonaldTrump";
    HttpResponse response = httpHelper.httpGet(new URI(uri));
    assertNotNull(response);
    assertEquals(200, response.getStatusLine().getStatusCode());
  }
}