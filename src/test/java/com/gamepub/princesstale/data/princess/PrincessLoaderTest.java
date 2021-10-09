package com.gamepub.princesstale.data.princess;

import com.gamepub.princesstale.data.localization.LocalizationLoader;
import com.gamepub.princesstale.data.princess.model.Princess;
import org.junit.jupiter.api.Test;

import java.util.List;

class PrincessLoaderTest {
    @Test
    public void test() {
        final PrincessLoader princessLoader = new PrincessLoader();
        assert princessLoader.princesses.size() > 1;
        System.out.println(princessLoader.princesses.get(0).getID());
        assert princessLoader.princesses.get(0).getID().equals("1");

//        princessLoader.princesses.forEach(System.out::println);
    }

    @Test
    public void testPrincess() {
        final LocalizationLoader localizationLoader = new LocalizationLoader();
        final PrincessLoader princessLoader = new PrincessLoader();
        final List<Princess> princesses = princessLoader.getPrincesses(localizationLoader.getLocalization());
        princesses.forEach(System.out::println);
    }
}