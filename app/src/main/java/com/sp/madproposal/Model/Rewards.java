package com.sp.madproposal.Model;

public class Rewards {

    private int totalPoints, redeemPoints;

    private Boolean selectedReward1, selectedReward2, selectedReward3, selectedReward4,
            selectedReward5, selectedReward6;

    public Rewards() {

    }

    public Rewards(int totalPoints, int redeemPoints, Boolean selectedReward1, Boolean selectedReward2, Boolean selectedReward3, Boolean selectedReward4, Boolean selectedReward5, Boolean selectedReward6) {
        this.totalPoints = totalPoints;
        this.redeemPoints = redeemPoints;
        this.selectedReward1 = selectedReward1;
        this.selectedReward2 = selectedReward2;
        this.selectedReward3 = selectedReward3;
        this.selectedReward4 = selectedReward4;
        this.selectedReward5 = selectedReward5;
        this.selectedReward6 = selectedReward6;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getRedeemPoints() {
        return redeemPoints;
    }

    public void setRedeemPoints(int redeemPoints) {
        this.redeemPoints = redeemPoints;
    }

    public Boolean getSelectedReward1() {
        return selectedReward1;
    }

    public void setSelectedReward1(Boolean selectedReward1) {
        this.selectedReward1 = selectedReward1;
    }

    public Boolean getSelectedReward2() {
        return selectedReward2;
    }

    public void setSelectedReward2(Boolean selectedReward2) {
        this.selectedReward2 = selectedReward2;
    }

    public Boolean getSelectedReward3() {
        return selectedReward3;
    }

    public void setSelectedReward3(Boolean selectedReward3) {
        this.selectedReward3 = selectedReward3;
    }

    public Boolean getSelectedReward4() {
        return selectedReward4;
    }

    public void setSelectedReward4(Boolean selectedReward4) {
        this.selectedReward4 = selectedReward4;
    }

    public Boolean getSelectedReward5() {
        return selectedReward5;
    }

    public void setSelectedReward5(Boolean selectedReward5) {
        this.selectedReward5 = selectedReward5;
    }

    public Boolean getSelectedReward6() {
        return selectedReward6;
    }

    public void setSelectedReward6(Boolean selectedReward6) {
        this.selectedReward6 = selectedReward6;
    }
}
