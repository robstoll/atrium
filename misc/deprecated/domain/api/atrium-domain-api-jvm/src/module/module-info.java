module ch.tutteli.atrium.domain.api {
    requires transitive ch.tutteli.atrium.core.api;
    requires            kotlin.stdlib;

    exports ch.tutteli.atrium.domain.creating;
    exports ch.tutteli.atrium.domain.creating.collectors;
}
