package ch.tutteli.atrium.reporting.impl

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.ReportableGroup

internal class DefaultProofExplanation(
    proof: Proof
) : ReportableGroup {
    //TODO 1.3.0 unpack invisible groups
    override val children: List<Reportable> = listOf(proof)
}

//TODO 1.3.0 several implementations vs. a group type.
internal class DefaultUsageHintGroup(
    override val children: List<Reportable>
) : ReportableGroup
