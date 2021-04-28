package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

object IterableToHaveNextAndAllExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableToHaveNextAndAllExpectationsSpec(
    fun1(Expect<Iterable<Double>>::toHaveNextAndAll),
    fun1(Expect<Iterable<Double?>>::toHaveNextAndAll).withNullableSuffix()
) {


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toHaveNextAndAll {}
        nList = nList.toHaveNextAndAll {}
        subList = subList.toHaveNextAndAll {}
        star = star.toHaveNextAndAll {}
    }
}


