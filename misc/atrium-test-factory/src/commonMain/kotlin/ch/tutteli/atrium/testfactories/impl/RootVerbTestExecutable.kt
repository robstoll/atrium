package ch.tutteli.atrium.testfactories.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.TestExecutable

internal class RootVerbTestExecutable(private val expectationVerbs: ExpectationVerbs) : TestExecutable {
    override fun <T> expect(subject: T): Expect<T> =
        expectationVerbs.expect(subject)

    override fun <T> expect(
        subject: T,
        expectationCreator: Expect<T>.() -> Unit
    ): Expect<T> = expectationVerbs.expect(subject, expectationCreator)
}
