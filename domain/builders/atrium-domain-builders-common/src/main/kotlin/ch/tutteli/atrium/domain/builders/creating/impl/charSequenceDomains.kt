package ch.tutteli.atrium.domain.builders.creating.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.creators.charSequenceContainsAssertions
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.searchBehaviourFactory

internal class CharSequenceDomainImpl<T : CharSequence>(
    charSequenceOnlyDomain: CharSequenceOnlyDomain<T>,
    anyDomain: AnyDomain<T>
) : CharSequenceDomain<T>, CharSequenceOnlyDomain<T> by charSequenceOnlyDomain, AnyDomain<T> by anyDomain {
    override val expect: Expect<T> = charSequenceOnlyDomain.expect
}

internal class CharSequenceOnlyDomainImpl<T : CharSequence>(
    override val expect: Expect<T>
) : CharSequenceOnlyDomain<T> {
    override val containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour> =
        charSequenceAssertions.containsBuilder(expect)
    override val containsNotBuilder: CharSequenceContains.Builder<T, NotSearchBehaviour> =
        charSequenceAssertions.containsNotBuilder(expect)

    override fun startsWith(expected: CharSequence) = charSequenceAssertions.startsWith(expect, expected)
    override fun startsNotWith(expected: CharSequence) = charSequenceAssertions.startsNotWith(expect, expected)
    override fun endsWith(expected: CharSequence) = charSequenceAssertions.endsWith(expect, expected)
    override fun endsNotWith(expected: CharSequence) = charSequenceAssertions.endsNotWith(expect, expected)
    override fun isEmpty() = charSequenceAssertions.isEmpty(expect)
    override fun isNotEmpty() = charSequenceAssertions.isNotEmpty(expect)
    override fun isNotBlank() = charSequenceAssertions.isNotBlank(expect)
    override fun matches(expected: Regex) = charSequenceAssertions.matches(expect, expected)
    override fun mismatches(expected: Regex) = charSequenceAssertions.mismatches(expect, expected)
}


internal class CharSequenceContainsBuilderNoOpSearchBehaviourDomainImpl<T : CharSequence>(
    override val builder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
) : CharSequenceContainsBuilderNoOpSearchBehaviourDomain<T> {
    override val ignoringCase: CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
        get() = searchBehaviourFactory.ignoringCase(builder)
}

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
