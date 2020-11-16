module ch.tutteli.atrium.logic {
    requires ch.tutteli.atrium.domain.builders;
    requires ch.tutteli.niok;
    requires kotlin.stdlib;

    exports ch.tutteli.atrium.logic;
    exports ch.tutteli.atrium.logic.creating.basic.contains;

    exports ch.tutteli.atrium.logic.creating.charsequence.contains;
    exports ch.tutteli.atrium.logic.creating.charsequence.contains.checkers;
    exports ch.tutteli.atrium.logic.creating.charsequence.contains.creators;
    exports ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours;
    exports ch.tutteli.atrium.logic.creating.charsequence.contains.steps;

    exports ch.tutteli.atrium.logic.creating.iterablelike.contains;
    exports ch.tutteli.atrium.logic.creating.iterablelike.contains.checkers;
    exports ch.tutteli.atrium.logic.creating.iterablelike.contains.creators;
    exports ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours;
    exports ch.tutteli.atrium.logic.creating.iterablelike.contains.steps;

    exports ch.tutteli.atrium.logic.creating.transformers;

    exports ch.tutteli.atrium.logic.creating.typeutils;

    exports ch.tutteli.atrium.logic.utils;

    exports ch.tutteli.atrium.logic.impl to ch.tutteli.atrium.logic.kotlin_1_3;
    exports ch.tutteli.atrium.logic.creating.transformers.impl to ch.tutteli.atrium.logic.kotlin_1_3;
}
