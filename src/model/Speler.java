package model;

import java.util.Objects;

/**
 * @author iedereen
 */
public class Speler {
    private String naam, voornaam, gebruiker;
    private double saldo;

    public Speler(String naam, String voornaam, String gebruiker){
        this(naam, voornaam, gebruiker, 0);
    }

    public Speler(String naam, String voornaam, String gebruiker, double saldo){
        this.naam = naam;
        this.voornaam = voornaam;
        this.gebruiker = gebruiker;
        this.saldo = saldo;
    }

    /*
    getters
     */

    public double getSaldo() {
        return saldo;
    }

    public String getGebruiker() {
        return gebruiker;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    /*
    setters
     */

    public void setSaldo(double saldo) {
        if (saldo < 0) throw new IllegalArgumentException("saldo kan niet negatief zijn");
        this.saldo = saldo;
    }

    public void addSaldo(int hoeveel){
        if(saldo + hoeveel < 0 ) throw new IllegalArgumentException("saldo kan niet negatief zijn");
        saldo += hoeveel;
    }

    public void removeSaldo(int hoeveel){
        if(saldo - hoeveel < 0 ) throw new IllegalArgumentException("saldo kan niet negatief zijn");
        saldo -= hoeveel;
    }

    /*
    overrides
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Speler)) return false;
        Speler speler = (Speler) o;
        return saldo == speler.saldo && Objects.equals(naam, speler.naam) && Objects.equals(voornaam, speler.voornaam) && Objects.equals(gebruiker, speler.gebruiker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam, voornaam, gebruiker, saldo);
    }

    @Override
    public String toString() {
        return "Speler{" +
                "naam='" + naam + '\'' +
                ", voornaam='" + voornaam + '\'' +
                ", gebruiker='" + gebruiker + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
