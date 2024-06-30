package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.UsageHintGroup

class UsageHintGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : BaseSubGroupBuilder<SubjectT, UsageHintGroup, UsageHintGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.usageHintGroup(children) }
)
