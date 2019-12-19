@file:JvmMultifileClass
@file:JvmName("MapAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.utils.subExpect
import ch.tutteli.atrium.domain.creating._domain
import ch.tutteli.atrium.domain.creating._domainNullable
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY
import ch.tutteli.atrium.translations.DescriptionMapAssertion.*
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.reflect.KClass

fun <K, V : Any, T : Map<out K, V?>> _contains(
    assertionContainer: Expect<T>,
    valueType: KClass<V>,
    pairs: List<Pair<K, V?>>
): Assertion = _containsKeyWithValueAssertion(assertionContainer, valueType, pairs.map {
    it.first to it.second?.let { expected -> subExpect<V> { toBe(expected) } }
})

fun <K, V : Any, T : Map<out K, V?>> _containsKeyWithValueAssertion(
    assertionContainer: Expect<T>,
    valueType: KClass<V>,
    keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>
): Assertion {
    //TODO we should actually make MethodCallFormatter configurable in ReporterBuilder and then get it via Expect
    val methodCallFormatter = coreFactory.newMethodCallFormatter()

    val assertion = assertionContainer._domain.collector.collect {
        keyValues.map { (key, assertionCreatorOrNull) ->
            _domain.featureExtractor
                .withDescription(TranslatableWithArgs(ENTRY_WITH_KEY, methodCallFormatter.formatArgument(key)))
                .withRepresentationForFailure(KEY_DOES_NOT_EXIST)
                .withFeatureExtraction { Option.someIf(it.containsKey(key)) { it[key] } }
                .withoutOptions()
                .build()
                .addToInitial {
                    addAssertion(_domainNullable.toBeNullIfNullGivenElse(valueType, assertionCreatorOrNull))
                }
        }
    }
    return assertionBuilder.list
        .withDescriptionAndEmptyRepresentation(CONTAINS_IN_ANY_ORDER)
        .withAssertion(assertion)
        .build()
}

fun <K> _containsKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K): Assertion =
    assertionBuilder.createDescriptive(subjectProvider, CONTAINS_KEY, key) { it.containsKey(key) }

fun <K> _containsNotKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K): Assertion =
    assertionBuilder.createDescriptive(subjectProvider, CONTAINS_NOT_KEY, key) { it.containsKey(key).not() }


fun _isEmpty(subjectProvider: SubjectProvider<Map<*, *>>): Assertion =
    assertionBuilder.createDescriptive(subjectProvider, IS, RawString.create(EMPTY)) { it.isEmpty() }

fun _isNotEmpty(subjectProvider: SubjectProvider<Map<*, *>>): Assertion =
    assertionBuilder.createDescriptive(subjectProvider, IS_NOT, RawString.create(EMPTY)) { it.isNotEmpty() }

fun <K, V, T : Map<out K, V>> _getExisting(assertionContainer: Expect<T>, key: K): ExtractedFeaturePostStep<T, V> =
    assertionContainer._domain.featureExtractor
        .methodCall("get", key)
        .withRepresentationForFailure(KEY_DOES_NOT_EXIST)
        .withFeatureExtraction {
            Option.someIf(it.containsKey(key)) {
                @Suppress(
                    "UNCHECKED_CAST"
                    /*
                    UNCHECKED_CAST is OK as this function will only be called if the key exists, so the value should be V
                    One note though, if one deals with a Map returned by Java code and forgot that the Map actually contains
                    `null` as values as well, then we ignore it here (due to the UNCHECKED_CAST). However, usually this
                    should not matter as the assertion about the value will cover it. In the worst case, a null-check included
                    by the Kotlin compiler will throw -> in such a case it might be hard for the user to grasp what is going on.
                    In this sense it might be better if we catch that already here and report accordingly. Yet, in the end we
                    end up introducing null-checks everywhere only because of Java => keep it like this for now.
                    */
                )
                it[key] as V
            }
        }
        .withoutOptions()
        .build()

fun <T : Map<*, *>> _size(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    assertionContainer._domain.manualFeature(SIZE) { size }
