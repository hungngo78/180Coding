using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace soapWCFServiceLibrary
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService3" in both code and config file together.
    [ServiceContract(Namespace = "http://localhost:9999/TweetService2")]
    [ServiceKnownType(typeof(Tweet))]
    public interface IService3
    {
        [OperationContract]
        String Hello(string para);

        [OperationContract]
        IList<Tweet> GetTweets();

        [OperationContract]
        Tweet GetTweetByID(string tweetId);

        [OperationContract]
        IList<Tweet> CreateTweet(Tweet newTweet);

        [OperationContract]
        void UpdateTweet(Tweet updateTweet);

        [OperationContract]
        void DeleteTweet(string deleteTweetId);
    }
}
