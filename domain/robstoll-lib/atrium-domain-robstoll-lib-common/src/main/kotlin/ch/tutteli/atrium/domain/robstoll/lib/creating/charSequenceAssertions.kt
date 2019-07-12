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
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.BLANK
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.EMPTY
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.ENDS_NOT_WITH
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.ENDS_WITH
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.STARTS_NOT_WITH
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.STARTS_WITH

fun <T : CharSequence> _containsBuilder(plant: AssertionPlant<T>): CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    = CharSequenceContainsBuilder(plant, NoOpSearchBehaviourImpl())

fun <T : CharSequence> _containsNotBuilder(plant: AssertionPlant<T>): CharSequenceContains.Builder<T, NotSearchBehaviour>
    = CharSequenceContainsBuilder(plant, NotSearchBehaviourImpl())


fun _startsWith(plant: AssertionPlant<CharSequence>, expected: CharSequence): Assertion
    = AssertImpl.builder.createDescriptive(plant, STARTS_WITH, expected) { it.startsWith(expected) }

fun _startsNotWith(plant: AssertionPlant<CharSequence>, expected: CharSequence): Assertion
    = AssertImpl.builder.createDescriptive(plant, STARTS_NOT_WITH, expected) { !it.startsWith(expected) }

fun _endsWith(plant: AssertionPlant<CharSequence>, expected: CharSequence): Assertion
    = AssertImpl.builder.createDescriptive(plant, ENDS_WITH, expected) { it.endsWith(expected) }

fun _endsNotWith(plant: AssertionPlant<CharSequence>, expected: CharSequence): Assertion
    = AssertImpl.builder.createDescriptive(plant, ENDS_NOT_WITH, expected) { !it.endsWith(expected) }

fun _isEmpty(plant: AssertionPlant<CharSequence>): Assertion
    = AssertImpl.builder.createDescriptive(plant, DescriptionBasic.IS, RawString.create(EMPTY)) { it.isEmpty() }

fun _isNotEmpty(plant: AssertionPlant<CharSequence>): Assertion
    = AssertImpl.builder.createDescriptive(plant, DescriptionBasic.IS_NOT, RawString.create(EMPTY)) { it.isNotEmpty() }

fun _isNotBlank(plant: AssertionPlant<CharSequence>): Assertion
    = AssertImpl.builder.createDescriptive(plant, DescriptionBasic.IS_NOT, RawString.create(BLANK)) { it.isNotBlank() }
