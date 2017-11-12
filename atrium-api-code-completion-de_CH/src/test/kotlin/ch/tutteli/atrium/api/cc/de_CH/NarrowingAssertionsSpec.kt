package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction2

class NarrowingAssertionsSpec : ch.tutteli.atrium.spec.assertions.NarrowingAssertionsSpec(
    AssertionVerbFactory,
    getIsNotNullPair(),
    Companion::isNotNullLess,
    Companion::isNotNullGreaterAndLess,
    getNameIsA(),
    Companion::isAInt,
    Companion::isAString,
    Companion::isACharSequence,
    Companion::isASubType,
    Companion::isAIntLess
) {
    companion object {

        private inline fun <reified T : Any> isNotNull(): KFunction2<IAssertionPlantNullable<T?>, IAssertionPlant<T>.() -> Unit, Unit>
            = IAssertionPlantNullable<T?>::istNichtNull

        private fun getIsNotNullPair() = isNotNull<Int>().name to isNotNull<Int>()

        private fun isNotNullLess(plant: IAssertionPlantNullable<Int?>, number: Int)
            = plant.istNichtNull { istKleinerAls(number) }

        private fun isNotNullGreaterAndLess(plant: IAssertionPlantNullable<Int?>, lowerBound:Int, upperBound: Int)
            = plant.istNichtNull { istGroesserAls(lowerBound); istKleinerAls(upperBound) }


        private fun getNameIsA(): String {
            val f: IAssertionPlant<Any>.(IAssertionPlant<Any>.() -> Unit) -> Unit = IAssertionPlant<Any>::istEin
            return (f as KFunction<*>).name
        }

        private inline fun <reified TSub : Any> isA(plant: IAssertionPlant<Any>, noinline createAssertions: IAssertionPlant<TSub>.() -> Unit) {
            plant.istEin(createAssertions)
        }

        //TODO get rid of different overloads as soon as https://youtrack.jetbrains.com/issue/KT-19884 is fixed
        private fun isAInt(plant: IAssertionPlant<Any>, createAssertions: IAssertionPlant<Int>.() -> Unit)
            = isA(plant, createAssertions)

        private fun isAString(plant: IAssertionPlant<Any>, createAssertions: IAssertionPlant<String>.() -> Unit)
            = isA(plant, createAssertions)

        private fun isACharSequence(plant: IAssertionPlant<Any>, createAssertions: IAssertionPlant<CharSequence>.() -> Unit)
            = isA(plant, createAssertions)

        private fun isASubType(plant: IAssertionPlant<Any>, createAssertions: IAssertionPlant<SubType>.() -> Unit)
            = isA(plant, createAssertions)

        private fun isAIntLess(plant: IAssertionPlant<Number>, number: Int)
            = plant.istEin<Int> { istKleinerAls(number) }
    }
}
