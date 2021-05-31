package model.gokStrategy;

/**
 * @author Jordy
 */
public enum GokEnum{
    STRAT_SOM21(
            Som21.class,
            "Som van alle ogen is samen 21",
            5,
            "Som 21"
    ),
    STRAT_EVEN_OGEN(
            EvenOgen.class,
            "Aantal ogen bij elke worp is een even getal",
            4,
            "Even ogen"
    ),
    STRAT_LAATSTE_WORP_HOGER_DAN(
            LaatsteWorpHogerDanVorige.class,
            "Ogen bij elke worp is hoger dan de vorige",
            10,
            "Hoger dan"
    );

    private final Class<? extends GokStrategy> clazz;
    private final String omschrijving;
    private final double defaultWinstfactor;
    private final String naam;

    GokEnum(Class<? extends GokStrategy> clazz, String omschrijving, double winstfactor, String naam){
        this.clazz = clazz;
        this.omschrijving = omschrijving;
        this.defaultWinstfactor = winstfactor;
        this.naam = naam;
    }

    public Class<? extends GokStrategy> getClazz() {
        return clazz;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public double getDefaultWinstfactor() {
        return defaultWinstfactor;
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
