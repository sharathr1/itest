package com.ip.itest.java;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class SerialClass implements Serializable {
    private LocalDate currentTime;

    public SerialClass() {
        currentTime =LocalDate.now();
    }

    public LocalDate getCurrentTime() {
        return currentTime;
    }
}