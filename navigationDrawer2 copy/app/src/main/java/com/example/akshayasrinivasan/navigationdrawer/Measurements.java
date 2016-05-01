package com.example.akshayasrinivasan.navigationdrawer;

/**
 * Created by AkshayaSrinivasan on 3/22/16.
 */
public class Measurements {

    public static final String TABLE = "Measurements";
    // Labels Table COXYGENLEVELumns names




    private String EMAIL;
    private String DATE;
    private String REFERENCE;
    private String HEIGHT;
    private Double WEIGHT;
    private Double BMI;
    private Double BLOODPRESSURE;
    private String PULSE;
    private String TEMPERATURE;
    private Double GLUCOSELEVEL;
    private String OXYGENLEVEL;







    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDATE()
    { return DATE;}
    public void setDATE(String DATE)
    {
        this.DATE=DATE;}

    public String getREFERENCE() {
        return REFERENCE;
    }

    public void setREFERENCE(String reference) {
        this.REFERENCE = reference;
    }

    public String getHEIGHT(){
        return HEIGHT;

    }

    public void setHEIGHT(String HEIGHT){
        this.HEIGHT=HEIGHT;
    }
    public Double getWEIGHT(){
        return WEIGHT;

    }

    public void setWEIGHT(Double WEIGHT){
        this.WEIGHT=WEIGHT;
    }

    public Double getBMI(){
        return BMI;

    }

    public void setBMI(Double BMI){
        this.BMI=BMI;
    }

    public Double getBLOODPRESSURE(){
       return BLOODPRESSURE;

    }

    public void setBLOODPRESSURE(Double BLOODPRESSURE){
        this.BLOODPRESSURE=BLOODPRESSURE;
    }
    public String getPULSE(){
        return PULSE;

    }

    public void setPULSE(String PULSE){
        this.PULSE=PULSE;
    }

    public String getTEMPERATURE(){
        return TEMPERATURE;

    }

    public void setTEMPERATURE(String TEMPERATURE){
        this.TEMPERATURE=TEMPERATURE;
    }

    public Double getGLUCOSELEVEL(){
        return GLUCOSELEVEL;

    }

    public void setGLUCOSELEVEL(Double GLUCOSELEVEL){
        this.GLUCOSELEVEL=GLUCOSELEVEL;
    }

    public String getOXYGENLEVEL(){
        return OXYGENLEVEL;

    }

    public void setOXYGENLEVEL(String OXYGENLEVEL){
        this.OXYGENLEVEL=OXYGENLEVEL;
    }
}
