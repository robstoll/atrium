module ch.tutteli.atrium.core.robstoll {

    requires transitive ch.tutteli.atrium.core.api;
    requires ch.tutteli.atrium.core.robstoll.lib;

    exports ch.tutteli.atrium.robstoll.core;

    provides ch.tutteli.atrium.core.CoreFactory
        with ch.tutteli.atrium.robstoll.core.CoreFactoryImpl;
}
