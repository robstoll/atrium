package ch.tutteli.atrium.assertions.charsequence.contains.builders


import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.*
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * The base class for builders which create [IChecker]s within the fluent API of a sophisticated
 * `contains` assertion which was started with the given [containsBuilder].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create [IChecker]s within the fluent API of a sophisticated
 *              `contains` assertion which was started with the given [containsBuilder].
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 */
abstract class CharSequenceContainsCheckerBuilder<T : CharSequence, D : IDecorator>(
    val containsBuilder: CharSequenceContainsBuilder<T, D>
) {
    /**
     * Contains all [IChecker]s which should be applied to the search result.
     *
     * It typically contains the [IChecker] this builder created and might contain other [IChecker]s which builders,
     * precedent to this builder within the fluent API, created already.
     */
    abstract val checkers: List<IChecker>

    /**
     * A helper method for [ISearcher]-API functions to finish the building of a sophisticated
     * `contains` assertion whereas [expected] and [otherExpected] are the objects which shall be found.
     *
     * It creates an [IAssertionGroup] with the help of an [CharSequenceContainsAssertionCreator] and using the given
     * [searcher] as well as [expected] and [otherExpected]. It then adds the created [IAssertionGroup] as new assertion
     * to the [plant][CharSequenceContainsBuilder.plant] of the [containsBuilder].
     *
     * @param searcher The [ISearcher] which will be used to search [expected] and [otherExpected].
     * @param expected The first object which shall be searched.
     * @Param otherExpected Either an empty array if no other objects shall be searched or some additional objects.
     *
     * @return The [plant][CharSequenceContainsBuilder.plant] of the [containsBuilder] to allow a fluent API.
     * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
     */
    fun addAssertion(searcher: ISearcher<D>, expected: Any, otherExpected: Array<out Any>): IAssertionPlant<T> {
        val assertionGroup = CharSequenceContainsAssertionCreator<T, D>(containsBuilder.decorator, searcher, checkers)
            .create(containsBuilder.plant, expected, *otherExpected)
        return containsBuilder.plant.addAssertion(assertionGroup)
    }
}
