package sample.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@Builder
public class Settings implements Serializable {

    private static final long serialVersionUID = 4470694738124772838L;

    @Builder.Default
    private @NonNull String serverAddress = "";

    public static Settings defaultSettings() {
        return Settings.builder().serverAddress("localhost").build();
    }
}
