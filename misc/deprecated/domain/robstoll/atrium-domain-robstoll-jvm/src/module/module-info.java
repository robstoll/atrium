module ch.tutteli.atrium.domain.robstoll {
    requires transitive ch.tutteli.atrium.domain.api;
    requires            ch.tutteli.atrium.domain.robstoll.lib;
    requires            kotlin.stdlib;

    provides ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
        with ch.tutteli.atrium.domain.robstoll.creating.collectors.AssertionCollectorImpl;
}
