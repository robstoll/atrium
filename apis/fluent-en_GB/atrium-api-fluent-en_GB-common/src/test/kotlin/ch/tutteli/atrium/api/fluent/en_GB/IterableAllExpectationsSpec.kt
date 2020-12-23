package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

object IterableAllExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableAllExpectationsSpec(
    fun1(Expect<Iterable<Double>>::all),
    fun1(Expect<Iterable<Double?>>::all).withNullableSuffix(),
    "◆ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ "
) {


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.all {}
        nList = nList.all {}
        subList = subList.all {}
        star = star.all {}
    }
}


