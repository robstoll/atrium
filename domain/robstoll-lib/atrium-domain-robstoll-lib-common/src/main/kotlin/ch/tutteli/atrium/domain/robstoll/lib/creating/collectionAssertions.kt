package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating._domain
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY

fun _isEmpty(subjectProvider: SubjectProvider<Collection<*>>): Assertion =
    assertionBuilder.createDescriptive(subjectProvider, IS, RawString.create(EMPTY)) { it.isEmpty() }

fun _isNotEmpty(subjectProvider: SubjectProvider<Collection<*>>): Assertion =
    assertionBuilder.createDescriptive(subjectProvider, IS_NOT, RawString.create(EMPTY)) { it.isNotEmpty() }

fun <T : Collection<*>> _size(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    assertionContainer._domain.manualFeature(DescriptionCollectionAssertion.SIZE) { size }
