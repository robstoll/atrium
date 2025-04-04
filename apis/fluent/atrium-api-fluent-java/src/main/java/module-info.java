module ch.tutteli.atrium.api.fluent.java {
    requires transitive ch.tutteli.atrium.core.api;
    requires            ch.tutteli.atrium.logic;
    requires            kotlin.stdlib;
    requires            ch.tutteli.kbox;
    requires java.sql;
//    requires annotations;

    exports ch.tutteli.atrium.api.fluent.java;
}
