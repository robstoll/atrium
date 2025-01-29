package ch.tutteli.atrium.creating.proofs.builders.impl

import ch.tutteli.atrium.creating.proofs.builders.*
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.InlineElement

interface DiagnosticBuilder<SubjectT> {
    fun diagnosticGroup(
        description: InlineElement,
        representation: Any?,
        init: DiagnosticGroupBuilder<SubjectT>.() -> Unit
    ): Diagnostic

    fun proofExplanation(init: ProofExplanationGroupBuilder<SubjectT>.() -> Unit): Diagnostic
    fun failureExplanationGroup(
        description: InlineElement,
        init: FailureExplanationGroupBuilder<SubjectT>.() -> Unit
    ): Diagnostic

    fun informationGroup(
        description: InlineElement,
        init: InformationGroupBuilder<SubjectT>.() -> Unit
    ): Diagnostic

    fun debugGroup(description: InlineElement, init: DebugGroupBuilder<SubjectT>.() -> Unit): Diagnostic
    fun usageHintGroup(init: UsageHintGroupBuilder<SubjectT>.() -> Unit): Diagnostic
    fun inlineGroup(vararg inlineElements: InlineElement): InlineElement
    fun row(icon: Icon? = null, includingBorder: Boolean = true, init: RowBuilder<SubjectT>.() -> Unit): Diagnostic
    fun text(string: String): Diagnostic
}

class DiagnosticBuilderDelegate<SubjectT>(

) : DiagnosticBuilder<SubjectT> {
    lateinit var reportableBuilder: ReportableBuilder<SubjectT, Diagnostic>

    private val proofContainer get() = reportableBuilder.proofContainer

    private fun <R: Diagnostic> add(r: R): R = reportableBuilder.add(r)

    override fun diagnosticGroup(
        description: InlineElement,
        representation: Any?,
        init: DiagnosticGroupBuilder<SubjectT>.() -> Unit
    ): Diagnostic = add(DiagnosticGroupBuilder(proofContainer, description, representation).build(init))

    override fun proofExplanation(init: ProofExplanationGroupBuilder<SubjectT>.() -> Unit): Diagnostic =
        add(ProofExplanationGroupBuilder(proofContainer).build(init))

    override fun failureExplanationGroup(
        description: InlineElement,
        init: FailureExplanationGroupBuilder<SubjectT>.() -> Unit
    ): Diagnostic = add(FailureExplanationGroupBuilder(proofContainer, description).build(init))

    override fun informationGroup(
        description: InlineElement,
        init: InformationGroupBuilder<SubjectT>.() -> Unit
    ): Diagnostic = add(InformationGroupBuilder(proofContainer, description).build(init))

    override fun debugGroup(description: InlineElement, init: DebugGroupBuilder<SubjectT>.() -> Unit): Diagnostic =
        add(DebugGroupBuilder(proofContainer, description).build(init))

    override fun usageHintGroup(init: UsageHintGroupBuilder<SubjectT>.() -> Unit): Diagnostic =
        add(UsageHintGroupBuilder(proofContainer).build(init))


    override fun inlineGroup(vararg inlineElements: InlineElement): InlineElement =
        Diagnostic.inlineGroup(inlineElements.toList())

    override fun row(icon: Icon?, includingBorder: Boolean, init: RowBuilder<SubjectT>.() -> Unit): Diagnostic =
        add(RowBuilder(proofContainer, icon, includingBorder).build(init))

    override fun text(string: String): Diagnostic = add(Text(string))
}
