http://localhost:7777/TweetService/Tweet/2
	"GetTweetByIDResult": {
        "Id": 2,
        "PostedBy": "Rahul Pawar",
        "Text": "To bad I dont have a Twitter Account."
    }

http://localhost:7777/TweetService/GetTweets
{
    "GetTweetsResult": [
        {
            "Id": 1,
            "PostedBy": "Rahul Pawar",
            "Text": "This is my First ever Tweet!"
        },
        {
            "Id": 2,
            "PostedBy": "Rahul Pawar",
            "Text": "To bad I dont have a Twitter Account."
        },
        {
            "Id": 3,
            "PostedBy": "Rahul Pawar",
            "Text": "Lets see if we can use a WCF Service to Get/Update/Delete and Save Tweets to our local file."
        },
        {
            "Id": 4,
            "PostedBy": "Rahul Pawar",
            "Text": "Running out of time would have loved to use MongoDB here instead of a simple file :("
        }
    ]
}