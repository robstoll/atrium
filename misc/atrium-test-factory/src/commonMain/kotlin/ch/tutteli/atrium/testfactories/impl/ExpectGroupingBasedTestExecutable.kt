package ch.tutteli.atrium.testfactories.impl

import ch.tutteli.atrium._core
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.creating.proofs.builders.buildSimpleProof
import ch.tutteli.atrium.creating.transformers.propertiesOfThrowable
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionFunLikeProof
import ch.tutteli.atrium.testfactories.TestExecutable

internal class ExpectGroupingBasedTestExecutable(
    private val expectGrouping: ExpectGrouping,
    private val expectationVerbs: ExpectationVerbs
) : TestExecutable {
    override fun <T> expect(subject: T): Expect<T> =
        expectationVerbs.expectInExpectGrouped(expectGrouping, subject)

    override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
        expectationVerbs.expectInExpectGrouped(expectGrouping, subject, expectationCreator)
}

internal fun ExpectGrouping.executeAndAppendExceptionAsProof(executable: () -> Unit) =
    try {
        executable()
        // does not collect the assertions created by the inner group but that's a different story
        // a feature which is not used currently
        _core.buildSimpleProof(Text("executing test group"), Text("succeeds")) { true }
    } catch (throwable: Throwable) {
        val causingProof = (throwable as? AtriumError)?.causingProof
        if (causingProof != null) {
            // TODO 1.4.0 we could add a usage hint that one should define own expectation verbs
            _core.append(causingProof)
        } else {
            _core.buildProof {
                fixedClaimGroup(
                    Text("executing test group"),
                    Diagnostic.inlineGroup(listOf(DescriptionFunLikeProof.THREW, Text(throwable::class.fullName))),
                    holds = false
                ) {
                    _core.propertiesOfThrowable(throwable)
                }
            }
        }
    }


