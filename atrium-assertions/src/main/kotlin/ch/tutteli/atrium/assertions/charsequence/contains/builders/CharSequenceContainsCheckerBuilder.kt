package ch.tutteli.atrium.assertions.charsequence.contains.builders


import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.creating.IAssertionPlant

abstract class CharSequenceContainsCheckerBuilder<T : CharSequence, D: IDecorator>(
    val containsBuilder: CharSequenceContainsBuilder<T, D>
) {
    abstract val checkers: List<IChecker>

    fun addAssertion(searcher: ISearcher<D>, expected: Any, otherExpected: Array<out Any>): IAssertionPlant<T> {
        val assertionGroup = CharSequenceContainsAssertionCreator<T, D>(containsBuilder.decorator, searcher, checkers)
            .create(containsBuilder.plant, expected, *otherExpected)
        return containsBuilder.plant.addAssertion(assertionGroup)
    }
}
