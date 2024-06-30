package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.filters.ReportableFilter
import ch.tutteli.atrium.reporting.prerendering.text.*
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler
import ch.tutteli.kbox.ifNotEmpty

internal class DefaultTextPreRenderController(
    //TODO 1.3.0 does it make sense to use Sequence, why not List, looks like we don't do operations with it
    private val preRenderers: Sequence<TextPreRenderer>,
    private val designationPreRenderer: Sequence<TextDesignationPreRenderer>,
    private val iconStyler: TextIconStyler,
    private val reportableFilter: ReportableFilter,
) : TextPreRenderController {

    override fun transformRoot(reportable: Reportable, indentNewLine: Boolean): List<OutputNode> =
        transformChildIncludingIndentationAndPrefix(
            reportable,
            TextPreRenderControlObject(Icon.EMPTY_STRING, 0, controller = this, reportableFilter, explainsProof = false)
        )

    override fun transformChildIncludingIndentationAndPrefix(
        child: Reportable,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> = transformChild(child, controlObject).map { node ->
        indentAndPrefix(node, controlObject)
    }

    override fun transformChild(child: Reportable, controlObject: TextPreRenderControlObject): List<OutputNode> =
        if (controlObject.includeInReporting(child)) {
            val preRenderer = preRenderers
                .firstOrNull { it.canTransform(child) }
                ?: throw UnsupportedOperationException("no suitable ${TextPreRenderer::class.simpleName} found for the given reportable ${child::class.simpleName}: $child")
            preRenderer.transform(child, controlObject)
        } else {
            emptyList()
        }

    override fun transformGroup(
        description: Reportable,
        representation: Any,
        controlObject: TextPreRenderControlObject,
        children: List<OutputNode>,
        prefixDescriptionColumns: List<StyledString>,
        suffixDescriptionColumns: List<StyledString>,
        startMergeAtColumn: Int
    ): List<OutputNode> {
        //TODO #724 allow that one can specify a reportableFilter which is only used on the current level but fall
        // back to default for its children

        val preRenderer = designationPreRenderer
            .firstOrNull { it.canTransform(description) }
            ?: throw UnsupportedOperationException("no suitable ${TextDesignationPreRenderer::class.simpleName} found for the given description: $description")
        return preRenderer.transform(
            description,
            representation,
            prefixDescriptionColumns,
            suffixDescriptionColumns,
            startMergeAtColumn,
            children,
            controlObject
        )
    }

    private fun indentAndPrefix(
        node: OutputNode,
        controlObject: TextPreRenderControlObject
    ): OutputNode {
        val newColumns =   node.columns.ifNotEmpty { columns ->
            val list = ArrayList<StyledString>(
                controlObject.indentLevel + (if (node.usesOwnPrefix.not()) 1 else 0) + columns.size
            )

            // start inserting styled strings after indent..
            var index = controlObject.indentLevel
            // i.e. first add the indent in form of empty styled strings
            (0 until index).forEach { i ->
                list.add(i, StyledString.EMPTY_STRING)
            }

            fun addToList(styledString: StyledString) = list.add(index++, styledString)

            if (node.usesOwnPrefix.not()) {
                addToList(iconStyler.style(controlObject.prefix))
            }

            columns.dropLast(1).forEach(::addToList)
            columns.last().also { styledString ->
                if (styledString.unstyled != "") {
                    addToList(styledString)
                }
            }

            list
        }

        val newIndentLevel = node.indentLevel + controlObject.indentLevel
        return node.copy(
            columns = newColumns,
            indentLevel = newIndentLevel,
            //TODO 1.3.0 not happy with this and DefaultFeatureProofGroupTextPreRenderer, does not make sense
            // that DefaultFeatureProofGroupTextPreRenderer defines startMergeAtColumn = 1 because in theory it cannot
            // know how many columns the prefix spans and it should be here were we define it
            startMergeAtColumn = ch.tutteli.kbox.takeIf(node.mergeColumns > 0) { newIndentLevel + node.startMergeAtColumn }
                ?: 0
        )
    }
}
