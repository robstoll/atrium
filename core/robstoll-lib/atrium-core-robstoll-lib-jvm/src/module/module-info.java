module ch.tutteli.atrium.core.robstoll.lib {
    requires        ch.tutteli.atrium.core.api;
    requires        ch.tutteli.kbox;
    requires static ch.tutteli.atrium.translations.en_GB;
    requires        kotlin.stdlib;

    exports ch.tutteli.atrium.core.robstoll.lib.checking;
    exports ch.tutteli.atrium.core.robstoll.lib.creating;
    exports ch.tutteli.atrium.core.robstoll.lib.reporting;
    exports ch.tutteli.atrium.core.robstoll.lib.reporting.translating;
}
