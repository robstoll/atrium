package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO
import ch.tutteli.atrium.translations.DescriptionBasic.TO
import ch.tutteli.atrium.translations.DescriptionPathAssertion
import ch.tutteli.atrium.translations.DescriptionPathAssertion.DOES_NOT_HAVE_PARENT
import ch.tutteli.atrium.translations.DescriptionPathAssertion.EXIST
import ch.tutteli.atrium.translations.DescriptionPathAssertion.ENDS_WITH
import ch.tutteli.niok.exists
import ch.tutteli.niok.notExists
import java.nio.file.Path

fun <T : Path> _exists(assertionContainer: Expect<T>): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, TO, RawString.create(EXIST)) { it.exists }

fun <T : Path> _existsNot(assertionContainer: Expect<T>): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, NOT_TO, RawString.create(EXIST)) { it.notExists }

fun <T : Path> _endsWith(assertionContainer: Expect<T>, expected: T): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, ENDS_WITH, expected) { it.endsWith(expected) }

fun <T : Path> _parent(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Path> =
    ExpectImpl.feature.extractor(assertionContainer)
        .withDescription(DescriptionPathAssertion.PARENT)
        .withRepresentationForFailure(DOES_NOT_HAVE_PARENT)
        .withCheck { it.parent != null }
        .withFeatureExtraction { it.parent }
        .build()
