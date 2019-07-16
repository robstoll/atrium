package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

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
)
