package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.DocumentationUtils
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDocumentationUtil

class DefaultDocumentationUtils : DocumentationUtils {
    override fun <T> because(
        container: ProofContainer<T>,
        reason: InlineElement,
        expectationCreator: Expect<T>.() -> Unit
    ): Proof = container.buildProof {
        collect(
            ExpectationCreatorWithUsageHints(
                // there is no alternative for `because` which does not take an expectationCreator
                usageHintsOverloadWithoutExpectationCreator = emptyList(),
                expectationCreator
            )
        )
        row(icon = Icon.INFORMATION_SOURCE, includingBorder = false) {
            column(DescriptionDocumentationUtil.BECAUSE)
            column(Text.SPACE)
            column(reason)
        }
    }
}
