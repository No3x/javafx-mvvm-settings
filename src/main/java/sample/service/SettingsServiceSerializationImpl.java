package sample.service;

import sample.model.Settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class SettingsServiceSerializationImpl implements SettingsService {

    private static final String name = "settings.ser";

    @Override
    public void save(Settings settings) throws Exception {
        try (FileOutputStream fos = new FileOutputStream (name);
             ObjectOutputStream oos = new ObjectOutputStream (fos)) {
            oos.writeObject (settings);
        }
    }

    @Override
    public Settings load() throws Exception {
        try (FileInputStream fis = new FileInputStream (name);
             ObjectInputStream ois = new ObjectInputStream (fis)) {
            return (Settings) ois.readObject ();
        }
    }
}
