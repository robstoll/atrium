package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.list.get.builders.ListGetStep
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KFunction2

class ListFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.ListFeatureAssertionsSpec(
    feature1<List<Int>, Int, Int>(Expect<List<Int>>::get).name to Companion::get,
    getIndexPair(),
    feature1<List<Int?>, Int, Int?>(Expect<List<Int?>>::get).name to Companion::getNullable,
    getIndexNullablePair()
) {

    companion object {
        fun get(expect: Expect<List<Int>>, index: Int) = expect get index
        fun getNullable(expect: Expect<List<Int?>>, index: Int) = expect get index

        fun getIndexPair() = getIndexFun.name to ::getIndex
        private val getIndexFun: KFunction2<Expect<List<Int>>, Index, ListGetStep<Int, List<Int>>> =
            Expect<List<Int>>::get

        private fun getIndex(expect: Expect<List<Int>>, index: Int, assertionCreator: Expect<Int>.() -> Unit) =
            expect get Index(index) assertIt { assertionCreator() }

        fun getIndexNullablePair() = getIndexNullableFun.name to ::getIndexNullable
        private val getIndexNullableFun: KFunction2<Expect<List<Int?>>, Index, ListGetStep<Int?, List<Int?>>> =
            Expect<List<Int?>>::get

        private fun getIndexNullable(
            expect: Expect<List<Int?>>,
            index: Int,
            assertionCreator: Expect<Int?>.() -> Unit
        ) = expect get Index(index) assertIt { assertionCreator() }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {

        var a1: Expect<List<Int>> = notImplemented()
        var a2: Expect<out List<Int>> = notImplemented()
        var a1b: Expect<List<Int?>> = notImplemented()
        var a2b: Expect<out List<Int?>> = notImplemented()

        var a3: Expect<out List<*>> = notImplemented()

        a1 get 1
        a2 get 1
        a1 = a1 get Index(1) assertIt { }
        a2 = a2 get Index(1) assertIt { }

        a1b get 1
        a2b get 1
        a1b = a1b get Index(1) assertIt { }
        a2b = a2b get Index(1) assertIt { }

        a3 get 1
        a3 = a3 get Index(1) assertIt { }
    }
}
