module ch.tutteli.atrium.logic {
    requires transitive ch.tutteli.atrium.core.api;
    requires            ch.tutteli.niok;
    requires static     ch.tutteli.atrium.translations.en_GB;
    requires            kotlin.stdlib;

    exports ch.tutteli.atrium.logic;
    exports ch.tutteli.atrium.logic.creating;
    exports ch.tutteli.atrium.logic.creating.basic.contains;

    exports ch.tutteli.atrium.logic.creating.charsequence.contains;
    exports ch.tutteli.atrium.logic.creating.charsequence.contains.checkers;
    exports ch.tutteli.atrium.logic.creating.charsequence.contains.creators;
    exports ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours;
    exports ch.tutteli.atrium.logic.creating.charsequence.contains.steps;

    exports ch.tutteli.atrium.logic.creating.feature;

    exports ch.tutteli.atrium.logic.creating.iterable.contains;
    exports ch.tutteli.atrium.logic.creating.iterable.contains.checkers;
    exports ch.tutteli.atrium.logic.creating.iterable.contains.creators;
    exports ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours;
    exports ch.tutteli.atrium.logic.creating.iterable.contains.steps;

    exports ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting;

    exports ch.tutteli.atrium.logic.creating.maplike.contains;
    exports ch.tutteli.atrium.logic.creating.maplike.contains.creators;
    exports ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours;
    exports ch.tutteli.atrium.logic.creating.maplike.contains.steps;

    exports ch.tutteli.atrium.logic.creating.transformers;

    exports ch.tutteli.atrium.logic.creating.typeutils;

    exports ch.tutteli.atrium.logic.utils;

    exports ch.tutteli.atrium.logic.impl to ch.tutteli.atrium.logic.kotlin_1_3;
    exports ch.tutteli.atrium.logic.creating.transformers.impl to ch.tutteli.atrium.logic.kotlin_1_3;
}
