package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours.NotSearchBehaviourImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.*

fun <T : CharSequence> _containsBuilder(plant: AssertionPlant<T>): CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    = CharSequenceContainsBuilder(plant, NoOpSearchBehaviourImpl())

fun <T : CharSequence> _containsNotBuilder(plant: AssertionPlant<T>): CharSequenceContains.Builder<T, NotSearchBehaviour>
    = CharSequenceContainsBuilder(plant, NotSearchBehaviourImpl())


fun <T : CharSequence> _startsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = AssertImpl.builder.descriptive
        .withTest { plant.subject.startsWith(expected) }
        .create(STARTS_WITH, expected)

fun <T : CharSequence> _startsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = AssertImpl.builder.descriptive
        .withTest { !plant.subject.startsWith(expected) }
        .create(STARTS_NOT_WITH, expected)

fun <T : CharSequence> _endsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = AssertImpl.builder.descriptive
        .withTest { plant.subject.endsWith(expected) }
        .create(ENDS_WITH, expected)

fun <T : CharSequence> _endsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = AssertImpl.builder.descriptive
        .withTest { !plant.subject.endsWith(expected) }
        .create(ENDS_NOT_WITH, expected)

fun <T : CharSequence> _isEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.builder.descriptive
        .withTest { plant.subject.isEmpty() }
        .create(DescriptionBasic.IS, RawString.create(EMPTY))

fun <T : CharSequence> _isNotEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.builder.descriptive
        .withTest { plant.subject.isNotEmpty() }
        .create(DescriptionBasic.IS_NOT, RawString.create(EMPTY))
