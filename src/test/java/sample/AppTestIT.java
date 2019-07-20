package sample;

import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import sample.ui.SettingsViewModel;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.ListViewMatchers.hasListCell;

public class AppTestIT extends ApplicationTest {

    @Before
    public void setupApp() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(App.class);
    }

    @Test
    public void executeSaveAndLoad() {
        clickOn("#serverAddressTextField");
        write("hello");

        clickOn("#saveButton");
        verifyThat("#historyListView", hasListCell(SettingsViewModel.MSG_SAVE));

        clickOn("#loadButton");
        verifyThat("#historyListView", hasListCell(SettingsViewModel.MSG_LOAD));
    }
}