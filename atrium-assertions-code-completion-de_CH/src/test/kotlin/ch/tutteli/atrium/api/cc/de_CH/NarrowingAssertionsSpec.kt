package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

class NarrowingAssertionsSpec : ch.tutteli.atrium.spec.assertions.NarrowingAssertionsSpec(
    AssertionVerbFactory,
    getIsNotNullTriple(),
    getIsNotNullLessPair(),
    getNameIsA(),
    getIsAIntPair(),
    getIsAStringPair(),
    getIsACharSequencePair(),
    getIsASubTypePair(),
    getIsAIntLessPair()
) {
    companion object {
        private inline fun <reified T : Any> isNotNull(): KFunction1<IAssertionPlantNullable<T?>, IAssertionPlant<T>>
            = IAssertionPlantNullable<T?>::istNichtNull

        private inline fun <reified T : Any> isNotNullLazy(): KFunction2<IAssertionPlantNullable<T?>, IAssertionPlant<T>.() -> Unit, IAssertionPlant<T>>
            = IAssertionPlantNullable<T?>::istNichtNull

        private fun getIsNotNullTriple() = Triple(isNotNull<Int>().name, isNotNull<Int>(), isNotNullLazy<Int>())

        private fun getIsNotNullLessPair() = Companion::isNotNullLess to Companion::isNotNullLessLazy

        private fun isNotNullLess(plant: IAssertionPlantNullable<Int?>, number: Int)
            = plant.istNichtNull().istWenigerAls(number)

        private fun isNotNullLessLazy(plant: IAssertionPlantNullable<Int?>, number: Int)
            = plant.istNichtNull { istWenigerAls(number) }


        private fun getNameIsA(): String {
            val f: IAssertionPlant<Any>.() -> IAssertionPlant<Int> = IAssertionPlant<Any>::istEin
            return (f as KFunction<*>).name
        }

        private inline fun <reified TSub : Any> isA(plant: IAssertionPlant<Any>): IAssertionPlant<TSub>
            = plant.istEin()

        private inline fun <reified TSub : Any> isALazy(plant: IAssertionPlant<Any>, noinline createAssertions: IAssertionPlant<TSub>.() -> Unit): IAssertionPlant<TSub>
            = plant.istEin(createAssertions)

        //TODO get rid of different overloads as soon as https://youtrack.jetbrains.com/issue/KT-19884 is fixed
        private fun getIsAIntPair() = Companion::isAInt to Companion::isAIntLazy
        private fun isAInt(plant: IAssertionPlant<Any>) = isA<Int>(plant)
        private fun isAIntLazy(plant: IAssertionPlant<Any>, createAssertions: IAssertionPlant<Int>.() -> Unit)
            = isALazy(plant, createAssertions)

        private fun getIsAStringPair() = Companion::isAString to Companion::isAStringLazy
        private fun isAString(plant: IAssertionPlant<Any>) = isA<String>(plant)
        private fun isAStringLazy(plant: IAssertionPlant<Any>, createAssertions: IAssertionPlant<String>.() -> Unit)
            = isALazy(plant, createAssertions)

        private fun getIsACharSequencePair() = Companion::isACharSequence to Companion::isACharSequenceLazy
        private fun isACharSequence(plant: IAssertionPlant<Any>) = isA<CharSequence>(plant)
        private fun isACharSequenceLazy(plant: IAssertionPlant<Any>, createAssertions: IAssertionPlant<CharSequence>.() -> Unit)
            = isALazy(plant, createAssertions)

        private fun getIsASubTypePair() = Companion::isASubType to Companion::isASubTypeLazy
        private fun isASubType(plant: IAssertionPlant<Any>) = isA<SubType>(plant)
        private fun isASubTypeLazy(plant: IAssertionPlant<Any>, createAssertions: IAssertionPlant<SubType>.() -> Unit)
            = isALazy(plant, createAssertions)

        private fun getIsAIntLessPair() = Companion::isAIntLess to Companion::isAIntLessLazy

        private fun isAIntLess(plant: IAssertionPlant<Number>, number: Int)
            = plant.istEin<Int>().istWenigerAls(number)

        private fun isAIntLessLazy(plant: IAssertionPlant<Number>, number: Int)
            = plant.istEin<Int> { istWenigerAls(number) }
    }
}
