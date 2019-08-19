@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object ArrayAsIterableAssertionsSpec : ch.tutteli.atrium.spec.integration.ArrayAsIterableAssertionsSpec(
    AssertionVerbFactory,
    "asIterable",
    Assert<Array<Int>>::asIterable,
    Assert<ByteArray>::asIterable,
    Assert<CharArray>::asIterable,
    Assert<ShortArray>::asIterable,
    Assert<IntArray>::asIterable,
    Assert<LongArray>::asIterable,
    Assert<FloatArray>::asIterable,
    Assert<DoubleArray>::asIterable,
    Assert<BooleanArray>::asIterable,
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
