package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

class ArrayAsListAssertionsSpec : ch.tutteli.atrium.specs.integration.ArrayAsListAssertionsSpec(
    "asList",
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
) {

    companion object {
        fun arrayInt(plant: Expect<Array<Int>>) = plant asList o
        fun byteArray(plant: Expect<ByteArray>) = plant asList o
        fun charArray(plant: Expect<CharArray>) = plant asList o
        fun shortArray(plant: Expect<ShortArray>) = plant asList o
        fun intArray(plant: Expect<IntArray>) = plant asList o
        fun longArray(plant: Expect<LongArray>) = plant asList o
        fun floatArray(plant: Expect<FloatArray>) = plant asList o
        fun doubleArray(plant: Expect<DoubleArray>) = plant asList o
        fun booleanArray(plant: Expect<BooleanArray>) = plant asList o

        fun arrayIntWithCreator(plant: Expect<Array<Int>>, assertionCreator: Expect<List<Int>>.() -> Unit) =
            plant asList assertionCreator
        fun byteArrayWithCreator(plant: Expect<ByteArray>, assertionCreator: Expect<List<Byte>>.() -> Unit) =
            plant asList assertionCreator
        fun charArrayWithCreator(plant: Expect<CharArray>, assertionCreator: Expect<List<Char>>.() -> Unit) =
            plant asList assertionCreator
        fun shortArrayWithCreator(plant: Expect<ShortArray>, assertionCreator: Expect<List<Short>>.() -> Unit) =
            plant asList assertionCreator
        fun intArrayWithCreator(plant: Expect<IntArray>, assertionCreator: Expect<List<Int>>.() -> Unit) =
            plant asList assertionCreator
        fun longArrayWithCreator(plant: Expect<LongArray>, assertionCreator: Expect<List<Long>>.() -> Unit) =
            plant asList assertionCreator
        fun floatArrayWithCreator(plant: Expect<FloatArray>, assertionCreator: Expect<List<Float>>.() -> Unit) =
            plant asList  assertionCreator
        fun doubleArrayWithCreator(plant: Expect<DoubleArray>, assertionCreator: Expect<List<Double>>.() -> Unit) =
            plant asList  assertionCreator
        fun booleanArrayWithCreator(plant: Expect<BooleanArray>, assertionCreator: Expect<List<Boolean>>.() -> Unit) =
            plant asList  assertionCreator
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Array<Int>> = notImplemented()
        var a2: Expect<Array<out Int>> = notImplemented()
        var a1b: Expect<Array<Int?>> = notImplemented()
        var a2b: Expect<Array<out Int?>> = notImplemented()

        var star: Expect<Array<*>> = notImplemented()

        a1 asList o
        a2 asList o

        a1 = a1 asList { }
        a2 = a2 asList { }

        a1b asList o
        a2b asList o

        a1b = a1b asList { }
        a2b = a2b asList { }

        star asList o
        star = star asList { }
    }
}
