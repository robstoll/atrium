package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Feature0
import ch.tutteli.atrium.specs.Fun1

abstract class PairAssertionsSpec(
    firstFeature: Feature0<Pair<String, Int>, String>,
    first: Fun1<Pair<String, Int>, Expect<String>.() -> Unit>,
    secondFeature: Feature0<Pair<String, Int>, Int>,
    second: Fun1<Pair<String, Int>, Expect<Int>.() -> Unit>,
    nullableFirstFeature: Feature0<Pair<String?, Int?>, String?>,
    nullableFirst: Fun1<Pair<String?, Int?>, Expect<String?>.() -> Unit>,
    nullableSecondFeature: Feature0<Pair<String?, Int?>, Int?>,
    nullableSecond: Fun1<Pair<String?, Int?>, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : KeyValueLikeAssertionsSpec<Pair<String, Int>, Pair<String?, Int?>>(
    ::Pair,
    ::Pair,
    "first",
    "second",
    firstFeature,
    first,
    secondFeature,
    second,
    nullableFirstFeature,
    nullableFirst,
    nullableSecondFeature,
    nullableSecond,
    describePrefix
)
