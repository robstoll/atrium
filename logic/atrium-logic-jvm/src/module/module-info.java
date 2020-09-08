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

    exports ch.tutteli.atrium.logic.creating.iterable.contains;
    exports ch.tutteli.atrium.logic.creating.iterable.contains.checkers;
    exports ch.tutteli.atrium.logic.creating.iterable.contains.creators;
    exports ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours;
    exports ch.tutteli.atrium.logic.creating.iterable.contains.steps;

    exports ch.tutteli.atrium.logic.impl to ch.tutteli.atrium.logic.kotlin_1_3;
    exports ch.tutteli.atrium.logic.impl.creating.changers to ch.tutteli.atrium.logic.kotlin_1_3;
}
