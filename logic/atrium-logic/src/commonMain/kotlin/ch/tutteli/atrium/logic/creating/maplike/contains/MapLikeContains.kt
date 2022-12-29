package ch.tutteli.atrium.logic.creating.maplike.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.logic.creating.basic.contains.Contains
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains.*
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.MapLikeContainsAssertions
import ch.tutteli.atrium.logic.creating.maplike.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract for sophisticated [MapLike] contains assertion builders.
 *
 * The building process is typically started by the creation of an [EntryPointStep],
 * goes on by specifying a desired [SearchBehaviour] and
 * is finalized by one of the [MapLikeContainsAssertions] which usually use a [Creator].
 */
interface MapLikeContains {

    /**
     * The entry point of the contract.
     *
     * Use `_logic` to retrieve the [EntryPointStepLogic] counterpart in case you want to extend the building process.
     */
    interface EntryPointStep<K, V, T : MapLike, out S : SearchBehaviour> : Contains.EntryPointStep<T, S>

    /**
     * The entry point of the contract on the logic level, containing the [container] -- i.e. the subject of this expectation
     * for which the sophisticated `contain` assertion should be created -- as well as the chosen [searchBehaviour].
     *
     * The [searchBehaviour] might me modified in which case it is recommended that a new [EntryPointStep] is created (retain
     * immutability).
     */
    interface EntryPointStepLogic<K, V, T : MapLike, out S : SearchBehaviour> : Contains.EntryPointStepLogic<T, S> {
        /**
         * The converter which shall be used to turn the subject of this expectation into an [Iterable] of type [E].
         */
        val converter: (T) -> Map<out K, V>

        fun <S2: SearchBehaviour> withSearchBehaviour(searchBehaviour: S2) : EntryPointStep<K, V, T, S2> =
            EntryPointStepImpl(container, converter, searchBehaviour)
    }

    /**
     * Sole purpose of this interface is to hide [EntryPointStepLogic] from newcomers which
     * usually do not have to deal with this type and to keep the API clean.
     *
     * Moreover, this keeps the API clean and does not pollute it with things like `times`, `containsBuilder` etc.
     *
     * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
     */
    interface EntryPointStepInternal<K, V, T : MapLike, out S : SearchBehaviour> :
        EntryPointStep<K, V, T, S>,
        EntryPointStepLogic<K, V, T, S>

    /**
     * Represents a search behaviour but leaves it up to the [Creator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [Translatable]) in order that it reflects the search behaviour.
     */
    interface SearchBehaviour : Contains.SearchBehaviour

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [AssertionGroup]
     * as such.
     *
     * @param T The type of the subject of this expectation.
     * @param SC The type of the search criteria.
     */
    interface Creator<T : MapLike, in SC> : Contains.Creator<T, SC>
}
