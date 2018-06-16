package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

object ArrayAsIterableAssertionsSpec : ch.tutteli.atrium.spec.integration.ArrayAsIterableAssertionsSpec(
    AssertionVerbFactory,
    Assert<Array<Int>>::asIterable.name,
    Assert<Array<Int>>::asIterable,
    Assert<ByteArray>::asIterable,
    Assert<CharArray>::asIterable,
    Assert<ShortArray>::asIterable,
    Assert<IntArray>::asIterable,
    Assert<LongArray>::asIterable,
    Assert<FloatArray>::asIterable,
    Assert<DoubleArray>::asIterable,
    Assert<BooleanArray>::asIterable
)
