module ch.tutteli.atrium.api.infix.en_GB {
    requires transitive ch.tutteli.atrium.core.api;
    requires            ch.tutteli.atrium.logic;
    requires            kotlin.stdlib;
    requires            ch.tutteli.kbox;

    exports ch.tutteli.atrium.api.infix.en_GB;
    exports ch.tutteli.atrium.api.infix.en_GB.creating.feature;
    exports ch.tutteli.atrium.api.infix.en_GB.creating.iterable;
    exports ch.tutteli.atrium.api.infix.en_GB.creating.map;
    exports ch.tutteli.atrium.api.infix.en_GB.creating.path;
}
