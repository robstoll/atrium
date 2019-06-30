package ch.tutteli.atrium.domain.robstoll.creating.changers

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.domain.robstoll.lib.creating.changers._changeSubject
import ch.tutteli.atrium.domain.robstoll.lib.creating.changers._changeSubjectUnreported
import ch.tutteli.atrium.reporting.translating.Translatable

class SubjectChangerImpl : SubjectChanger {

    override fun <T, R> unreported(
        originalAssertionContainer: Expect<T>,
        transformation: (T) -> R
    ): Expect<R> = _changeSubjectUnreported(originalAssertionContainer, transformation)

    override fun <T, R> reported(
        originalAssertionContainer: Expect<T>,
        description: Translatable,
        representation: Any,
        canBeTransformed: (T) -> Boolean,
        transformation: (T) -> R,
        subAssertions: (Expect<R>.() -> Unit)?
    ): Expect<R> = _changeSubject(
        originalAssertionContainer,
        description,
        representation,
        canBeTransformed,
        transformation,
        subAssertions
    )
}
