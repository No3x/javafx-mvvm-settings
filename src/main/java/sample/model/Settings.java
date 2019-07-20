package sample.model;

import java.io.Serializable;

public class Settings implements Serializable {

    private static final long serialVersionUID = 4470694738124772838L;

    private String serverAddress = "";

    public Settings setServerAddress(final String serverAddress) {
        this.serverAddress = serverAddress;
        return this;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public static Settings defaultSettings() {
        return new Settings().setServerAddress("localhost");
    }
}
