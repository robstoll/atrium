package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours.NotSearchBehaviourImpl
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.*

fun <T : CharSequence> _containsBuilder(subjectProvider: SubjectProvider<T>): CharSequenceContains.Builder<T, NoOpSearchBehaviour> =
    CharSequenceContainsBuilder(subjectProvider, NoOpSearchBehaviourImpl())

fun <T : CharSequence> _containsNotBuilder(subjectProvider: SubjectProvider<T>): CharSequenceContains.Builder<T, NotSearchBehaviour> =
    CharSequenceContainsBuilder(subjectProvider, NotSearchBehaviourImpl())


fun _startsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, STARTS_WITH, expected) { it.startsWith(expected) }

fun _startsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, STARTS_NOT_WITH, expected) { !it.startsWith(expected) }

fun _endsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, ENDS_WITH, expected) { it.endsWith(expected) }

fun _endsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, ENDS_NOT_WITH, expected) { !it.endsWith(expected) }

fun _isEmpty(subjectProvider: SubjectProvider<CharSequence>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS, EMPTY) { it.isEmpty() }

fun _isNotEmpty(subjectProvider: SubjectProvider<CharSequence>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_NOT, EMPTY) { it.isNotEmpty() }

fun _isNotBlank(subjectProvider: SubjectProvider<CharSequence>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_NOT, BLANK) { it.isNotBlank() }

fun <T : CharSequence> _matches(expect: Expect<T>, expected: Regex): Assertion =
    ExpectImpl.builder.createDescriptive(expect, MATCHES, expected) { it.matches(expected) }

fun <T : CharSequence> _mismatches(expect: Expect<T>, expected: Regex): Assertion =
    ExpectImpl.builder.createDescriptive(expect, MISMATCHES, expected) { !it.matches(expected) }
