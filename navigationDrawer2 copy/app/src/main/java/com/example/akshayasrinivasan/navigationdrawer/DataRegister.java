package com.example.akshayasrinivasan.navigationdrawer;

import android.graphics.Bitmap;

/**
 * Created by ReemaThakur on 4/17/16.
 */
public class DataRegister { public static final String TABLE = "DataRegister";




    private String EMAIL;

    private String FIRSTNAME;
    private String LASTNAME;
    private String PASSWORD;
    private String DOB;
    private String CONTACT;
    private String IMAGE;








    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }


    public String getFIRSTNAME(){
        return FIRSTNAME;

    }

    public void setFIRSTNAME(String FIRSTNAME){
        this.FIRSTNAME=FIRSTNAME;
    }
    public String getLASTNAME(){
        return LASTNAME;

    }

    public void setLASTNAME(String LASTNAME){
        this.LASTNAME=LASTNAME;
    }

    public String getDOB(){
        return DOB;

    }

    public void setDOB(String DOB){
        this.DOB=DOB;
    }

    public String getCONTACT(){
        return CONTACT;

    }

    public void setCONTACT(String CONTACT){
        this.CONTACT=CONTACT;
    }
    public String getIMAGE(){
        return IMAGE;

    }


    public void setIMAGE(String IMAGE){
        this.IMAGE=IMAGE;
    }

    public String getPASSWORD(){
        return PASSWORD;

    }

    public void setPASSWORD(String PASSWORD){
        this.PASSWORD=PASSWORD;
    }



}
