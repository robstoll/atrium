package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InformationGroup
import ch.tutteli.atrium.reporting.reportables.InlineElement

/** @since 1.3.0 */
class InformationGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
) : BaseSubGroupBuilder<SubjectT, InformationGroup, InformationGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Diagnostic.informationGroup(description, children) }
)
