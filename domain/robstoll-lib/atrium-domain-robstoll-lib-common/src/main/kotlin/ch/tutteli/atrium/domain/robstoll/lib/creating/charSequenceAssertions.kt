package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours.NotSearchBehaviourImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.*

fun <T : CharSequence> _containsBuilder(subjectProvider: SubjectProvider<T>): CharSequenceContains.Builder<T, NoOpSearchBehaviour> =
    CharSequenceContainsBuilder(subjectProvider, NoOpSearchBehaviourImpl())

fun <T : CharSequence> _containsNotBuilder(subjectProvider: SubjectProvider<T>): CharSequenceContains.Builder<T, NotSearchBehaviour> =
    CharSequenceContainsBuilder(subjectProvider, NotSearchBehaviourImpl())


fun _startsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion =
    AssertImpl.builder.createDescriptive(subjectProvider, STARTS_WITH, expected) { it.startsWith(expected) }

fun _startsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion =
    AssertImpl.builder.createDescriptive(subjectProvider, STARTS_NOT_WITH, expected) { !it.startsWith(expected) }

fun _endsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion =
    AssertImpl.builder.createDescriptive(subjectProvider, ENDS_WITH, expected) { it.endsWith(expected) }

fun _endsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion =
    AssertImpl.builder.createDescriptive(subjectProvider, ENDS_NOT_WITH, expected) { !it.endsWith(expected) }

fun _isEmpty(subjectProvider: SubjectProvider<CharSequence>): Assertion =
    AssertImpl.builder.createDescriptive(subjectProvider, IS, RawString.create(EMPTY)) { it.isEmpty() }

fun _isNotEmpty(subjectProvider: SubjectProvider<CharSequence>): Assertion =
    AssertImpl.builder.createDescriptive(subjectProvider, IS_NOT, RawString.create(EMPTY)) { it.isNotEmpty() }

fun _isNotBlank(subjectProvider: SubjectProvider<CharSequence>): Assertion =
    AssertImpl.builder.createDescriptive(subjectProvider, IS_NOT, RawString.create(BLANK)) { it.isNotBlank() }

fun _mismatches(subjectProvider: Expect<CharSequence>, expected: Regex): Assertion =
    AssertImpl.builder.createDescriptive(subjectProvider, MISMATCHES, expected) {!it.matches(expected)}
