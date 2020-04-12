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
        fun arrayInt(expect: Expect<Array<Int>>) = expect asList o
        fun byteArray(expect: Expect<ByteArray>) = expect asList o
        fun charArray(expect: Expect<CharArray>) = expect asList o
        fun shortArray(expect: Expect<ShortArray>) = expect asList o
        fun intArray(expect: Expect<IntArray>) = expect asList o
        fun longArray(expect: Expect<LongArray>) = expect asList o
        fun floatArray(expect: Expect<FloatArray>) = expect asList o
        fun doubleArray(expect: Expect<DoubleArray>) = expect asList o
        fun booleanArray(expect: Expect<BooleanArray>) = expect asList o

        fun arrayIntWithCreator(expect: Expect<Array<Int>>, assertionCreator: Expect<List<Int>>.() -> Unit) =
            expect asList { assertionCreator() }
        fun byteArrayWithCreator(expect: Expect<ByteArray>, assertionCreator: Expect<List<Byte>>.() -> Unit) =
            expect asList { assertionCreator() }
        fun charArrayWithCreator(expect: Expect<CharArray>, assertionCreator: Expect<List<Char>>.() -> Unit) =
            expect asList { assertionCreator() }
        fun shortArrayWithCreator(expect: Expect<ShortArray>, assertionCreator: Expect<List<Short>>.() -> Unit) =
            expect asList { assertionCreator() }
        fun intArrayWithCreator(expect: Expect<IntArray>, assertionCreator: Expect<List<Int>>.() -> Unit) =
            expect asList { assertionCreator() }
        fun longArrayWithCreator(expect: Expect<LongArray>, assertionCreator: Expect<List<Long>>.() -> Unit) =
            expect asList { assertionCreator() }
        fun floatArrayWithCreator(expect: Expect<FloatArray>, assertionCreator: Expect<List<Float>>.() -> Unit) =
            expect asList  { assertionCreator() }
        fun doubleArrayWithCreator(expect: Expect<DoubleArray>, assertionCreator: Expect<List<Double>>.() -> Unit) =
            expect asList  { assertionCreator() }
        fun booleanArrayWithCreator(expect: Expect<BooleanArray>, assertionCreator: Expect<List<Boolean>>.() -> Unit) =
            expect asList  { assertionCreator() }
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
