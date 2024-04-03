package com.sp.madproposal.Model;

public class Reward {

    private String rewardName;
    private boolean isChecked;
    private String dateTime;

    public Reward(String rewardName, boolean isChecked, String dateTime) {
        this.rewardName = rewardName;
        this.isChecked = isChecked;
        this.dateTime = dateTime;
    }

    public Reward() {
        // Default constructor required for Firebase
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
