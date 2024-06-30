module ch.tutteli.atrium.domain {
    requires transitive ch.tutteli.atrium.core.api;
    requires            ch.tutteli.kbox;
    requires            ch.tutteli.niok;
    //TODO 1.3.0 change
    requires static     ch.tutteli.atrium.translations.en_GB;
    requires            kotlin.stdlib;

    //TODO 1.3.0 define exports
    exports ch.tutteli.atrium.domain;
    exports ch.tutteli.atrium.domain.creating.transformers;
    exports ch.tutteli.atrium.domain.reporting;
}

