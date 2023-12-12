package ex2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
abstract class InstrumentMuzical implements Comparable<InstrumentMuzical> {
    private String producator;
    private double pret;

    public InstrumentMuzical() {
    }

    public InstrumentMuzical(String producator, double pret) {
        this.producator = producator;
        this.pret = pret;
    }
    public int compareTo(InstrumentMuzical a){
        if(this.producator.equalsIgnoreCase(a.producator)==false){
            return this.producator.compareToIgnoreCase(a.producator);
        }
        else
            return (int)(this.pret-a.pret);
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "InstrumentMuzical{" +
                "producator='" + producator + '\'' +
                ", pret=" + pret +
                '}';
    }
}

