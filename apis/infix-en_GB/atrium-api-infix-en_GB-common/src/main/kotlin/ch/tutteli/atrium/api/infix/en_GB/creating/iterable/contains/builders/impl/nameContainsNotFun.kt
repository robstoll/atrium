package ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.infix.en_GB.Values
import ch.tutteli.atrium.api.infix.en_GB.containsNot
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.the
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import kotlin.reflect.KFunction2

internal object StaticName {
    private val f: KFunction2<Expect<Iterable<Double>>, o, NotCheckerOption<Double, Iterable<Double>, NotSearchBehaviour>> =
        Expect<Iterable<Double>>::containsNot
    private val fThe: KFunction2<
        IterableContains.CheckerOption<Double, Iterable<Double>, InAnyOrderSearchBehaviour>,
        Values<Double>,
        Expect<Iterable<Double>>
        > = IterableContains.CheckerOption<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::the
    val nameContainsNotValuesFun = "`${f.name} ${o::class.simpleName} ${fThe.name} ${Values::class.simpleName}`"
}
