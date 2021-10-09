package com.gamepub.princesstale.data.localization;

import com.gamepub.princesstale.data.CSVLoader;
import com.gamepub.princesstale.data.localization.model.Localization;
import com.gamepub.princesstale.data.localization.data.LocalizationBean202109;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocalizationLoader {
    public Map<String, String> localization;

    public LocalizationLoader() {
        final String resource = "LangEN_202109.csv";
        final List<LocalizationBean202109> localizations = CSVLoader.loadCSV(LocalizationBean202109.class, resource);
        localization = localizations.stream()
                .collect(Collectors.toMap(LocalizationBean202109::getKey, LocalizationBean202109::getValue));
    }

    public Localization getLocalization() {
        return new Localization(localization);
    }
}
