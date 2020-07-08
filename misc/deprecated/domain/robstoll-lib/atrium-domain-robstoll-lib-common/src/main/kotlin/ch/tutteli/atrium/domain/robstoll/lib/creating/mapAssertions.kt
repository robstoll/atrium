//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
@file:JvmMultifileClass
@file:JvmName("MapAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.utils.subExpect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY
import ch.tutteli.atrium.translations.DescriptionMapAssertion.*
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.reflect.KClass

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <K, V, T : Map<out K, V>> _contains(
    expect: Expect<T>,
    pairs: List<Pair<K, V>>
): Assertion = _containsKeyWithValueAssertion(expect, pairs.map { (key, value) ->
    key to subExpect<V> { toBe(value) }
})

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <K, V : Any, T : Map<out K, V?>> _containsKeyWithValueAssertion(
    expect: Expect<T>,
    valueType: KClass<V>,
    keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>
): Assertion = _containsKeyWithValueAssertion(expect, keyValues.map { (key, assertionCreatorOrNull) ->
    key to subExpect<V?> {
        addAssertion(ExpectImpl.any.toBeNullIfNullGivenElse(this, valueType, assertionCreatorOrNull))
    }
})

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <K, V, T : Map<out K, V>> _containsKeyWithValueAssertion(
    expect: Expect<T>,
    keyValues: List<Pair<K, Expect<V>.() -> Unit>>
): Assertion {
    //TODO we should actually make MethodCallFormatter configurable in ReporterBuilder and then get it via Expect
    val methodCallFormatter = coreFactory.newMethodCallFormatter()

    val assertion = ExpectImpl.collector.collect(expect) {
        keyValues.forEach { (key, assertionCreator) ->
            ExpectImpl.feature.extractor(this)
                .withDescription(TranslatableWithArgs(ENTRY_WITH_KEY, methodCallFormatter.formatArgument(key)))
                .withRepresentationForFailure(KEY_DOES_NOT_EXIST)
                .withFeatureExtraction { extractKey(it, key) }
                .withoutOptions()
                .build()
                .addToInitial(assertionCreator)
        }
    }
    return ExpectImpl.builder.list
        .withDescriptionAndEmptyRepresentation(CONTAINS_IN_ANY_ORDER)
        .withAssertion(assertion)
        .build()
}

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <K> _containsKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, CONTAINS_KEY, key) { it.containsKey(key) }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <K> _containsNotKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, CONTAINS_NOT_KEY, key) { it.containsKey(key).not() }


@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun _isEmpty(subjectProvider: SubjectProvider<Map<*, *>>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS, EMPTY) { it.isEmpty() }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun _isNotEmpty(subjectProvider: SubjectProvider<Map<*, *>>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_NOT, EMPTY) { it.isNotEmpty() }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <K, V, T : Map<out K, V>> _getExisting(expect: Expect<T>, key: K): ExtractedFeaturePostStep<T, V> =
    ExpectImpl.feature.extractor(expect)
        .methodCall("get", key)
        .withRepresentationForFailure(KEY_DOES_NOT_EXIST)
        .withFeatureExtraction { extractKey(it, key) }
        .withoutOptions()
        .build()

private fun <K, T : Map<out K, V>, V> extractKey(it: T, key: K): Option<V> {
    return Option.someIf(it.containsKey(key)) {
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

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Map<*, *>> _size(expect: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(expect, SIZE) { size }
