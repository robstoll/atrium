@file:JvmMultifileClass
@file:JvmName("MapAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.builders.utils.subExpect
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
    val methodCallFormatter = ExpectImpl.coreFactory.newMethodCallFormatter()

    val assertion = ExpectImpl.collector.collect(assertionContainer) {
        keyValues.map { (key, assertionCreatorOrNull) ->
            ExpectImpl.feature.extractor(this)
                .withDescription(TranslatableWithArgs(ENTRY_WITH_KEY, methodCallFormatter.formatArgument(key)))
                .withRepresentationForFailure(KEY_DOES_NOT_EXIST)
                .withCheck { it.containsKey(key) }
                .withFeatureExtraction { it[key] }
                .build()
                .addToInitial {
                    addAssertion(ExpectImpl.any.toBeNullIfNullGivenElse(this, valueType, assertionCreatorOrNull))
                }
        }
    }
    return ExpectImpl.builder.list
        .withDescriptionAndEmptyRepresentation(CONTAINS_IN_ANY_ORDER)
        .withAssertion(assertion)
        .build()
}

fun <K> _containsKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, CONTAINS_KEY, key) { it.containsKey(key) }

fun <K> _containsNotKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, CONTAINS_NOT_KEY, key) { it.containsKey(key).not() }


fun _isEmpty(subjectProvider: SubjectProvider<Map<*, *>>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS, RawString.create(EMPTY)) { it.isEmpty() }

fun _isNotEmpty(subjectProvider: SubjectProvider<Map<*, *>>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_NOT, RawString.create(EMPTY)) { it.isNotEmpty() }

fun <K, V, T: Map<out K, V>> _getExisting(assertionContainer: Expect<T>, key: K): ExtractedFeaturePostStep<T, V> =
    ExpectImpl.feature.extractor(assertionContainer)
        .methodCall("get", key)
        .withRepresentationForFailure(KEY_DOES_NOT_EXIST)
        .withCheck { it.containsKey(key) }
        .withFeatureExtraction {
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
        .build()

fun <T : Map<*, *>> _size(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(assertionContainer, SIZE) { size }
