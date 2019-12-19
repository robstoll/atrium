package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

fun <K, T : Pair<K, *>> _first(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, K> =
    assertionContainer._domain.property(Pair<K, *>::first)

fun <V, T : Pair<*, V>> _second(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, V> =
    assertionContainer._domain.property(Pair<*, V>::second)
