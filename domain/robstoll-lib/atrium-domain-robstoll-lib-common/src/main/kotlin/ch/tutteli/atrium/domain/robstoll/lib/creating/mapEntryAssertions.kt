package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating._domain
import ch.tutteli.atrium.domain.builders.creating._domainNullable
import ch.tutteli.atrium.domain.builders.creating.collector
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import kotlin.reflect.KClass

fun <K : Any, V : Any, T : Map.Entry<K, V>> _isKeyValue(assertionContainer: Expect<T>, key: K, value: V): Assertion =
    assertionContainer._domain.collector.collect {
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
    assertionContainer._domain.collector.collect {
        ExpectImpl.map.entry.key(this).addToInitial {
            addAssertion(_domainNullable.toBeNullable(keyType, key))
        }
        ExpectImpl.map.entry.value(this).addToInitial {
            addAssertion(_domainNullable.toBeNullable(valueType, value))
        }
    }

fun <K, T : Map.Entry<K, *>> _key(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, K> =
    assertionContainer._domain.property(Map.Entry<K, *>::key)

fun <V, T : Map.Entry<*, V>> _value(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, V> =
    assertionContainer._domain.property(Map.Entry<*, V>::value)


