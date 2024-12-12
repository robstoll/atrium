package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseBuilder
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.reportables.*

class RowBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val icon: Icon?,
    private val includingBorder: Boolean
) : BaseBuilder<SubjectT, Row, Column, RowBuilder<SubjectT>>(proofContainer) {

    fun column(inlineElement: InlineElement, alignment: HorizontalAlignment = HorizontalAlignment.DEFAULT) =
        add(Reportable.column(inlineElement, alignment))

    override fun build(children: List<Column>): Row =
        Reportable.row(icon, includingBorder, children)
}
