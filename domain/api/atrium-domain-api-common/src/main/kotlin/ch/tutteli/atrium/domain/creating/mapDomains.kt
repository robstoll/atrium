@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.impl.*
import ch.tutteli.atrium.domain.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.creating.impl.MapDomainImpl
import ch.tutteli.atrium.domain.creating.impl.MapSubDomainImpl
import ch.tutteli.atrium.domain.creating.impl.MapValueAnySubDomainImpl
import ch.tutteli.atrium.domain.creating.impl.MapValueNullableDomainImpl
import ch.tutteli.atrium.domain.creating.impl.MapValueNullableSubDomainImpl
import kotlin.js.JsName
import kotlin.reflect.KClass

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [Map];
 * i.e. it returns a [MapDomain] for this [Expect].
 */
val <K, V, T : Map<out K, V>> Expect<T>._domain: MapDomain<K, V, T>
    get() = MapDomainImpl(MapSubDomainImpl(this), AnyDomainImpl(this, AnyInclNullableDomainImpl(this)))

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [Map] with a value type extending [Any];
 * i.e. it returns a [MapValueAnyDomain] for this [Expect].
 */
val <K, V : Any, T : Map<out K, V>> Expect<T>._domain: MapValueAnyDomain<K, V, T>
    @JsName("_domainMapValueAny")
    get() = MapValueAnyDomainImpl(
        MapValueAnySubDomainImpl(this),
        MapDomainImpl(MapSubDomainImpl(this), AnyDomainImpl(this, AnyInclNullableDomainImpl(this)))
    )

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [Map] with a nullable value type;
 * i.e. it returns a [MapValueNullableDomain] for this [Expect].
 */
val <K, V : Any, T : Map<out K, V?>> Expect<T>._domainNullable: MapValueNullableDomain<K, V, T>
    get() = MapValueNullableDomainImpl(
        MapValueNullableSubDomainImpl(this),
        MapDomainImpl(MapSubDomainImpl(this), AnyDomainImpl(this, AnyInclNullableDomainImpl(this)))
    )

/**
 * Represents the [ExpectDomain] whose type extends [Map];
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface MapDomain<K, V, T : Map<out K, V>> : MapSubDomain<K, V, T>, AnyDomain<T>

/**
 * Represents a sub-[ExpectDomain] whose type extends [Map]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [Map] (e.g. the functions of the [AnyDomain]).
 */
interface MapSubDomain<K, V, T : Map<out K, V>> : ExpectDomain<T> {

    fun containsKey(key: K): Assertion
    fun containsNotKey(key: K): Assertion

    fun isEmpty(): Assertion
    fun isNotEmpty(): Assertion

    fun getExisting(key: K): ExtractedFeaturePostStep<T, V>

    val size: ExtractedFeaturePostStep<T, Int>
}

/**
 * Represents the [ExpectDomain] whose type extends [Map] with a value type extending [Any];
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface MapValueAnyDomain<K, V : Any, T : Map<out K, V>> :
    MapValueAnySubDomain<K, V, T>,
    MapDomain<K, V, T>

/**
 * Represents a sub-[ExpectDomain] whose type extends [Map] with a value type extending [Any]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [Map] (e.g. the functions of the [AnyDomain]).
 */
interface MapValueAnySubDomain<K, V : Any, T : Map<out K, V>> : ExpectDomain<T> {

    fun contains(keyValuePairs: List<Pair<K, V>>): Assertion
    fun containsKeyWithValueAssertion(keyValueAssertionPairs: List<Pair<K, Expect<V>.() -> Unit>>): Assertion
}

/**
 * Represents the [ExpectDomain] whose type extends [Map] with a nullable value type;
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface MapValueNullableDomain<K, V : Any, T : Map<out K, V?>> :
    MapValueNullableSubDomain<K, V, T>,
    MapDomain<K, V?, T>

/**
 * Represents a sub-[ExpectDomain] whose type extends [Map] and the [Map.values] type is a nullable type
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [Map] (e.g. the functions of the [AnyDomain]).
 */
interface MapValueNullableSubDomain<K, V : Any, T : Map<out K, V?>> : ExpectDomain<T> {
    fun contains(
        valueType: KClass<V>,
        keyValuePairs: List<Pair<K, V?>>
    ): Assertion

    fun containsKeyWithValueAssertion(
        valueType: KClass<V>,
        keyValueAssertionPairs: List<Pair<K, (Expect<V>.() -> Unit)?>>
    ): Assertion
}
