package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
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

        private inline fun <reified T : Any> isNotNull(): KFunction2<AssertionPlantNullable<T?>, AssertionPlant<T>.() -> Unit, Unit>
            = AssertionPlantNullable<T?>::notToBeNull

        private fun getIsNotNullPair() = isNotNull<Int>().name to isNotNull<Int>()

        private fun isNotNullLess(plant: AssertionPlantNullable<Int?>, number: Int)
            = plant notToBeNull { isLessThan(number) }

        private fun isNotNullGreaterAndLess(plant: AssertionPlantNullable<Int?>, lowerBound:Int, upperBound: Int)
            = plant notToBeNull { isGreaterThan(lowerBound); isLessThan(upperBound) }


        private fun getNameIsA(): String {
            val f: AssertionPlant<Any>.(AssertionPlant<Any>.() -> Unit) -> Unit = AssertionPlant<Any>::isA
            return (f as KFunction<*>).name
        }

        private inline fun <reified TSub : Any> isA(plant: AssertionPlant<Any>, noinline assertionCreator: AssertionPlant<TSub>.() -> Unit) {
            plant isA(assertionCreator)
        }

        //TODO get rid of different overloads as soon as https://youtrack.jetbrains.com/issue/KT-19884 is fixed
        private fun isAInt(plant: AssertionPlant<Any>, assertionCreator: AssertionPlant<Int>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isAString(plant: AssertionPlant<Any>, assertionCreator: AssertionPlant<String>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isACharSequence(plant: AssertionPlant<Any>, assertionCreator: AssertionPlant<CharSequence>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isASubType(plant: AssertionPlant<Any>, assertionCreator: AssertionPlant<SubType>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isAIntLess(plant: AssertionPlant<Number>, number: Int)
            //TODO change to infix as soon as https://youtrack.jetbrains.com/issue/KT-21593 is fixed
            = plant.isA<Int> { isLessThan(number) }
    }
}
