package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO
import ch.tutteli.atrium.translations.DescriptionBasic.TO
import ch.tutteli.atrium.translations.DescriptionPathAssertion.EXIST
import ch.tutteli.atrium.translations.DescriptionPathAssertion.PARENT
import ch.tutteli.niok.exists
import ch.tutteli.niok.notExists
import java.nio.file.Path

fun <T : Path> _exists(assertionContainer: Expect<T>): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, TO, RawString.create(EXIST)) { it.exists }

fun <T : Path> _existsNot(assertionContainer: Expect<T>): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, NOT_TO, RawString.create(EXIST)) { it.notExists }

fun <T : Path> _parent(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Path> =
    ExpectImpl.feature.manualFeature(assertionContainer, PARENT) { parent }
