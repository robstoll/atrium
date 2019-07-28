package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

object ArrayAsIterableAssertionsSpec : ch.tutteli.atrium.specs.integration.ArrayAsIterableAssertionsSpec(
    AssertionVerbFactory,
    "asIterable",
    Expect<Array<Int>>::asIterable,
    Expect<ByteArray>::asIterable,
    Expect<CharArray>::asIterable,
    Expect<ShortArray>::asIterable,
    Expect<IntArray>::asIterable,
    Expect<LongArray>::asIterable,
    Expect<FloatArray>::asIterable,
    Expect<DoubleArray>::asIterable,
    Expect<BooleanArray>::asIterable,

    Expect<Array<Int>>::asIterable,
    Expect<ByteArray>::asIterable,
    Expect<CharArray>::asIterable,
    Expect<ShortArray>::asIterable,
    Expect<IntArray>::asIterable,
    Expect<LongArray>::asIterable,
    Expect<FloatArray>::asIterable,
    Expect<DoubleArray>::asIterable,
    Expect<BooleanArray>::asIterable
) {


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Array<Int>> = notImplemented()
        var a2: Expect<Array<out Int>> = notImplemented()
        var a3: Expect<out Array<Int>> = notImplemented()
        var a4: Expect<out Array<out Int>> = notImplemented()

        a1.asIterable()
        a1 = a1.asIterable { }
        a2.asIterable()
        a2 = a2.asIterable { }
        a3.asIterable()
        a3 = a3.asIterable { }
        a4.asIterable()
        a4 = a4.asIterable { }
    }
}
