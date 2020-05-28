package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY

fun _isEmpty(subjectProvider: SubjectProvider<Collection<*>>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS, EMPTY) { it.isEmpty() }

fun _isNotEmpty(subjectProvider: SubjectProvider<Collection<*>>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_NOT, EMPTY) { it.isNotEmpty() }

fun <T : Collection<*>> _size(expect: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(expect, DescriptionCollectionAssertion.SIZE) { size }
