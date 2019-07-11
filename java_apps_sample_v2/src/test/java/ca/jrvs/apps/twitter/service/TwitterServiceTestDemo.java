package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceTestDemo {

  @InjectMocks
  private TwitterServiceImp service;
  @Mock
  private CrdDao mockDao;


  @Test
  public void postTweet() {

    //Replaced by @Mock @InjectMocks
/*    CrdDao mockDao = Mockito.mock(CrdDao.class);
    TwitterService service = new TwitterServiceImp(mockDao);*/
    Tweet mockTweet = new Tweet();
    mockTweet.setText("This is a fake tweet");
    when(mockDao.create(any())).thenReturn(mockTweet);

    service.postTweet("some tweet", 0.0, 0.0);

    try {
      service.postTweet("", 0.0, 0.0);
      fail();
    } catch (IllegalArgumentException e) {
      //e.printStackTrace();
    }
  }

  @Test
  public void showTweet() {

  }

  @Test
  public void deleteTweet() {

  }

}
