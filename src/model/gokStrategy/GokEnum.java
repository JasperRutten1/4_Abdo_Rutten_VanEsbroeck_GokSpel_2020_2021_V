package model.gokStrategy;

public enum GokEnum{
    STRAT_SOM21(Som21.class, "de som van de ogen van alle worpen samen is 21",5),
    STRAT_EVEN_OGEN(EvenOgen.class,"het aantal ogen bij elke worp is een even getal",4),
    STRAT_LAATSTE_WORP_HOGER_DAN_21(LaatsteWorpHogerDanVorige.class,"het aantal ogen is bij elke worp hoger dan de vorige worp",10);

    private final Class<? extends GokStrategy> clazz;
    private final String omschrijving;
    private final  double winstfactor;

    GokEnum(Class<? extends GokStrategy> clazz, String omschrijving, double winstfactor){
        this.clazz = clazz;
        this.omschrijving = omschrijving;
        this.winstfactor = winstfactor;
    }

    public Class<? extends GokStrategy> getClazz() {
        return clazz;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public double getWinstfactor() {
        return winstfactor;
    }
}
