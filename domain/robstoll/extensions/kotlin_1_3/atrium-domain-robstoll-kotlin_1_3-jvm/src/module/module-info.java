module ch.tutteli.atrium.domain.robstoll.kotlin_1_3 {
    requires transitive ch.tutteli.atrium.domain.api.kotlin_1_3;
    requires            ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3;
    requires            kotlin.stdlib;

    provides ch.tutteli.atrium.domain.kotlin_1_3.creating.ResultAssertions
        with ch.tutteli.atrium.domain.robstoll.kotlin_1_3.creating.ResultAssertionsImpl;
}
