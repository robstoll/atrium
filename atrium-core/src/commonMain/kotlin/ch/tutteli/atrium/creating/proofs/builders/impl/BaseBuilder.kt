package ch.tutteli.atrium.creating.proofs.builders.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.builders.ProofBuilderMarker
import ch.tutteli.atrium.reporting.reportables.Reportable

@ProofBuilderMarker
abstract class BaseBuilder<
    SubjectT,
    ReportableT : Reportable,
    ReportableElementT : Reportable,
    SelfT : BaseBuilder<SubjectT, ReportableT, ReportableElementT, SelfT>
    >(
    protected val proofContainer: ProofContainer<SubjectT>
) {
    @Suppress("UNCHECKED_CAST")
    private inline val self: SelfT get() = this as SelfT

    private val reportables = mutableListOf<ReportableElementT>()

    fun <R : ReportableElementT> add(r: R): R = r.also { reportables.add(it) }
    fun addAll(reportables: List<ReportableElementT>): Unit = reportables.forEach(this::add)
    fun addAll(vararg reportables: ReportableElementT): Unit = reportables.forEach(this::add)

    fun build(init: SelfT.() -> Unit): ReportableT {
        //TODO 1.3.0 transform an unexpected exception into a failing proof
        self.also(init)
        return build(reportables)
    }

    protected abstract fun build(children: List<ReportableElementT>): ReportableT
}
