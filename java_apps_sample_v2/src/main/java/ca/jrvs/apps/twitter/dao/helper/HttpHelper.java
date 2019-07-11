package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

public interface HttpHelper {

  HttpResponse httpPost(URI uri);

  HttpResponse httpPost(URI uri, StringEntity stringEntity);

  HttpResponse httpGet(URI uri);
}
