package com.sp.madproposal.Model;

public class Habit {
    private String habitName;
    private boolean isChecked;
    private String dateTime;

    public Habit() {
        // Default constructor required for Firebase
    }

    public Habit(String habitName, boolean isChecked, String dateTime) {
        this.habitName = habitName;
        this.isChecked = isChecked;
        this.dateTime = dateTime;
    }

    public String getHabitName() {
        return habitName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getDateTime() {
        return dateTime;
    }
}
