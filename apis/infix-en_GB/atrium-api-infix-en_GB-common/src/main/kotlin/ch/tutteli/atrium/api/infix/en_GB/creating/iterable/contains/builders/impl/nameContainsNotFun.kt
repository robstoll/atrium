package ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.infix.en_GB.containsNot
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.the
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import kotlin.reflect.KFunction2

internal object StaticName {
    val nameContainsNotValuesFun: String = {
        val containsNotKf: KFunction2<Expect<Iterable<Double>>, o, NotCheckerOption<Double, Iterable<Double>, NotSearchBehaviour>> =
            Expect<Iterable<Double>>::containsNot
        val theKf: KFunction2<
            IterableContains.CheckerOption<Double, Iterable<Double>, InAnyOrderSearchBehaviour>,
            Values<Double>,
            Expect<Iterable<Double>>
            > = IterableContains.CheckerOption<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::the
        //TODO use once  https://youtrack.jetbrains.com/issue/KT-38013 is fixed (there are other places where "values" is hard-coded)
//        val values : KFunction2<Int, Array<out Int>, Values<Int>> = ::values
//        "`${f.name} ${o::class.simpleName} ${fThe.name} ${values.name}`"
        "`${containsNotKf.name} ${o::class.simpleName} ${theKf.name} values`"
    }()
}
