package com.clearbox.stockfolio.adapter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ghodk on 1/15/2018.
 */

public class DateAxisFormatter extends ValueFormatter {

    private int interval;

    public DateAxisFormatter(int i) {
        this.interval = i;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    @Override
    public String getFormattedValue(float value) {
        long millis = (long) value;
        String time;
        switch (interval) {
            case 0:
            case 1:
            case 2:
                time = new SimpleDateFormat("HH:MM", Locale.US).format(new Date(millis * 1000L));
                break;
            case 3:
            case 4:
            case 5:
                time = new SimpleDateFormat("MM/dd", Locale.US).format(new Date(millis * 1000L));
                break;
            case 6:
            case 7:
                time = new SimpleDateFormat("MM/yyyy", Locale.US).format(new Date(millis * 1000L));
                break;
            default:
                time = new SimpleDateFormat("HH:MM", Locale.US).format(new Date(millis * 1000L));
                break;
        }
        return time;
    }
}
