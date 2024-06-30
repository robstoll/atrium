package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.text.TextObjFormatter
import ch.tutteli.atrium.reporting.theming.text.checkIsNoLineBreakDueToMergeColumns
import ch.tutteli.atrium.reporting.failWithBugErrorIf
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TypedDesignatorPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.transformAndGetSingleNode
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.theming.text.StyledString

class DefaultInlineDesignatorPreRenderer(
    private val objectFormatter: TextObjFormatter
) : TypedDesignatorPreRenderer<InlineElement>(InlineElement::class) {

    override fun transformIt(
        description: InlineElement,
        representation: Any,
        prefixDescriptionColumns: List<StyledString>,
        suffixDescriptionColumns: List<StyledString>,
        startMergeAtColumn: Int,
        children: List<OutputNode>,
        controlObject: TextPreRenderControlObject,
    ): List<OutputNode> {

        val descriptionColumns = getDescriptionColumns(description, controlObject)

        // one column is normal hence - 1, only if description takes 2 or more columns we need to merge x - 1
        val mergeColumns =
            (prefixDescriptionColumns.size + descriptionColumns.size + suffixDescriptionColumns.size) - 1

        return listOf(
            OutputNode(
                columns = ArrayList<StyledString>(mergeColumns + 2).apply {
                    prefixDescriptionColumns.forEach {
                        checkIsNoLineBreakDueToMergeColumns(it)
                        add(it)
                    }
                    addAll(descriptionColumns)
                    suffixDescriptionColumns.forEach {
                        checkIsNoLineBreakDueToMergeColumns(it)
                        add(it)
                    }
                    add(StyledString.COLON_SEPARATOR)
                    if (controlObject.explainsProof.not()) {
                        add(objectFormatter.format(representation))
                    }
                },
                children = children,
                // a group defines an own level
                definesOwnLevel = true,
                mergeColumns = mergeColumns,
                startMergeAtColumn = startMergeAtColumn
            )
        )
    }

    private fun getDescriptionColumns(
        description: InlineElement,
        controlObject: TextPreRenderControlObject
    ): List<StyledString> {
        val node = controlObject.transformAndGetSingleNode(description)

        failWithBugErrorIf(node.columns.isEmpty()) {
            "transformChild for InlineElement $description returned 0 columns"
        }
        // we are going to merge the columns, hence ensure noLineBreak=true
        return node.columns.map { it.withoutLineBreaks() }
    }
}
