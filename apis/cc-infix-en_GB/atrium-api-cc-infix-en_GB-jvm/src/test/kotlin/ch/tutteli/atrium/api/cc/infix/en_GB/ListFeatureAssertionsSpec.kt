@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetNullableOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.verbs.internal.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class ListFeatureAssertionsSpec : Spek({
    include(AtriumFeatureAssertionsSpec)

    describeFun("", arrayOf("get for nullable")) {
        test("throws if no assertion is made for index within bound") {
            expect {
                ch.tutteli.atrium.verbs.internal.assert(listOf(null as String?, "hello")) get Index(1) assertIt {}
            }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
        }
        test("throws if no assertion is made for index not within bound") {
            expect {
                ch.tutteli.atrium.verbs.internal.assert(listOf(null as String?, "hello")) get Index(2) assertIt {}
                //TODO change to IllegalStateException in v0.10.0
            }.toThrow<IllegalArgumentException> { messageContains("There was not any assertion created") }
        }
    }

}) {
    object AtriumFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.ListFeatureAssertionsSpec(
        AssertionVerbFactory,
        getPlantFun.name to ListFeatureAssertionsSpec.Companion::getPlant,
        getFun.name to ListFeatureAssertionsSpec.Companion::get,
        getNullablePlantFun.name to ListFeatureAssertionsSpec.Companion::getNullablePlant,
        getNullableFun.name to ListFeatureAssertionsSpec.Companion::getNullable
    )

    companion object {
        val getPlantFun: KFunction2<Assert<List<Int>>, Int, Assert<Int>> = Assert<List<Int>>::get
        val getFun: KFunction2<Assert<List<Int>>, Index, ListGetOption<Int, List<Int>>> = Assert<List<Int>>::get
        val getNullablePlantFun: KFunction2<Assert<List<Int?>>, Int, AssertionPlantNullable<Int?>> = Assert<List<Int?>>::get
        val getNullableFun: KFunction2<Assert<List<Int?>>, Index, ListGetNullableOption<Int?, List<Int?>>> = Assert<List<Int?>>::get

        fun getPlant(plant: Assert<List<Int>>, index: Int) = plant get index

        fun get(plant: Assert<List<Int>>, index: Int, assertionCreator: Assert<Int>.() -> Unit) =
            plant get Index(index) assertIt { assertionCreator() }

        fun getNullablePlant(plant: Assert<List<Int?>>, index: Int) = plant get index

        fun getNullable(
            plant: Assert<List<Int?>>,
            index: Int,
            assertionCreator: AssertionPlantNullable<Int?>.() -> Unit
        ) = plant get Index(index) assertIt { assertionCreator() }
    }
}
