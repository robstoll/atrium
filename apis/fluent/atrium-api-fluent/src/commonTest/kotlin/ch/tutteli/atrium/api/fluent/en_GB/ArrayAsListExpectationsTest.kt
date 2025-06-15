package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractArrayAsListExpectationsTest
import ch.tutteli.atrium.specs.notImplemented
import kotlin.test.Test

class ArrayAsListExpectationsTest : AbstractArrayAsListExpectationsTest(
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

    @Suppress("UNUSED_VALUE", "AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var a1: Expect<Array<Int>> = expect(arrayOf(1))
        var a2: Expect<Array<out Int>> = expect(arrayOf(1))
        var a1b: Expect<Array<Int?>> = expect(arrayOf(1))
        var a2b: Expect<Array<out Int?>> = expect(arrayOf(1))

        var star: Expect<Array<*>> = expect(arrayOf(1))

        a1.asList()
        a2.asList()

        a1 = a1.asList { toContain(1) }
        a2 = a2.asList { toContain(1) }

        a1b.asList()
        a2b.asList()

        a1b = a1b.asList { toContain(1) }
        a2b = a2b.asList { toContain(1) }

        star.asList()
        star = star.asList { toContain(1) }
    }
}
