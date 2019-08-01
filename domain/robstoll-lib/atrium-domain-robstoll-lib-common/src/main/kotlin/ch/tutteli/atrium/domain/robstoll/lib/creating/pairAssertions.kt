package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

fun <K, T : Pair<K, *>> _first(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, K> =
    ExpectImpl.feature.property(assertionContainer, Pair<K, *>::first)

fun <V, T : Pair<*, V>> _second(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, V> =
    ExpectImpl.feature.property(assertionContainer, Pair<*, V>::second)
