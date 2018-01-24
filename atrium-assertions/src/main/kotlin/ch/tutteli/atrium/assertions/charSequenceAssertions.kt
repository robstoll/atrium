package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.RawString

fun <T : CharSequence> _containsBuilder(plant: AssertionPlant<T>)
    = CharSequenceContainsBuilder(plant, CharSequenceContainsNoOpSearchBehaviour())

fun <T : CharSequence> _containsNotBuilder(plant: AssertionPlant<T>)
    = CharSequenceContainsBuilder(plant, CharSequenceContainsNotSearchBehaviour())


fun <T : CharSequence> _startsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = AssertionBuilder.descriptive.create(STARTS_WITH, expected, { plant.subject.startsWith(expected) })

fun <T : CharSequence> _startsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = AssertionBuilder.descriptive.create(STARTS_NOT_WITH, expected, { !plant.subject.startsWith(expected) })

fun <T : CharSequence> _endsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = AssertionBuilder.descriptive.create(ENDS_WITH, expected, { plant.subject.endsWith(expected) })

fun <T : CharSequence> _endsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = AssertionBuilder.descriptive.create(ENDS_NOT_WITH, expected, { !plant.subject.endsWith(expected) })

fun <T : CharSequence> _isEmpty(plant: AssertionPlant<T>): Assertion
    = AssertionBuilder.descriptive.create(DescriptionBasic.IS, RawString.create(EMPTY), { plant.subject.isEmpty() })

fun <T : CharSequence> _isNotEmpty(plant: AssertionPlant<T>): Assertion
    = AssertionBuilder.descriptive.create(DescriptionBasic.IS_NOT, RawString.create(EMPTY), { plant.subject.isNotEmpty() })
