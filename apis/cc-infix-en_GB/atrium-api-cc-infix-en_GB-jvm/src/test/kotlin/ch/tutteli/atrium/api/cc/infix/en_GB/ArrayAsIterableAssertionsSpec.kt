@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.asList
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO remove with 1.0.0, no need to migrate to Spek 2
class ArrayAsIterableAssertionsSpec : ch.tutteli.atrium.spec.integration.ArrayAsIterableAssertionsSpec(
    AssertionVerbFactory,
    "asIterable",
    Companion::arrayInt,
    Companion::byteArray,
    Companion::charArray,
    Companion::shortArray,
    Companion::intArray,
    Companion::longArray,
    Companion::floatArray,
    Companion::doubleArray,
    Companion::booleanArray,
    Companion::arrayIntWithCreator,
    Companion::byteArrayWithCreator,
    Companion::charArrayWithCreator,
    Companion::shortArrayWithCreator,
    Companion::intArrayWithCreator,
    Companion::longArrayWithCreator,
    Companion::floatArrayWithCreator,
    Companion::doubleArrayWithCreator,
    Companion::booleanArrayWithCreator
){
    companion object {
        fun arrayInt(plant: Assert<Array<Int>>) = plant.asExpect<Array<out Int>, Assert<Array<out Int>>>().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert()
        fun byteArray(plant: Assert<ByteArray>) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert()
        fun charArray(plant: Assert<CharArray>) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert()
        fun shortArray(plant: Assert<ShortArray>) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert()
        fun intArray(plant: Assert<IntArray>) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert()
        fun longArray(plant: Assert<LongArray>) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert()
        fun floatArray(plant: Assert<FloatArray>) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert()
        fun doubleArray(plant: Assert<DoubleArray>) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert()
        fun booleanArray(plant: Assert<BooleanArray>) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert()


        fun arrayIntWithCreator(plant: Assert<Array<Int>>, assertionCreator: Assert<Iterable<Int>>.() -> Unit) = plant.asExpect<Array<out Int>, Assert<Array<out Int>>>().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert(assertionCreator)
        fun byteArrayWithCreator(plant: Assert<ByteArray>, assertionCreator: Assert<Iterable<Byte>>.() -> Unit) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert(assertionCreator)
        fun charArrayWithCreator(plant: Assert<CharArray>, assertionCreator: Assert<Iterable<Char>>.() -> Unit) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert(assertionCreator)
        fun shortArrayWithCreator(plant: Assert<ShortArray>, assertionCreator: Assert<Iterable<Short>>.() -> Unit) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert(assertionCreator)
        fun intArrayWithCreator(plant: Assert<IntArray>, assertionCreator: Assert<Iterable<Int>>.() -> Unit) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert(assertionCreator)
        fun longArrayWithCreator(plant: Assert<LongArray>, assertionCreator: Assert<Iterable<Long>>.() -> Unit) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert(assertionCreator)
        fun floatArrayWithCreator(plant: Assert<FloatArray>, assertionCreator: Assert<Iterable<Float>>.() -> Unit) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert(assertionCreator)
        fun doubleArrayWithCreator(plant: Assert<DoubleArray>, assertionCreator: Assert<Iterable<Double>>.() -> Unit) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert(assertionCreator)
        fun booleanArrayWithCreator(plant: Assert<BooleanArray>, assertionCreator: Assert<Iterable<Boolean>>.() -> Unit) = plant.asExpect().asList(ch.tutteli.atrium.api.infix.en_GB.o).asAssert(assertionCreator)
    }
}
