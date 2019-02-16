package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction2


class TypeTransformationAssertionsSpec : ch.tutteli.atrium.spec.integration.TypeTransformationAssertionsSpec(
    AssertionVerbFactory,
    getNotToBeNullPair(),
    Companion::notToBeNullLess,
    Companion::notToBeNullGreaterAndLess,
    getNotToBeNullButPair(),
    getNameIsA(),
    Companion::isAInt,
    Companion::isAString,
    Companion::isACharSequence,
    Companion::isASubType,
    Companion::isAIntLess
) {
    companion object {

        private inline fun <reified T : Any> notToBeNull(): KFunction2<AssertionPlantNullable<T?>, Assert<T>.() -> Unit, Unit>
            = AssertionPlantNullable<T?>::notToBeNull

        private fun getNotToBeNullPair() = notToBeNull<Int>().name to notToBeNull<Int>()

        private fun notToBeNullLess(plant: AssertionPlantNullable<Int?>, number: Int)
            = plant.notToBeNull { isLessThan(number) }

        private fun notToBeNullGreaterAndLess(plant: AssertionPlantNullable<Int?>, lowerBound: Int, upperBound: Int)
            = plant.notToBeNull { isGreaterThan(lowerBound); isLessThan(upperBound) }

        @Suppress("DEPRECATION")
        private fun getNotToBeNullButPair()
            = AssertionPlantNullable<Int?>::notToBeNullBut.name to AssertionPlantNullable<Int?>::notToBeNullBut


        private fun getNameIsA(): String {
            val f: Assert<Any>.(Assert<Any>.() -> Unit) -> Unit = Assert<Any>::isA
            return (f as KFunction<*>).name
        }

        private inline fun <reified TSub : Any> isA(plant: Assert<Any>, noinline assertionCreator: Assert<TSub>.() -> Unit) {
            plant.isA(assertionCreator)
        }

        //TODO get rid of different overloads as soon as https://youtrack.jetbrains.com/issue/KT-19884 is fixed
        private fun isAInt(plant: Assert<Any>, assertionCreator: Assert<Int>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isAString(plant: Assert<Any>, assertionCreator: Assert<String>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isACharSequence(plant: Assert<Any>, assertionCreator: Assert<CharSequence>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isASubType(plant: Assert<Any>, assertionCreator: Assert<SubType>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isAIntLess(plant: Assert<Number>, number: Int)
            = plant.isA<Int> { isLessThan(number) }
    }
}
