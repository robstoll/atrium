package ch.tutteli.atrium.creating.proofs.builders.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.reporting.reportables.Reportable

abstract class BaseSubGroupBuilder<SubjectT,
    ReportableT : Reportable,
    SelfT : BaseGroupBuilder<SubjectT, ReportableT, SelfT>
    >(
    proofContainer: ProofContainer<SubjectT>,
    private val factory: (children: List<Reportable>) -> ReportableT,
) : BaseGroupBuilder<SubjectT, ReportableT, SelfT>(proofContainer) {
    override fun build(children: List<Reportable>): ReportableT = factory(children)
}
