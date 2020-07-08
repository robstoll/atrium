//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.AnyAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import kotlin.reflect.KClass

@Deprecated("Will be removed with 1.0.0")
class AnyAssertionsImpl : AnyAssertions, AnyAssertionsDeprecatedImpl() {

    override fun <T> toBe(subjectProvider: SubjectProvider<T>, expected: T) = _toBe(subjectProvider, expected)
    override fun <T> notToBe(subjectProvider: SubjectProvider<T>, expected: T) = _notToBe(subjectProvider, expected)
    override fun <T> isSame(subjectProvider: SubjectProvider<T>, expected: T) = _isSame(subjectProvider, expected)
    override fun <T> isNotSame(subjectProvider: SubjectProvider<T>, expected: T) = _isNotSame(subjectProvider, expected)

    override fun <T : Any?> toBeNull(subjectProvider: SubjectProvider<T>) = _toBeNull(subjectProvider)

    override fun <T : Any> toBeNullIfNullGivenElse(
        expect: Expect<T?>,
        type: KClass<T>,
        assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ) = _toBeNullIfNullGivenElse(expect, type, assertionCreatorOrNull)

    override fun <T, TSub : Any> isA(expect: Expect<T>, subType: KClass<TSub>) =
        _isA(expect, subType)
}
