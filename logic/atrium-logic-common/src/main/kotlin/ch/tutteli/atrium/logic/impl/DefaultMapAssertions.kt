package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY
import ch.tutteli.atrium.translations.DescriptionMapAssertion.*
import kotlin.reflect.KClass

/**
 * Defines the minimum set of assertion functions and builders applicable to [Map],
 * which an implementation of the domain of Atrium has to provide.
 */
class DefaultMapAssertions : MapAssertions {
    override fun <K, V, T : Map<out K, V>> contains(
        container: AssertionContainer<T>,
        keyValuePairs: List<Pair<K, V>>
    ): Assertion = containsKeyWithValueAssertion(container, keyValuePairs.map { (key, value) ->
        key to expectLambda<V> { _logicAppend { toBe(value) } }
    })

    override fun <K, V : Any, T : Map<out K, V?>> containsKeyWithValueAssertions(
        container: AssertionContainer<T>,
        valueType: KClass<V>,
        keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>
    ): Assertion = containsKeyWithValueAssertion(container, keyValues.map { (key, assertionCreatorOrNull) ->
        key to expectLambda<V?> { _logicAppend { toBeNullIfNullGivenElse(valueType, assertionCreatorOrNull) } }
    })

    private fun <K, V, T : Map<out K, V>> containsKeyWithValueAssertion(
        container: AssertionContainer<T>,
        keyValues: List<Pair<K, Expect<V>.() -> Unit>>
    ): Assertion {
        //TODO we should actually make MethodCallFormatter configurable in ReporterBuilder and then get it via Expect
        val methodCallFormatter = coreFactory.newMethodCallFormatter()

        val assertion = container.collect {
            keyValues.forEach { (key, assertionCreator) ->
                _logic.extractFeature
                    .withDescription(TranslatableWithArgs(ENTRY_WITH_KEY, methodCallFormatter.formatArgument(key)))
                    .withRepresentationForFailure(KEY_DOES_NOT_EXIST)
                    .withFeatureExtraction { extractKey(it, key) }
                    .withoutOptions()
                    .build()
                    .addToInitial(assertionCreator)
            }
        }
        return assertionBuilder.list
            .withDescriptionAndEmptyRepresentation(CONTAINS_IN_ANY_ORDER)
            .withAssertion(assertion)
            .build()
    }

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

    override fun <K, T : Map<out K, *>> containsKey(container: AssertionContainer<T>, key: K): Assertion =
        container.createDescriptiveAssertion(CONTAINS_KEY, key) { it.containsKey(key) }

    override fun <K, T : Map<out K, *>> containsNotKey(container: AssertionContainer<T>, key: K): Assertion =
        container.createDescriptiveAssertion(CONTAINS_NOT_KEY, key) { it.containsKey(key).not() }

    override fun <T : Map<*, *>> isEmpty(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(IS, EMPTY) { it.isEmpty() }

    override fun <T : Map<*, *>> isNotEmpty(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(IS_NOT, EMPTY) { it.isNotEmpty() }

    override fun <K, V, T : Map<out K, V>> getExisting(
        container: AssertionContainer<T>,
        key: K
    ): ExtractedFeaturePostStep<T, V> =
        container.extractFeature
            .methodCall("get", key)
            .withRepresentationForFailure(KEY_DOES_NOT_EXIST)
            .withFeatureExtraction { extractKey(it, key) }
            .withoutOptions()
            .build()


    override fun <T : Map<*, *>> size(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, Int> =
        container.manualFeature(SIZE) { size }
}
