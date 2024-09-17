package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.transformAndGetSingleColumnOfSingleNode
import ch.tutteli.atrium.reporting.reportables.Row
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

internal class DefaultRowTextPreRenderer(
    private val textIconStyler: TextIconStyler
) : TypedTextPreRenderer<Row>(Row::class) {
    override fun transformIt(reportable: Row, controlObject: TextPreRenderControlObject): List<OutputNode> {
        val columnsWithSeparator = reportable.columns.let { columns ->
            val first = controlObject.transformAndGetSingleColumnOfSingleNode(columns.first())
            when (columns.size) {
                1 -> listOf(first)
                else -> {
                    columns.asSequence().drop(1)
                        .fold(ArrayList<StyledString>(columns.size * 2 - 1).also { it.add(first) }) { acc, column ->
                            acc.also {
                                it.add(StyledString.COLON_SEPARATOR)
                                it.add(controlObject.transformAndGetSingleColumnOfSingleNode(column))
                            }
                        }
                }
            }
        }
        val prefix = reportable.icon?.let { listOf(textIconStyler.style(it)) } ?: emptyList()
        return listOf(
            OutputNode(
                columns = prefix + columnsWithSeparator,
                children = emptyList(),
                definesOwnLevel = true,
                usesOwnPrefix = reportable.icon != null
            )
        )
    }
}
