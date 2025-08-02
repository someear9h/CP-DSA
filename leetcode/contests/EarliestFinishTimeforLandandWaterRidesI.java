public class EarliestFinishTimeforLandandWaterRidesI {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        
        int n = landStartTime.length;
        int m = waterStartTime.length;

        int earliestFinishOnLand = (int)1e9;
        int overAllTime = (int)1e9;

        // get the minimum time to finish the land
        for(int i = 0; i < n; i++) {
            int timeOnLand = landStartTime[i] + landDuration[i];
            earliestFinishOnLand = Math.min(earliestFinishOnLand, timeOnLand);
        }

        // now go through each water ride
        for(int i = 0; i < m; i++) {
            int landFirstTime = waterDuration[i] + Math.max(earliestFinishOnLand, waterStartTime[i]);
            overAllTime = Math.min(overAllTime, landFirstTime);
        }
        
        int earliestFinishOnWater = (int)1e9;
        // water -> land rides
        for(int i = 0; i < m; i++) {
            earliestFinishOnWater = Math.min(earliestFinishOnWater, waterStartTime[i] + waterDuration[i]);
        }

        // now go on land and compare
        for(int i = 0; i < n; i++) {
            int waterFirstTime = landDuration[i] + Math.max(earliestFinishOnWater, landStartTime[i]);
            overAllTime = Math.min(overAllTime, waterFirstTime);
        }

        return overAllTime;
    }
}

