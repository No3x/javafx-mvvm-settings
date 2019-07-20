package sample.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Settings;
import sample.service.SettingsService;

public class SettingsViewModel {

    public final static String MSG_SAVE = "Try to save...";
    public final static String MSG_LOAD = "Try to load...";
    private final StringProperty serverAddress = new SimpleStringProperty(this, "serverAddress");
    private final ObservableList<String> history = FXCollections.observableArrayList();
    private final SettingsService settingsService;
    private Settings model;

    public SettingsViewModel(Settings model, SettingsService settingsService) {
        this.model = model;
        this.settingsService = settingsService;
        init(model);
    }

    private void init(Settings settings) {
        serverAddress.set(settings.getServerAddress());
    }

    public void save() {
        history.add(MSG_SAVE);
        try {
            model.setServerAddress(serverAddress.get());
            settingsService.save(model);
        } catch (Exception e) {
            history.add(e.getMessage());
        }
    }

    public void load() {
        history.add(MSG_LOAD);
        try {
            model = settingsService.load();
            init(model);
        } catch (Exception e) {
            history.add(e.getMessage());
        }
    }

    public BooleanBinding validationIsOk() {
        return Bindings.isNotEmpty(serverAddress);
    }

    public BooleanBinding validationIsNotOk() {
        return Bindings.not(validationIsOk());
    }

    public String getServerAddress() {
        return serverAddress.get();
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress.set(serverAddress);
    }

    public StringProperty serverAddressProperty() {
        return serverAddress;
    }

    public ObservableList<String> historyItemsProperty() {
        return history;
    }

    public ObservableList<String> getHistory() {
        return history;
    }
}
