package ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.impl

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import kotlin.reflect.KFunction2

internal object StaticNames {
    val notToContainValuesFun = run {
        val containsNotKf: KFunction2<Expect<Iterable<Double>>, o, NotCheckerStep<Double, Iterable<Double>, NotSearchBehaviour>> =
            Expect<Iterable<Double>>::notToContain
        //TODO use once  https://youtrack.jetbrains.com/issue/KT-38013 is fixed (there are other places where "values" is hard-coded)
//        val values : KFunction2<Int, Array<out Int>, Values<Int>> = ::values
//        "`${f.name} ${o::class.simpleName} ${fThe.name} ${values.name}`"
        "`${containsNotKf.name} values`"
    }

    val atLeast = IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::atLeast.name
    val butAtMost = AtLeastCheckerStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::butAtMost.name
    val atMost = IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::atMost.name
    val exactly = IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::exactly.name
    val notOrAtMost = IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::notOrAtMost.name

    /** @since 1.0.0 */
    val notToHaveElementsOrNone = Expect<List<Int>>::notToHaveElementsOrNone.name
}
