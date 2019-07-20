package sample.service;

import sample.model.Settings;

public interface SettingsService {
    void save(Settings settings) throws Exception;
    Settings load() throws Exception;

    static SettingsService getInstance() {
        return new SettingsServiceSerializationImpl();
    }
}
