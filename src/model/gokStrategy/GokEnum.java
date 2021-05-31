package model.gokStrategy;

public enum GokEnum{
    STRAT_SOM21(
            Som21.class,
            "de som van de ogen van alle worpen samen is 21",
            5,
            "Som 21"
    ),
    STRAT_EVEN_OGEN(
            EvenOgen.class,
            "het aantal ogen bij elke worp is een even getal",
            4,
            "Even ogen"
    ),
    STRAT_LAATSTE_WORP_HOGER_DAN(
            LaatsteWorpHogerDanVorige.class,
            "het aantal ogen is bij elke worp hoger dan de vorige worp",
            10,
            "Hoger dan"
    );

    private final Class<? extends GokStrategy> clazz;
    private final String omschrijving;
    private final double winstfactor;
    private final String naam;

    GokEnum(Class<? extends GokStrategy> clazz, String omschrijving, double winstfactor, String naam){
        this.clazz = clazz;
        this.omschrijving = omschrijving;
        this.winstfactor = winstfactor;
        this.naam = naam;
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

    public String getNaam() {
        return naam;
    }

    public static GokEnum getEnumFromName(String naam){
        for(GokEnum gok : values()){
            if(gok.naam.equals(naam)) return gok;
        }
        return null;
    }
}
