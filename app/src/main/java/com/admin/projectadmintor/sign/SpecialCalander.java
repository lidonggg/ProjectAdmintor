package com.admin.projectadmintor.sign;

import java.util.Calendar;

/**
 * Created by apple on 2018/1/4.
 */

public class SpecialCalander {
    public boolean isLeapYear(int year){
        if(year%100==0&&year%400==0){
            return true;
        }else if(year%100!=0&&year%4==0){
            return true;
        }
        return false;
    }

    public int getDaysOfMonth(boolean isLeapYear,int month){
        int days=0;
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days=30;
                break;
            case 2:
                if(isLeapYear){
                    days=29;
                }else {
                    days=28;
                }
        }
        return days;
    }

    public int getWeekdayOfMonth(int mYear,int mMonth){
        Calendar calendar=Calendar.getInstance();
        calendar.set(mYear,mMonth,1);
        return calendar.get(Calendar.DAY_OF_WEEK)-1;
    }
}


