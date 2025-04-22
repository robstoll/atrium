package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractArrayAsListExpectationsTest
import ch.tutteli.atrium.specs.notImplemented
import kotlin.test.Test

class ArrayAsListExpectationsTest : AbstractArrayAsListExpectationsTest(
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

        fun arrayIntWithCreator(expect: Expect<Array<Int>>, expectationCreator: Expect<List<Int>>.() -> Unit) =
            expect asList { expectationCreator() }

        fun byteArrayWithCreator(expect: Expect<ByteArray>, expectationCreator: Expect<List<Byte>>.() -> Unit) =
            expect asList { expectationCreator() }

        fun charArrayWithCreator(expect: Expect<CharArray>, expectationCreator: Expect<List<Char>>.() -> Unit) =
            expect asList { expectationCreator() }

        fun shortArrayWithCreator(expect: Expect<ShortArray>, expectationCreator: Expect<List<Short>>.() -> Unit) =
            expect asList { expectationCreator() }

        fun intArrayWithCreator(expect: Expect<IntArray>, expectationCreator: Expect<List<Int>>.() -> Unit) =
            expect asList { expectationCreator() }

        fun longArrayWithCreator(expect: Expect<LongArray>, expectationCreator: Expect<List<Long>>.() -> Unit) =
            expect asList { expectationCreator() }

        fun floatArrayWithCreator(expect: Expect<FloatArray>, expectationCreator: Expect<List<Float>>.() -> Unit) =
            expect asList { expectationCreator() }

        fun doubleArrayWithCreator(expect: Expect<DoubleArray>, expectationCreator: Expect<List<Double>>.() -> Unit) =
            expect asList { expectationCreator() }

        fun booleanArrayWithCreator(expect: Expect<BooleanArray>, expectationCreator: Expect<List<Boolean>>.() -> Unit) =
            expect asList { expectationCreator() }
    }

    @Suppress("UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var a1: Expect<Array<Int>> = expect(arrayOf(1))
        var a2: Expect<Array<out Int>> = expect(arrayOf(1))
        var a1b: Expect<Array<Int?>> = expect(arrayOf(1))
        var a2b: Expect<Array<out Int?>> = expect(arrayOf(1))

        var star: Expect<Array<*>> = expect(arrayOf(1))

        a1 asList o
        a2 asList o

        a1 = a1 asList { it toContain 1 }
        a2 = a2 asList { it toContain 1 }

        a1b asList o
        a2b asList o

        a1b = a1b asList { it toContain 1 }
        a2b = a2b asList { it toContain 1 }

        star asList o
        star = star asList { it toContain 1 }
    }
}
