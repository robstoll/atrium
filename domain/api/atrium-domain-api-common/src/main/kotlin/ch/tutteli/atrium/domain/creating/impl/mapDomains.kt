package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.utils.subExpect
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import kotlin.reflect.KClass

internal class MapDomainImpl<K, V, T : Map<out K, V>>(
    mapSubDomain: MapSubDomain<K, V, T>,
    anyDomain: AnyDomain<T>
) : MapDomain<K, V, T>, MapSubDomain<K, V, T> by mapSubDomain, AnyDomain<T> by anyDomain {
    override val expect: Expect<T> = mapSubDomain.expect
}

internal class MapSubDomainImpl<K, V, T : Map<out K, V>>(
    override val expect: Expect<T>
) : MapSubDomain<K, V, T> {

    override fun containsKey(key: K): Assertion =
        mapAssertions.containsKey(expect, key)

    override fun containsNotKey(key: K): Assertion =
        mapAssertions.containsNotKey(expect, key)

    override fun isEmpty(): Assertion =
        mapAssertions.isEmpty(expect)

    override fun isNotEmpty(): Assertion =
        mapAssertions.isNotEmpty(expect)

    override fun getExisting(key: K): ExtractedFeaturePostStep<T, V> =
        expect._domain.featureExtractor
            .methodCall("get", key)
            .withRepresentationForFailure(DescriptionMapAssertion.KEY_DOES_NOT_EXIST)
            .withFeatureExtraction {
                Option.someIf(it.containsKey(key)) {
                    @Suppress(
                        "UNCHECKED_CAST"
                        /*
                        UNCHECKED_CAST is OK as this function will only be called if the key exists, so the value should be V
                        One note though, if one deals with a Map returned by Java code and forgot that the Map
                        actually contains `null` as values as well, then we ignore it here (due to the UNCHECKED_CAST).
                        However, usually this should not matter as the assertion about the value will cover it.
                        In the worst case, a null-check included by the Kotlin compiler will throw -> in such a case
                        it might be hard for the user to grasp what is going on.
                        In this sense it might be better if we catch that already here and report accordingly.
                        Yet, in the end we end up introducing null-checks everywhere only because of Java => thus keep
                        it like this for now.
                        */
                    )
                    it[key] as V
                }
            }
            .withoutOptions()
            .build()

    override val size: ExtractedFeaturePostStep<T, Int>
        get() = expect._domain.manualFeature(DescriptionMapAssertion.SIZE) { size }
}


internal class MapValueAnyDomainImpl<K, V : Any, T : Map<out K, V>>(
    mapValueAnySubDomain: MapValueAnySubDomain<K, V, T>,
    mapDomain: MapDomain<K, V, T>
) : MapValueAnyDomain<K, V, T>,
    MapValueAnySubDomain<K, V, T> by mapValueAnySubDomain,
    MapDomain<K, V, T> by mapDomain {
    override val expect: Expect<T> = mapValueAnySubDomain.expect
}

internal class MapValueAnySubDomainImpl<K, V : Any, T : Map<out K, V>>(
    override val expect: Expect<T>
) : MapValueAnySubDomain<K, V, T> {

    override fun contains(keyValuePairs: List<Pair<K, V>>): Assertion =
        containsKeyWithValueAssertion(keyValuePairs.map {
            it.first to subExpect<V> { addAssertion(_domain.toBe(it.second)) }
        })

    override fun containsKeyWithValueAssertion(keyValueAssertionPairs: List<Pair<K, Expect<V>.() -> Unit>>): Assertion =
        containsKeyWithValueAssertion(expect, keyValueAssertionPairs) { subject, key ->
            val value = subject[key]
            if (value != null) Some(value) else None
        }
}

internal class MapValueNullableDomainImpl<K, V : Any, T : Map<out K, V?>>(
    mapValueNullableSubDomain: MapValueNullableSubDomain<K, V, T>,
    mapDomain: MapDomain<K, V?, T>
) : MapValueNullableDomain<K, V, T>,
    MapValueNullableSubDomain<K, V, T> by mapValueNullableSubDomain,
    MapDomain<K, V?, T> by mapDomain {
    override val expect: Expect<T> = mapValueNullableSubDomain.expect
}

internal class MapValueNullableSubDomainImpl<K, V : Any, T : Map<out K, V?>>(
    override val expect: Expect<T>
) : MapValueNullableSubDomain<K, V, T> {

    override fun contains(valueType: KClass<V>, keyValuePairs: List<Pair<K, V?>>): Assertion =
        containsKeyWithValueAssertion(valueType, keyValuePairs.map {
            it.first to it.second?.let { expected -> subExpect<V> { addAssertion(_domain.toBe(expected)) } }
        })

    override fun containsKeyWithValueAssertion(
        valueType: KClass<V>,
        keyValueAssertionPairs: List<Pair<K, (Expect<V>.() -> Unit)?>>
    ): Assertion {
        val transformedKeyValues = keyValueAssertionPairs.map {
            it.first to subExpect<V?> {
                addAssertion(_domainNullable.toBeNullIfNullGivenElse(valueType, it.second))
            }
        }
        return containsKeyWithValueAssertion(expect, transformedKeyValues) { subject, key ->
            Option.someIf(subject.containsKey(key)) { subject[key] }
        }
    }
}

private fun <K, V, T : Map<out K, V>, R> containsKeyWithValueAssertion(
    expect: Expect<T>,
    keyValues: List<Pair<K, Expect<R>.() -> Unit>>,
    extraction: (subject: T, key: K) -> Option<R>
): AssertionGroup {
    //TODO we should actually make MethodCallFormatter configurable in ReporterBuilder and then get it via Expect
    val methodCallFormatter = coreFactory.newMethodCallFormatter()

    val assertion = expect._domain.collector.collect {
        keyValues.map { (key, assertionCreator) ->
            _domain.featureExtractor
                .withDescription(
                    TranslatableWithArgs(
                        DescriptionMapAssertion.ENTRY_WITH_KEY,
                        methodCallFormatter.formatArgument(key)
                    )
                )
                .withRepresentationForFailure(DescriptionMapAssertion.KEY_DOES_NOT_EXIST)
                .withFeatureExtraction { extraction(it, key) }
                .withoutOptions()
                .build()
                .addToInitial(assertionCreator)
        }
    }
    return assertionBuilder.list
        .withDescriptionAndEmptyRepresentation(DescriptionMapAssertion.CONTAINS_IN_ANY_ORDER)
        .withAssertion(assertion)
        .build()
}
