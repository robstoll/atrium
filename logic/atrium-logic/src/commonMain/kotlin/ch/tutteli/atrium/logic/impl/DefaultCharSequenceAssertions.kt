package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.CharSequenceAssertions
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl.NotSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.notCheckerStep
import ch.tutteli.atrium.logic.property
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_BE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation.*

class DefaultCharSequenceAssertions : CharSequenceAssertions {
    override fun <T : CharSequence> containsBuilder(
        container: AssertionContainer<T>
    ): CharSequenceContains.EntryPointStep<T, NoOpSearchBehaviour> =
        EntryPointStepImpl(container, NoOpSearchBehaviourImpl())

    override fun <T : CharSequence> containsNotBuilder(
        container: AssertionContainer<T>
    ): NotCheckerStep<T, NotSearchBehaviour> =
        EntryPointStepImpl(container, NotSearchBehaviourImpl())._logic.notCheckerStep()

    override fun <T : CharSequence> startsWith(container: AssertionContainer<T>, expected: CharSequence): Assertion =
        container.createDescriptiveAssertion(TO_START_WITH, expected) { it.startsWith(expected) }

    override fun <T : CharSequence> startsNotWith(container: AssertionContainer<T>, expected: CharSequence): Assertion =
        container.createDescriptiveAssertion(NOT_TO_START_WITH, expected) { !it.startsWith(expected) }

    override fun <T : CharSequence> endsWith(container: AssertionContainer<T>, expected: CharSequence): Assertion =
        container.createDescriptiveAssertion(TO_END_WITH, expected) { it.endsWith(expected) }

    override fun <T : CharSequence> endsNotWith(container: AssertionContainer<T>, expected: CharSequence): Assertion =
        container.createDescriptiveAssertion(NOT_TO_END_WITH, expected) { !it.endsWith(expected) }

    override fun <T : CharSequence> isEmpty(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(TO_BE, EMPTY) { it.isEmpty() }

    override fun <T : CharSequence> isNotEmpty(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(NOT_TO_BE, EMPTY) { it.isNotEmpty() }

    override fun <T : CharSequence> isNotBlank(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(NOT_TO_BE, BLANK) { it.isNotBlank() }

    override fun <T : CharSequence> matches(container: AssertionContainer<T>, expected: Regex): Assertion =
        container.createDescriptiveAssertion(TO_MATCH, expected) { it.matches(expected) }

    override fun <T : CharSequence> mismatches(container: AssertionContainer<T>, expected: Regex): Assertion =
        container.createDescriptiveAssertion(NOT_TO_MATCH, expected) { !it.matches(expected) }

    override fun <T : CharSequence> length(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, Int> =
        container.property(CharSequence::length)
}
