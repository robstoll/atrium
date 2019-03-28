module ch.tutteli.atrium.core.robstoll {
    requires transitive ch.tutteli.atrium.core.api;
    requires            ch.tutteli.atrium.core.robstoll.lib;
    requires            kotlin.stdlib;

    exports ch.tutteli.atrium.core.robstoll;

    provides ch.tutteli.atrium.core.CoreFactory
        with ch.tutteli.atrium.core.robstoll.CoreFactoryImpl;
}
