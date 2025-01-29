package ch.tutteli.atrium.creating.proofs.builders.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.builders.ProofBuilderMarker
import ch.tutteli.atrium.reporting.reportables.Reportable

interface ReportableBuilder<
    SubjectT,
    in ReportableElementT : Reportable,
    > {

    val proofContainer: ProofContainer<SubjectT>

    fun <R : ReportableElementT> add(r: R): R
    fun addAll(reportables: List<ReportableElementT>)
    fun addAll(vararg reportables: ReportableElementT)
}

@ProofBuilderMarker
abstract class BaseBuilder<
    SubjectT,
    ReportableT : Reportable,
    in ReportableElementT : Reportable,
    SelfT : BaseBuilder<SubjectT, ReportableT, ReportableElementT, SelfT>
    >(
    override val proofContainer: ProofContainer<SubjectT>
) : ReportableBuilder<SubjectT, ReportableElementT> {

    @Suppress("UNCHECKED_CAST")
    private inline val self: SelfT get() = this as SelfT

    private val reportables = mutableListOf<ReportableElementT>()

    final override fun <R : ReportableElementT> add(r: R): R = r.also { reportables.add(it) }
    final override fun addAll(reportables: List<ReportableElementT>): Unit = reportables.forEach(this::add)
    final override fun addAll(vararg reportables: ReportableElementT): Unit = reportables.forEach(this::add)

    fun build(init: SelfT.() -> Unit): ReportableT {
        //TODO 1.3.0 transform an unexpected exception into a failing proof
        self.also(init)
        return build(reportables)
    }

    protected abstract fun build(children: List<ReportableElementT>): ReportableT
}
