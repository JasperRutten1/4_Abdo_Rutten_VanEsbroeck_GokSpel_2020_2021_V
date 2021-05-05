package model.database.saveLoadStrategies;

import model.Speler;

public class SpelerTekstSaveLoad extends TeksSaveLoadTemplate<String,Speler> implements SaveLoadInterface{
    @Override
    protected Speler generateValue(String[] args) {
        return new Speler(args[0], args[1], args[2], Double.parseDouble(args[3]));
    }

    @Override
    protected String generateKey(Speler value) {
        return value.getGebruiker();
    }

    @Override
    protected String generateDataString(Speler value) {
        return value.getNaam() + "," + value.getVoornaam() + "," + value.getGebruiker() + "," + value.getSaldo();
    }
}
