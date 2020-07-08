package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <K, T : Pair<K, *>> _first(expect: Expect<T>): ExtractedFeaturePostStep<T, K> =
    ExpectImpl.feature.property(expect, Pair<K, *>::first)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <V, T : Pair<*, V>> _second(expect: Expect<T>): ExtractedFeaturePostStep<T, V> =
    ExpectImpl.feature.property(expect, Pair<*, V>::second)
