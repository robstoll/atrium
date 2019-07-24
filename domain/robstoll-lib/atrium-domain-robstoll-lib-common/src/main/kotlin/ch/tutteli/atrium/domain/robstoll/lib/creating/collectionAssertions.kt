package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY

fun _isEmpty(subjectProvider: SubjectProvider<Collection<*>>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, DescriptionBasic.IS, RawString.create(EMPTY)) { it.isEmpty() }

fun _isNotEmpty(subjectProvider: SubjectProvider<Collection<*>>): Assertion =
    ExpectImpl.builder.createDescriptive(subjectProvider, DescriptionBasic.IS_NOT, RawString.create(EMPTY)) {
        it.isNotEmpty()
    }
