using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace RestWCFServiceLibrary
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
    public class Service1 : IService1
    {
        private ManageTweet _businessLayerTweetService;

        public String Hello()
        {
            return "Vua phai thoi nha";
        }

        public Service1()
        {
            _businessLayerTweetService = new ManageTweet();
        }

        public IList<Tweet> GetTweets()
        {
            return _businessLayerTweetService.GetTweets();
        }

        public Tweet GetTweetByID(string tweetId)
        {
            int tweetIdParsedToInt;
            Int32.TryParse(tweetId, out tweetIdParsedToInt);

            return _businessLayerTweetService.GetTweetById(tweetIdParsedToInt);
        }

        public Tweet CreateTweet(Tweet newTweet)
        {
            _businessLayerTweetService.CreateTweet(newTweet);
            return newTweet;
        }

        public void UpdateTweet(Tweet updateTweet)
        {
            _businessLayerTweetService.UpdateTweet(updateTweet);
        }

        public void DeleteTweet(string deleteTweetId)
        {
            int deleteTweetIdParsedToInt;
            Int32.TryParse(deleteTweetId, out deleteTweetIdParsedToInt);

            _businessLayerTweetService.DeleteTweet(deleteTweetIdParsedToInt);
        }
    }
}
