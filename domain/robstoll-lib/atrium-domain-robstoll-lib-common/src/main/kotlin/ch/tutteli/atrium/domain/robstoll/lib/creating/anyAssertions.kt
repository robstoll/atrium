@file:JvmMultifileClass
@file:JvmName("AnyAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.reporting.RawString
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
    ExpectImpl.builder.createDescriptive(subjectProvider, TO_BE, RawString.NULL) { it == null }

fun <T : Any> _toBeNullable(
    assertionContainer: Expect<T?>,
    type: KClass<T>,
    expectedOrNull: T?
): Assertion = when (expectedOrNull) {
    null -> ExpectImpl.any.toBeNull(assertionContainer)
    else -> notToBeNull(assertionContainer, type) { toBe(expectedOrNull) }
}

fun <T : Any> _toBeNullIfNullGivenElse(
    assertionContainer: Expect<T?>,
    type: KClass<T>,
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): Assertion =
    if (assertionCreatorOrNull == null) ExpectImpl.any.toBeNull(assertionContainer)
    else notToBeNull(assertionContainer, type, assertionCreatorOrNull)

private fun <T : Any> notToBeNull(
    assertionContainer: Expect<T?>,
    type: KClass<T>,
    assertionCreator: Expect<T>.() -> Unit
) = ExpectImpl.any.notToBeNull(assertionContainer, type).collect(assertionCreator)

@Suppress(
    "UNCHECKED_CAST" /*
        Has to be an unchecked_cast because we cannot already know that assertionContainer is a TSub.
        However, it is fine as we can provide an Expect<TSub> even for the case the down-cast fails.
        And we specified Expect<*> and not Expect<T> in order that we only have one type parameter in the API so
        that writing isA<Int> is possible.
    */
)
fun <TSub : Any> _isA(
    assertionContainer: Expect<*>,
    subType: KClass<TSub>
): ChangedSubjectPostStep<TSub, TSub> =
    ExpectImpl.changeSubject.reportBuilder(assertionContainer as Expect<TSub>)
        .downCastTo(subType)
        .build()
