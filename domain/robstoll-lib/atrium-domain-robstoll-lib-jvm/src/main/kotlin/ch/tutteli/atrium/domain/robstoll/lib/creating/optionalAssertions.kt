@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)
package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionOptionalAssertion.EMPTY
import java.util.*

fun <T : Optional<*>> _isEmpty(assertionContainer: Expect<T>): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, IS, RawString.create(EMPTY)) { !it.isPresent }
