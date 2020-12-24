module ch.tutteli.atrium.domain.robstoll.lib {
    requires        ch.tutteli.atrium.domain.builders;
    requires static ch.tutteli.atrium.translations.en_GB;
    requires        kotlin.stdlib;
    requires        ch.tutteli.niok;

    exports ch.tutteli.atrium.domain.robstoll.lib.creating.collectors;
}
