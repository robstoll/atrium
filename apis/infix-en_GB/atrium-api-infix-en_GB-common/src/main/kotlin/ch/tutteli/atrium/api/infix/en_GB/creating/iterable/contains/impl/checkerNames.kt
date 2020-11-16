package ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.impl

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.NotCheckerStep
import kotlin.reflect.KFunction2

internal object StaticName {
    val containsNotValuesFun = {
        val containsNotKf: KFunction2<Expect<Iterable<Double>>, o, NotCheckerStep<Double, Iterable<Double>, NotSearchBehaviour>> =
            Expect<Iterable<Double>>::containsNot
        //TODO use once  https://youtrack.jetbrains.com/issue/KT-38013 is fixed (there are other places where "values" is hard-coded)
//        val values : KFunction2<Int, Array<out Int>, Values<Int>> = ::values
//        "`${f.name} ${o::class.simpleName} ${fThe.name} ${values.name}`"
        "`${containsNotKf.name} values`"
    }()

    val atLeast = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::atLeast.name
    val butAtMost = AtLeastCheckerStep<*, *, InAnyOrderSearchBehaviour>::butAtMost.name
    val atMost = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::atMost.name
    val exactly = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::exactly.name
    val notOrAtMost = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::notOrAtMost.name
}
