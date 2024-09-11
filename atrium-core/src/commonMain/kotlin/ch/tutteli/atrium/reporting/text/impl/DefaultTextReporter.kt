package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.core.polyfills.appendSpaces
import ch.tutteli.atrium.core.polyfills.appendln
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.text.TextReporter
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderController
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.checkIsNoLineBreakDueToMergeColumns
import ch.tutteli.atrium.reporting.theming.text.pad
import ch.tutteli.kbox.ifWithinBound

class DefaultTextReporter(
    private val preRenderController: TextPreRenderController
) : TextReporter {

    override fun createReport(reportable: Reportable): StringBuilder {
        val rootNodes = preRenderController.transformRoot(reportable)
        if (rootNodes.size != 1) throw IllegalStateException(
            "the transformation of the root reportable (in this case a ${
                reportable::class.fullName(reportable)
            } should always result in one ${OutputNode::class.simpleName}, was ${rootNodes.size}"
        )
        val rootNode = rootNodes[0]
        val sb = StringBuilder()
        format(rootNode, emptyList(), sb)
        return sb
    }

    private fun format(outputNode: OutputNode, indentLevels: List<Int>, sb: StringBuilder) =
        format(outputNode, indentLevels, sb, calculateMaxLengths(outputNode))


    private fun format(outputNode: OutputNode, indentLevels: List<Int>, sb: StringBuilder, maxLengths: List<Int>) {

        fun indentWithSpaces(indentLevel: Int, untilColumn: Int) {
            (0 until minOf(indentLevel, untilColumn)).forEach {
                sb.appendSpaces(indentLevels[it])
            }
            (indentLevel until untilColumn).forEach { i ->
                sb.appendSpaces(maxLengths[i])
            }
        }

        fun appendColumn(columns: List<StyledString>, index: Int) {
            val styledString = columns[index]
            if (styledString.noLineBreak) {
                sb.append(styledString.pad(maxLengths[index]))
            } else {
                val lines = styledString.unstyled.split(Regex("\r\n|\n"))
                if (lines.size == 1) {
                    // no newline, we can just pad the styledString
                    sb.append(styledString.pad(maxLengths[index]))
                } else {
                    throw UnsupportedOperationException("wrapping (i.e. noLineBreak=false) is only supported for the last column")
                }
            }
        }

        fun appendColumns(node: OutputNode) {
            val columns = node.columns
            val size = columns.size
            if (size > 0) {
                val indentLevel = node.indentLevel
                val mergeColumns = node.mergeColumns

                (0 until indentLevel).forEach { i ->
                    val styledString = columns[i]
                    //TODO 1.3.0 what about wrapping throw an error as well?
                    sb.append(styledString.pad(indentLevels[i]))
                }

                val startIndex = if (mergeColumns > 0) {
                    (indentLevel until node.startMergeAtColumn).forEach { i ->
                        // we want to merge columns but apparently not directly after the indent, i.e. append those
                        // columns first
                        appendColumn(columns, index = i)
                    }

                    var additionalPadding = 0
                    var index = node.startMergeAtColumn
                    //TODO 1.3.0 take alignment into account, we should merge all the styled String into one
                    // where we keep only the align of the last column and then pad
                    repeat(mergeColumns) {
                        val styledString = columns[index]
                        checkIsNoLineBreakDueToMergeColumns(styledString)
                        sb.append(styledString.result())
                        additionalPadding += maxLengths[index] - styledString.unstyled.length
                        ++index
                    }

                    val styledString = columns[index]
                    checkIsNoLineBreakDueToMergeColumns(styledString)
                    sb.append(
                        styledString.pad(additionalPadding + maxLengths[index])
                    )
                    ++index
                } else {
                    indentLevel
                }
                val lastIndex = size - 1
                (startIndex until lastIndex).forEach { i ->
                    appendColumn(columns, index = i)
                }
                val lastColumn = columns[lastIndex]
                if (lastColumn.noLineBreak) {
                    sb.append(lastColumn.result())
                } else {
                    val lines = lastColumn.result().split(Regex("\r\n|\n"))
                    sb.append(lines.first())
                    lines.drop(1).forEach { line ->
                        sb.appendln()
                        indentWithSpaces(indentLevel = indentLevel, untilColumn = lastIndex)
                        sb.append(line)
                    }
                }
            }
        }

        appendColumns(outputNode)
        outputNode.children.forEachIndexed { index, child ->
            if (outputNode.columns.isNotEmpty() || index > 0) {
                sb.appendln()
            }
            if (child.definesOwnLevel) {
                //TODO 2.0.0 don't call calculateMaxLengths here but use format with 3 args instead if we restrict
                // that node.columns has to be non-empty (currently only the case because of ExplanatoryAssertionGroup)
                // because then the indent is always given via description columns and we don't need to do this hack
                val newMaxLengths = calculateMaxLengths(child)
                val maxLengthsToConvertToIndent =
                    if (child.columns.isNotEmpty()) {
                        child.indentLevel - outputNode.indentLevel
                    } else {
                        val numberOfZeroIndents =
                            newMaxLengths.asSequence().drop(indentLevels.size).takeWhile { it == 0 }.count()
                        maxOf(child.indentLevel - outputNode.indentLevel, numberOfZeroIndents)
                    }
                val newIndentLevels = indentLevels +
                    maxLengths.asSequence().drop(indentLevels.size).take(maxLengthsToConvertToIndent)
                format(child, newIndentLevels, sb, newMaxLengths)
            } else {
                format(child, indentLevels + maxLengths.drop(indentLevels.size), sb, maxLengths)
            }
        }
    }

    private fun calculateMaxLengths(node: OutputNode): List<Int> {
        val maxLengths = mutableListOf<Int>()

        fun updateMaxLengthsIfNecessary(index: Int, length: Int) {
            maxLengths.ifWithinBound(
                index,
                thenBlock = {
                    if (maxLengths[index] < length) {
                        maxLengths[index] = length
                    }
                },
                elseBlock = { maxLengths.add(index, length) }
            )
        }

        fun updateMaxLengthsIfNecessary(index: Int, styledString: StyledString) {
            updateMaxLengthsIfNecessary(index, styledString.getMaxUnstyledLineLength())
        }

        fun updateMaxLengths(columns: Iterable<IndexedValue<StyledString>>) {
            columns.forEach { (index, styledString) ->
                updateMaxLengthsIfNecessary(index, styledString)
            }
        }

        fun updateMaxLengths(child: OutputNode) =
            updateMaxLengths(child.columns.withIndex())

        node.children
            .asSequence()
            .filterNot { it.definesOwnLevel }
            .flatMap {
                if (it.columns.isEmpty()) {
                    it.children.asSequence()
                        .filterNot { subChild -> subChild.definesOwnLevel }
                } else sequenceOf(it)
            }
            .forEach(::updateMaxLengths)

        // we are still interested in the first column of children which define an own level as the prefix (which is
        // the first column after indent of this node) still counts towards the alignment of this node. Now, if the child
        // should be further indented (i.e. prefix is after node.indentLevel + 1) then it doesn't count towards the
        // lengths of this node
        node.children.asSequence()
            .filter { it.definesOwnLevel }
            .flatMap {
                it.columns.asSequence().withIndex().take(node.indentLevel + 1)
            }
            .forEach { (index, styledString) ->
                updateMaxLengthsIfNecessary(index, styledString)
            }

        val columns = node.columns
        if (node.mergeColumns > 0) {
            val (mergedColumns, rest) = columns.withIndex().partition {
                it.index >= node.startMergeAtColumn && it.index <= node.startMergeAtColumn + node.mergeColumns
            }
            val (lastIndex, lastStyledString) = mergedColumns.last()

            // If all children definesOnwLevel=true and itself indent more than the node + 1 then the columns between
            // indent and startMergeAtColumn were not calculated yet, i.e. are not defined yet in maxLengths, we
            // initialise them here with 0 in such as case
            (maxLengths.size until node.startMergeAtColumn).forEach { index ->
                maxLengths.add(index, 0)
            }

            // if we merge columns and the children's indent is in the range of the merged columns then those columns
            // would have a length of 0 if we don't calculate the length of those columns as well.
            (node.startMergeAtColumn until lastIndex).forEach { index ->
                updateMaxLengthsIfNecessary(index, columns[index])
            }

            val mergedLength = mergedColumns
                .take(mergedColumns.size - 1)
                .sumOf { (index, value) ->
                    value.unstyled.length - maxLengths[index]
                } + lastStyledString.unstyled.length

            updateMaxLengthsIfNecessary(lastIndex, mergedLength)
            updateMaxLengths(rest)
        } else {
            updateMaxLengths(columns.withIndex())
        }

        return maxLengths
    }

    private fun StyledString.getMaxUnstyledLineLength() =
        if (noLineBreak) unstyled.length
        else unstyled.split("\n").maxOf { it.length }

}
