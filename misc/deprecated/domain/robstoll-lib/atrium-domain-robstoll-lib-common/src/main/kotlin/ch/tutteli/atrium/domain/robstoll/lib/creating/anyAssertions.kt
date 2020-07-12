@file:JvmMultifileClass
@file:JvmName("AnyAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.reflect.KClass

fun <T> _toBe(subjectProvider: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(subjectProvider, TO_BE, expected) { it == expected }

fun <T> _notToBe(subjectProvider: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(subjectProvider, NOT_TO_BE, expected) { it != expected }

fun <T> _isSame(subjectProvider: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_SAME, expected) { it === expected }

fun <T> _isNotSame(subjectProvider: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_NOT_SAME, expected) { it !== expected }


fun <T : Any?> _toBeNull(subjectProvider: SubjectProvider<T>) =
    ExpectImpl.builder.createDescriptive(subjectProvider, TO_BE, Text.NULL) { it == null }

fun <T : Any> _toBeNullIfNullGivenElse(
    expect: Expect<T?>,
    type: KClass<T>,
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): Assertion =
    if (assertionCreatorOrNull == null) ExpectImpl.any.toBeNull(expect)
    else notToBeNull(expect, type, assertionCreatorOrNull)

private fun <T : Any> notToBeNull(
    expect: Expect<T?>,
    type: KClass<T>,
    assertionCreator: Expect<T>.() -> Unit
) = ExpectImpl.any.notToBeNull(expect, type).collect(assertionCreator)


fun <T, TSub : Any> _isA(
    expect: Expect<T>,
    subType: KClass<TSub>
): ChangedSubjectPostStep<T, TSub> =
    ExpectImpl.changeSubject(expect).reportBuilder()
        .downCastTo(subType)
        .build()
