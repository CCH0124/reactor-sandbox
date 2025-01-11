package org.reactor.common;

import net.datafaker.Faker;

public class Utils {
    private static Faker faker;

    public static Faker instance() {
        faker = new Faker();
        return faker;
    }
}
    
