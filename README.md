# javafx-mvvm-settings
A simple implementation of a settings view to show the MVVM concept in JavaFX to produce testable code.

The implementation has no dependency except JavaFX.

The example consists of a simple TextField that represent one property in the settings. A basic validation is done in the ViewModel by checking if the value is empty. If so the save button is disabled.
When clicking the save button the settings are persisted.
When clicking the load button the settings are loaded.

![a](https://user-images.githubusercontent.com/2690708/61583748-b126ac80-ab3c-11e9-99cc-c4b574b51f54.gif)
## Structure

```
└───src
    ├───main
    │   ├───java
    │   │   └───sample
    │   │       │   App.java: (Starter)
    │   │       │
    │   │       ├───model
    │   │       │       Settings.java: (data object for the settings)
    │   │       │
    │   │       ├───service
    │   │       │       (I)SettingsService.java: service for storage and loading of a settings object
    │   │       │       SettingsServiceSerializationImpl.java: service implementation by serialization
    │   │       │
    │   │       └───ui
    │   │               SettingsView.java: ViewController to do bindings with the VM
    │   │               SettingsViewModel.java: VM that holds logic for visualization, validation and delegates to business rules
    │   │
    │   └───resources
    │       └───sample
    │           └───ui
    │                   SettingsView.fxml: the view described in FXML
    │
    └───test
        ├───java
        │   └───sample
        │       │   AppTestIT.java: integration test
        │       │
        │       └───ui
        │               SettingsViewModelTest.java: unit test for ViewModel
        │
        └───resources
```

## Unit and Integration Tests
The tests depend on some libraries
```
testCompile 'junit:junit:4.12' : testing framework
testCompile 'org.assertj:assertj-core:3.12.2' : extension for assertions
testCompile 'eu.lestard:assertj-javafx:0.3.0' : extension for assertj to assert Observables
testCompile 'org.mockito:mockito-core:2.28.2' : mocking framework for unit tests to to mock the service
testCompile 'org.testfx:testfx-junit:4.0.15-alpha' : test framework for integration test with JavaFX
```
By executing the goal `test` the unit test and the integration test are executed. For the latter a window will open that performs interaction with the window.
The tests looks a follows:
```java
@Test
public void executeSaveAndLoad() {
    clickOn("#serverAddressTextField");
    write("hello");

    clickOn("#saveButton");
    verifyThat("#historyListView", hasListCell(SettingsViewModel.MSG_SAVE));

    clickOn("#loadButton");
    verifyThat("#historyListView", hasListCell(SettingsViewModel.MSG_LOAD));
}
```

![b](https://user-images.githubusercontent.com/2690708/61583752-c996c700-ab3c-11e9-9176-b0d8295c8162.gif)

The server address field is clicked and hello is typed in. When clicking the `saveButton` the text `Try to save...` should be displayed in the ListView. 
The tests are not very meaningful but should get you started.

## Info
I use this kind of structure with the spring boot framework.

If you plan to use MVVM please have a look at the [mvvmFX](https://github.com/sialcasa/mvvmFX) framework.