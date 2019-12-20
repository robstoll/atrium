package ch.tutteli.atrium.domain.creating.charsequence.contains.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomain
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContainsCheckerNoOpSearchBehaviourDomain
import ch.tutteli.atrium.domain.creating.charsequence.contains.creators.charSequenceContainsAssertions
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

internal class CharSequenceContainsCheckerNoOpSearchBehaviourDomainImpl<T : CharSequence>(
    override val checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>
) : CharSequenceContainsCheckerNoOpSearchBehaviourDomain<T> {

    override fun values(expected: List<Any>): AssertionGroup =
        charSequenceContainsAssertions.values(checkerOption, expected)

    override fun regex(expected: List<Regex>): AssertionGroup =
        charSequenceContainsAssertions.regex(checkerOption, expected)
}

internal class CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomainImpl<T : CharSequence>(
    override val checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>
) : CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomain<T> {

    override fun values(expected: List<Any>): AssertionGroup =
        charSequenceContainsAssertions.valuesIgnoringCase(checkerOption, expected)

    override fun regex(expected: List<String>): AssertionGroup =
        charSequenceContainsAssertions.regexIgnoringCase(checkerOption, expected)
}
