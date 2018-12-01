package com.JP1.STNFO;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Insurance {
    private Date expirationdate;
    private Date currentdate;
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
        this.currentdate = new Date();
    }

    public Date getExpirationDate() {
        return expirationdate;
    }

    public Boolean getStatus() {
        return status;
    }

    public long getDays() {
        return TimeUnit.DAYS.convert(Days, TimeUnit.MILLISECONDS);
    }

    public void setExpDate(Date expDate) {
        this.expirationdate = expDate;
        this.Days = this.expirationdate.getTime()-this.currentdate.getTime();
        if (this.Days > 0){
            this.status = true;
        }else{
            this.status =false;
        }

    }

    @Override
    public String toString() {
        return "{" +
                "expirationdate=" + expirationdate +
                ", status=" + status +
                ", Days=" + Math.abs(getDays()) +
                "}\n";
    }
}
