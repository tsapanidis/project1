package com.JP1.STNFO;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Insurance {
    private Date expDate;
    private Date cDate;
    private Boolean status;
    private long Days;
    private int idvehicle;

    public int getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(int idvehicle) {
        this.idvehicle = idvehicle;
    }

    public Insurance() {
        this.cDate = new Date();
    }

    public Date getExpDate() {
        return expDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public long getDays() {
        return TimeUnit.DAYS.convert(Days, TimeUnit.MILLISECONDS);
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
        this.Days = this.expDate.getTime()-this.cDate.getTime();
        if (this.Days > 0){
            this.status = true;
        }else{
            this.status =false;
        }

    }
}
