package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.MapLikeExpectations
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.impl.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.maplike.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.translations.DescriptionMapLikeAssertion.*

class DefaultMapLikeExpectations : MapLikeExpectations {
    override fun <T : MapLike, K, V> builderContainsInMapLike(
        container: AssertionContainer<T>,
        converter: (T) -> Map<out K, V>
    ): MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour> =
        EntryPointStepImpl(container, converter, NoOpSearchBehaviourImpl())


    override fun <K, T : MapLike> containsKey(
        container: AssertionContainer<T>,
        converter: (T) -> Map<out K, *>,
        key: K
    ): Assertion =
        container.createDescriptiveAssertion(CONTAINS_KEY, key) {
            converter(it).containsKey(key)
        }

    override fun <K, T : MapLike> containsNotKey(
        container: AssertionContainer<T>,
        converter: (T) -> Map<out K, *>,
        key: K
    ): Assertion =
        container.createDescriptiveAssertion(CONTAINS_NOT_KEY, key) {
            converter(it).containsKey(key).not()
        }

    override fun <K, V, T : MapLike> getExisting(
        container: AssertionContainer<T>,
        converter: (T) -> Map<out K, V>,
        key: K
    ): FeatureExtractorBuilder.ExecutionStep<T, V> =
        container.extractFeature
            .methodCall("get", key)
            .withRepresentationForFailure(KEY_DOES_NOT_EXIST)
            .withFeatureExtraction { extractKey(converter(it), key) }
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
}
