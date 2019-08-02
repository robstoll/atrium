package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import kotlin.reflect.KClass

fun <K : Any, V : Any, T : Map.Entry<K, V>> _isKeyValue(assertionContainer: Expect<T>, key: K, value: V): Assertion =
    ExpectImpl.collector.collect(assertionContainer) {
        ExpectImpl.map.entry.key(this).addToInitial { toBe(key) }
        ExpectImpl.map.entry.value(this).addToInitial { toBe(value) }
    }

fun <K : Any, V : Any, T : Map.Entry<K?, V?>> _isKeyValue(
    assertionContainer: Expect<T>,
    key: K?,
    value: V?,
    keyType: KClass<K>,
    valueType: KClass<V>
): Assertion =
    ExpectImpl.collector.collect(assertionContainer) {
        ExpectImpl.map.entry.key(this).addToInitial {
            addAssertion(ExpectImpl.any.toBeNullable(this, keyType, key))
        }
        ExpectImpl.map.entry.value(this).addToInitial {
            addAssertion(ExpectImpl.any.toBeNullable(this, valueType, value))
        }
    }

fun <K, T : Map.Entry<K, *>> _key(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, K> =
    ExpectImpl.feature.property(assertionContainer, Map.Entry<K, *>::key)

fun <V, T : Map.Entry<*, V>> _value(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, V> =
    ExpectImpl.feature.property(assertionContainer, Map.Entry<*, V>::value)


