package com.gamepub.princesstale.data.localization.model;

import java.util.Map;

public class Localization {
    private Map<String, String> localization;
    public Localization(Map<String, String> localization) {
        this.localization = localization;
    }

    public String getLocalization(String key) {
        return localization.get(key);
    }
}
