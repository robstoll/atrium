package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

object ArrayAsListAssertionsSpec : ch.tutteli.atrium.specs.integration.ArrayAsListAssertionsSpec (
    "asIterable",
    Expect<Array<Int>>::asList,
    Expect<ByteArray>::asList,
    Expect<CharArray>::asList,
    Expect<ShortArray>::asList,
    Expect<IntArray>::asList,
    Expect<LongArray>::asList,
    Expect<FloatArray>::asList,
    Expect<DoubleArray>::asList,
    Expect<BooleanArray>::asList,

    Expect<Array<Int>>::asList,
    Expect<ByteArray>::asList,
    Expect<CharArray>::asList,
    Expect<ShortArray>::asList,
    Expect<IntArray>::asList,
    Expect<LongArray>::asList,
    Expect<FloatArray>::asList,
    Expect<DoubleArray>::asList,
    Expect<BooleanArray>::asList
) {


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Array<Int>> = notImplemented()
        var a2: Expect<Array<out Int>> = notImplemented()
        var a3: Expect<out Array<Int>> = notImplemented()
        var a4: Expect<out Array<out Int>> = notImplemented()

        a1.asList()
        a1 = a1.asList { }
        a2.asList()
        a2 = a2.asList { }
        a3.asList()
        a3 = a3.asList { }
        a4.asList()
        a4 = a4.asList { }
    }
}
