module ch.tutteli.atrium.api.infix.en_GB {
    requires ch.tutteli.atrium.domain.builders;
    requires kotlin.stdlib;
    requires java.base;
    requires ch.tutteli.kbox;
    requires ch.tutteli.niok;

    exports ch.tutteli.atrium.api.infix.en_GB;
    exports ch.tutteli.atrium.api.infix.en_GB.creating;
    exports ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders;
    exports ch.tutteli.atrium.api.infix.en_GB.creating.feature;
    exports ch.tutteli.atrium.api.infix.en_GB.creating.iterable;
    exports ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders;
    exports ch.tutteli.atrium.api.infix.en_GB.creating.map;
    exports ch.tutteli.atrium.api.infix.en_GB.creating.path;
    exports ch.tutteli.atrium.api.infix.en_GB.workaround;
}
