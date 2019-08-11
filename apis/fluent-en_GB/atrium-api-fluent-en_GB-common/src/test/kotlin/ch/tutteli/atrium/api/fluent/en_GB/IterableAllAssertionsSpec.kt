package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented

class IterableAllAssertionsSpec: ch.tutteli.atrium.specs.integration.IterableAllAssertionsSpec(
    AssertionVerbFactory,
    fun1(Expect<Iterable<Double>>::all),
    fun1(Expect<Iterable<Double?>>::all),
    "◆ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ "
)
fun ambiguityTest(){
    val e: Expect<Iterable<Number>> = notImplemented()
    e.contains(1)
    e.contains(1.0)
    e.contains(null)
}
