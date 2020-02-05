package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.list.get.builders.ListGetStep
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.reflect.KFunction2

class ListFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.ListFeatureAssertionsSpec(
    feature1<List<Int>, Int, Int>(Expect<List<Int>>::get),
    getIndexPair(),
    feature1<List<Int?>, Int, Int?>(Expect<List<Int?>>::get).withNullableSuffix(),
    getIndexNullablePair()
) {
    companion object : WithAsciiReporter()

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Int>> = notImplemented()
        var a1b: Expect<List<Int?>> = notImplemented()

        var star: Expect<out List<*>> = notImplemented()

        a1 get 1
        a1 = a1 get Index(1) assertIt { }

        a1b get 1
        a1b = a1b get Index(1) assertIt { }

        star get 1
        star = star get Index(1) assertIt { }
    }
}

private val getIndexFun: KFunction2<Expect<List<Int>>, Index, ListGetStep<Int, List<Int>>> = Expect<List<Int>>::get
private fun getIndexPair() = getIndexFun.name to ::getIndex

private fun getIndex(expect: Expect<List<Int>>, index: Int, assertionCreator: Expect<Int>.() -> Unit) =
    expect get Index(index) assertIt { assertionCreator() }

private val getIndexNullableFun: KFunction2<Expect<List<Int?>>, Index, ListGetStep<Int?, List<Int?>>> =
    Expect<List<Int?>>::get

private fun getIndexNullablePair() = getIndexNullableFun.name to ::getIndexNullable

private fun getIndexNullable(
    expect: Expect<List<Int?>>,
    index: Int,
    assertionCreator: Expect<Int?>.() -> Unit
) = expect get Index(index) assertIt { assertionCreator() }
