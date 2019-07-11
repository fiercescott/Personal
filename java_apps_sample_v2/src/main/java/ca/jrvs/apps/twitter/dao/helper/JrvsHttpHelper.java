package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.util.StringUtil;
import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class JrvsHttpHelper implements HttpHelper {

  private OAuthConsumer consumer;
  private HttpClient httpClient;

  public JrvsHttpHelper(OAuthConsumer consumer, HttpClient httpClient) {
    this.consumer = consumer;
    this.httpClient = httpClient;
  }

  /**
   * Default constructor. Getting key and tokens from env
   */
  public JrvsHttpHelper() {
    System.out.println("This is a:" + JrvsHttpHelper.class.getSimpleName());

    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    if (StringUtil.isEmpty(consumerKey, consumerSecret, accessToken, tokenSecret)) {
      throw new RuntimeException("Unable to detect key and tokens from System env");
    }

    consumer = new CommonsHttpOAuthConsumer(consumerKey,
        consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    /**
     * Default = single connection. Discuss source code if time permit
     */
    httpClient = new DefaultHttpClient();
  }

  public JrvsHttpHelper(String consumerKey, String consumerSecret, String accessToken,
      String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey,
        consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    /**
     * Default = single connection. Discuss source code if time permit
     */
    httpClient = new DefaultHttpClient();
  }

  @Override
  public HttpResponse httpPost(URI uri) {
    try {
      return executeHttpRequest(HttpMethod.POST, uri, null);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("Failed to execute", e);
    }
  }

  @Override
  public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
    try {
      return executeHttpRequest(HttpMethod.POST, uri, stringEntity);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("Failed to execute", e);
    }
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    try {
      return executeHttpRequest(HttpMethod.GET, uri, null);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("Failed to execute", e);
    }
  }

  public HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity stringEntity)
      throws OAuthException, IOException {
    HttpEntityEnclosingRequestBase request = new HttpEntityEnclosingRequestBase() {
      @Override
      public String getMethod() {
        return method.name();
      }
    };

    request.setURI(uri);
    if (stringEntity != null) {
      request.setEntity(stringEntity);
    }

    consumer.sign(request);
    return httpClient.execute(request);
  }
}
