@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

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
        fun arrayInt(plant: Assert<Array<Int>>) = plant.asIterable()
        fun byteArray(plant: Assert<ByteArray>) = plant.asIterable()
        fun charArray(plant: Assert<CharArray>) = plant.asIterable()
        fun shortArray(plant: Assert<ShortArray>) = plant.asIterable()
        fun intArray(plant: Assert<IntArray>) = plant.asIterable()
        fun longArray(plant: Assert<LongArray>) = plant.asIterable()
        fun floatArray(plant: Assert<FloatArray>) = plant.asIterable()
        fun doubleArray(plant: Assert<DoubleArray>) = plant.asIterable()
        fun booleanArray(plant: Assert<BooleanArray>) = plant.asIterable()


        fun arrayIntWithCreator(plant: Assert<Array<Int>>, assertionCreator: Assert<Iterable<Int>>.() -> Unit) = plant asIterable assertionCreator
        fun byteArrayWithCreator(plant: Assert<ByteArray>, assertionCreator: Assert<Iterable<Byte>>.() -> Unit) = plant asIterable assertionCreator
        fun charArrayWithCreator(plant: Assert<CharArray>, assertionCreator: Assert<Iterable<Char>>.() -> Unit) = plant asIterable assertionCreator
        fun shortArrayWithCreator(plant: Assert<ShortArray>, assertionCreator: Assert<Iterable<Short>>.() -> Unit) = plant asIterable assertionCreator
        fun intArrayWithCreator(plant: Assert<IntArray>, assertionCreator: Assert<Iterable<Int>>.() -> Unit) = plant asIterable assertionCreator
        fun longArrayWithCreator(plant: Assert<LongArray>, assertionCreator: Assert<Iterable<Long>>.() -> Unit) = plant asIterable assertionCreator
        fun floatArrayWithCreator(plant: Assert<FloatArray>, assertionCreator: Assert<Iterable<Float>>.() -> Unit) = plant asIterable assertionCreator
        fun doubleArrayWithCreator(plant: Assert<DoubleArray>, assertionCreator: Assert<Iterable<Double>>.() -> Unit) = plant asIterable assertionCreator
        fun booleanArrayWithCreator(plant: Assert<BooleanArray>, assertionCreator: Assert<Iterable<Boolean>>.() -> Unit) = plant asIterable assertionCreator
    }
}
