@file:JvmMultifileClass
@file:JvmName("AnyAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_NOT_SAME
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_BE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

fun <T> _toBe(subjectProvider: SubjectProvider<T>, expected: T) =
    assertionBuilder.createDescriptive(subjectProvider, TO_BE, expected) { it == expected }

fun <T> _notToBe(subjectProvider: SubjectProvider<T>, expected: T) =
    assertionBuilder.createDescriptive(subjectProvider, NOT_TO_BE, expected) { it != expected }

fun <T> _isSame(subjectProvider: SubjectProvider<T>, expected: T) =
    assertionBuilder.createDescriptive(subjectProvider, IS_SAME, expected) { it === expected }

fun <T> _isNotSame(subjectProvider: SubjectProvider<T>, expected: T) =
    assertionBuilder.createDescriptive(subjectProvider, IS_NOT_SAME, expected) { it !== expected }

fun <T : Any?> _toBeNull(subjectProvider: SubjectProvider<T>) =
    assertionBuilder.createDescriptive(subjectProvider, TO_BE, RawString.NULL) { it == null }
