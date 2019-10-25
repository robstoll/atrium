module ch.tutteli.atrium.domain.builders.kotlin_1_3 {
    requires transitive ch.tutteli.atrium.domain.builders;
    requires transitive ch.tutteli.atrium.domain.api.kotlin_1_3;
    requires transitive ch.tutteli.atrium.core.api;
    requires static     ch.tutteli.atrium.translations.en_GB;
    requires            kotlin.stdlib;

    exports ch.tutteli.atrium.domain.builders.kotlin_1_3.creating;
    exports ch.tutteli.atrium.domain.builders.kotlin_1_3;
}
