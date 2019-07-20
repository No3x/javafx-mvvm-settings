package sample.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.model.Settings;
import sample.service.SettingsService;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsView implements Initializable {

    @FXML
    private Button saveButton;

    @FXML
    private TextField serverAddressTextField;

    @FXML
    private ListView<String> historyListView;

    private final SettingsViewModel viewModel;

    public SettingsView() {
        SettingsService settingsService = SettingsService.getInstance();
        // Load from file
        Settings settings = Settings.defaultSettings();
        // Construct view model
        this.viewModel = new SettingsViewModel(settings, settingsService);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serverAddressTextField.textProperty().bindBidirectional(viewModel.serverAddressProperty());
        historyListView.setItems(viewModel.historyItemsProperty());
        saveButton.disableProperty().bind(viewModel.validationIsNotOk());
    }

    @FXML
    public void save() {
        viewModel.save();
    }

    @FXML
    public void load() {
        viewModel.load();
    }
}
