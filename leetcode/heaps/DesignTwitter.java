import java.util.*;

class Twitter {
    private static int timeStamp = 0;

    HashMap<Integer, HashSet<Integer>> following;
    HashMap<Integer, List<int[]>> tweets;

    public Twitter() {
        following = new HashMap<>();
        tweets = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new ArrayList<>());
        tweets.get(userId).add(new int[] {timeStamp++, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        if(tweets.containsKey(userId)) {
            maxHeap.addAll(tweets.get(userId));
        }

        // include followee's tweets
        if(following.containsKey(userId)) {
            for(int fol : following.get(userId)) {
                if(tweets.containsKey(fol)) {
                    maxHeap.addAll(tweets.get(fol));
                }
            }
        }

        // get the 10 recent tweets
        List<Integer> feed = new ArrayList<>();
        int cnt = 0;
        while(!maxHeap.isEmpty() && cnt < 10) {
            feed.add(maxHeap.remove()[1]);
            cnt++;
        }

        return feed;
    }

    public void follow(int followerId, int followeeId) {
        if(followeeId == followerId) return;

        following.putIfAbsent(followerId, new HashSet<>());
        following.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (following.containsKey(followerId)) {
            following.get(followerId).remove(followeeId);
        }
    }
}