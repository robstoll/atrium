module ch.tutteli.atrium.logic {
    requires ch.tutteli.atrium.domain.builders;
    requires ch.tutteli.niok;
    requires kotlin.stdlib;

    exports ch.tutteli.atrium.logic;
    exports ch.tutteli.atrium.logic.impl to ch.tutteli.atrium.logic.kotlin_1_3;
    exports ch.tutteli.atrium.logic.impl.creating.changers to ch.tutteli.atrium.logic.kotlin_1_3;
}
