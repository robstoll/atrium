package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.AnyAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import kotlin.reflect.KClass


class AnyAssertionsImpl : AnyAssertions, AnyAssertionsDeprecatedImpl() {

    override fun <T : Any> toBe(subjectProvider: SubjectProvider<T>, expected: T) = _toBe(subjectProvider, expected)
    override fun <T> notToBe(subjectProvider: SubjectProvider<T>, expected: T) = _notToBe(subjectProvider, expected)
    override fun <T> isSame(subjectProvider: SubjectProvider<T>, expected: T) = _isSame(subjectProvider, expected)
    override fun <T> isNotSame(subjectProvider: SubjectProvider<T>, expected: T) = _isNotSame(subjectProvider, expected)

    override fun <T : Any?> toBeNull(subjectProvider: SubjectProvider<T>) = _toBeNull(subjectProvider)

    override fun <T : Any> toBeNullable(
        expect: Expect<T?>,
        type: KClass<T>,
        expectedOrNull: T?
    ) = _toBeNullable(expect, type, expectedOrNull)

    override fun <T : Any> toBeNullIfNullGivenElse(
        expect: Expect<T?>,
        type: KClass<T>,
        assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ) = _toBeNullIfNullGivenElse(expect, type, assertionCreatorOrNull)

    override fun <T, TSub : Any> isA(expect: Expect<T>, subType: KClass<TSub>) =
        _isA(expect, subType)
}
