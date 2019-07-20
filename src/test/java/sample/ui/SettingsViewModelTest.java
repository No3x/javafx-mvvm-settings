package sample.ui;

import org.junit.Before;
import org.junit.Test;
import sample.model.Settings;
import sample.service.SettingsService;

import static eu.lestard.assertj.javafx.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static sample.ui.SettingsViewModel.MSG_LOAD;
import static sample.ui.SettingsViewModel.MSG_SAVE;

public class SettingsViewModelTest {

    private SettingsViewModel viewModel;
    private SettingsService settingsServiceMock;

    @Before
    public void setUp() throws Exception {
        settingsServiceMock = mock(SettingsService.class);
        when(settingsServiceMock.load()).thenReturn(new Settings().setServerAddress("new value"));

        Settings settings = new Settings();

        viewModel = new SettingsViewModel(settings, settingsServiceMock);
    }

    @Test
    public void testFormValidation() {
        // initial values
        assertThat(viewModel.serverAddressProperty()).hasValue("");

        // empty value is not valid
        assertThat(viewModel.validationIsOk()).isFalse();

        // change server address
        viewModel.serverAddressProperty().set("changed value");

        // now ok disabled
        assertThat(viewModel.validationIsOk()).isTrue();

        // set to empty value again
        viewModel.serverAddressProperty().set("");
        assertThat(viewModel.validationIsOk()).isFalse();
    }

    @Test
    public void testLoadAndSave() throws Exception {
        viewModel.save();
        viewModel.load();

        verify(settingsServiceMock, times(1)).save(any(Settings.class));
        verify(settingsServiceMock, times(1)).load();
        verifyNoMoreInteractions(settingsServiceMock);

        assertThat(viewModel.serverAddressProperty()).hasValue("new value");
    }

    @Test
    public void testHistory() {
        assertThat(viewModel.historyItemsProperty()).hasSize(0);
        viewModel.save();
        assertThat(viewModel.historyItemsProperty()).hasSize(1).first().isEqualTo(MSG_SAVE);
        viewModel.save();
        assertThat(viewModel.historyItemsProperty()).hasSize(2).last().isEqualTo(MSG_SAVE);
        viewModel.load();
        assertThat(viewModel.historyItemsProperty()).hasSize(3).last().isEqualTo(MSG_LOAD);
    }
}
