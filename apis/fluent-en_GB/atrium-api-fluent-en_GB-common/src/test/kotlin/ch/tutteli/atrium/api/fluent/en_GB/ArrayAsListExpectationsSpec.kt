package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

object ArrayAsListAssertionsSpec : ch.tutteli.atrium.specs.integration.ArrayAsListAssertionsSpec(
    "asList",
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
        var a1b: Expect<Array<Int?>> = notImplemented()
        var a2b: Expect<Array<out Int?>> = notImplemented()

        var star: Expect<Array<*>> = notImplemented()

        a1.asList()
        a2.asList()

        a1 = a1.asList { }
        a2 = a2.asList { }

        a1b.asList()
        a2b.asList()

        a1b = a1b.asList { }
        a2b = a2b.asList { }

        star.asList()
        star = star.asList {}
    }
}
