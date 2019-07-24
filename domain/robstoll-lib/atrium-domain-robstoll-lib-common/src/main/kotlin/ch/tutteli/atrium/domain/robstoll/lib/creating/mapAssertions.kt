@file:JvmMultifileClass
@file:JvmName("MapAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.utils.subExpect
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
                .withSubAssertions {
                    addAssertion(ExpectImpl.any.toBeNullIfNullGivenElse(this, valueType, assertionCreatorOrNull))
                }
                .build()
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
