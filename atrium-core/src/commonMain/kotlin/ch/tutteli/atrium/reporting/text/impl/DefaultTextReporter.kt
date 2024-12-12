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
        format(rootNode, emptyList(), sb, calculateMaxMonospaceLengths(rootNode, mutableListOf()))
        return sb
    }

    private fun format(
        outputNode: OutputNode,
        indentLevels: List<Int>,
        sb: StringBuilder,
        maxMonospaceLengths: List<Int>
    ) {

        fun indentWithSpaces(indentLevel: Int, untilColumn: Int) {
            (0 until minOf(indentLevel, untilColumn)).forEach {
                sb.appendSpaces(indentLevels[it])
            }
            (indentLevel until untilColumn).forEach { i ->
                sb.appendSpaces(maxMonospaceLengths[i])
            }
        }

        fun appendColumn(columns: List<StyledString>, index: Int) {
            val styledString = columns[index]
            if (styledString.noLineBreak) {
                sb.append(styledString.padString(maxMonospaceLengths[index]))
            } else {
                val lines = styledString.unstyled.split(Regex("\r\n|\n"))
                if (lines.size == 1) {
                    // no newline, we can just pad the styledString
                    sb.append(styledString.padString(maxMonospaceLengths[index]))
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
                    val indent = indentLevels[i]
                    sb.append(styledString.padString(if (indent > 0) indent else 0))
                }

                val startIndex = if (mergeColumns == 0) {
                    indentLevel
                } else {
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
                        sb.append(styledString.getString())
                        additionalPadding += maxMonospaceLengths[index] - styledString.monospaceLength
                        ++index
                    }

                    val styledString = columns[index]
                    checkIsNoLineBreakDueToMergeColumns(styledString)
                    sb.append(
                        styledString.padString(additionalPadding + maxMonospaceLengths[index])
                    )
                    ++index
                }

                val lastIndex = size - 1
                (startIndex until lastIndex).forEach { i ->
                    appendColumn(columns, index = i)
                }
                val lastColumn = columns[lastIndex]
                if (lastColumn.noLineBreak) {
                    sb.append(lastColumn.getString())
                } else {
                    val lines = lastColumn.getString().split(Regex("\r\n|\n"))
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

        val indentLevelsForChildrenWhichDoNotDefineOwnLevel =
            indentLevels + maxMonospaceLengths.asSequence().drop(indentLevels.size)

        outputNode.children.forEachIndexed { index, child ->
            if (outputNode.columns.isNotEmpty() || index > 0) {
                sb.appendln()
            }

            if (child.definesOwnLevel) {
                val (newIndentLevels, newMaxMonospaceLengths) = calculateIndentLevelsAndMaxLengths(
                    child, outputNode, indentLevels, maxMonospaceLengths
                )
                format(child, newIndentLevels, sb, newMaxMonospaceLengths)
            } else {
                format(child, indentLevelsForChildrenWhichDoNotDefineOwnLevel, sb, maxMonospaceLengths)
            }
        }
    }

    private fun calculateIndentLevelsAndMaxLengths(
        child: OutputNode,
        parent: OutputNode,
        parentIndentLevels: List<Int>,
        parentMaxMonospaceLengths: List<Int>
    ): Pair<List<Int>, List<Int>> {
        val newMaxMonospaceLengths = calculateMaxMonospaceLengths(
            child,
            parentMaxMonospaceLengths.take(
                //TODO 1.3.0 take final decision, change and execute CreateReportTest
//                run {
//                    parent.children.filter { it.definesOwnLevel.not() }
//                        .minOfOrNull { it.indentLevel }
//                        ?:
//                        parent.indentLevel
//                } + 1
                child.indentLevel
            )
        )

        val indentLevelsInheritedFromParent = run {
            // the child defines an own level and hence also own indent levels. It only "inherits" the
            // indent levels of the parent for which it itself is already indented. For instance:
            // ------------------------------
            // I expected subject: 1
            // (d) some debug info
            //     (u) usage hint
            // -------------------------
            // above the debug group `(d)` defines an own level. Since it is not indented at all, it doesn't
            // "inherit" any identLevel from the root group. On the other hand, the usage hint group `(u)` which
            // defines an own level as well, inherits one indent level from the debug group (indentLevel[0] = 4)
            if (child.indentLevel == 0) emptySequence()
            else parentIndentLevels.asSequence().take(child.indentLevel)
        }

        val indentLevelsDeducedFromMaxMonospaceLengthOfParent = run {

            val maxMonospaceLengthsFromParentToConvertToIndent = run {
                // TODO 1.4.0 consider the following, once we drop ExplanatoryAssertionGroup then we don't have any
                // pre-renderer left which defines an outputNode without columns but with definesOwnLevel=true
                // in such a case the if branch is always taken and we could simplify this code (we should then
                // establish a corresponding invariant in OutputNode)
                if (child.columns.isNotEmpty()) {
                    child.indentLevel - parent.indentLevel
                } else {
                    // special case nested ExplanatoryAssertionGroup where all children define an own level and
                    // are indented further, for instance:
                    // ---------------------------
                    // (i) properties of the unknown Exception
                    //     » stacktrace :
                    //       ⚬ test
                    //       ⚬ lines
                    //-----------------------------
                    // `(i)` is an ExplanatoryAssertionGroup and the `stacktrace` is a proof group wrapped into another
                    // ExplanatoryAssertionGroup. The indentLevel of `(i)` is 0 here, same same for the hidden
                    // ExplanatoryGroup (i.e. child.indentLevel - parent.indentLevel would be 0). However, `stacktrace`
                    // has indentLevel=1 which only works if we take the maxMonospaceLength[0] from the parent (because
                    // the newMaxMonospaceLength[0] = 0)
                    val numberOfZeroIndents = newMaxMonospaceLengths.asSequence()
                        .drop(child.indentLevel)
                        .takeWhile { it == 0 }.count()
                    maxOf(child.indentLevel - parent.indentLevel, numberOfZeroIndents)
                }
            }

            if(maxMonospaceLengthsFromParentToConvertToIndent == 0) emptySequence()
            else parentMaxMonospaceLengths.asSequence()
                .drop(minOf(parentIndentLevels.size, child.indentLevel))
                .take(maxMonospaceLengthsFromParentToConvertToIndent)
        }

        val newIndentLevels = run {
            indentLevelsInheritedFromParent + indentLevelsDeducedFromMaxMonospaceLengthOfParent
        }.toList()
        return newIndentLevels to newMaxMonospaceLengths
    }

    private fun calculateMaxMonospaceLengths(
        node: OutputNode,
        parentMaxMonospaceLengthsToConsider: List<Int>
    ): List<Int> {
        val maxMonospaceLengths = mutableListOf<Int>()
        maxMonospaceLengths.addAll(parentMaxMonospaceLengthsToConsider)

        fun updateMaxMonospaceLengthsIfNecessary(index: Int, length: Int) {
            maxMonospaceLengths.ifWithinBound(
                index,
                thenBlock = {
                    if (maxMonospaceLengths[index] < length) {
                        maxMonospaceLengths[index] = length
                    }
                },
                elseBlock = { maxMonospaceLengths.add(index, length) }
            )
        }

        fun updateMaxMonospaceLengthsIfNecessary(index: Int, styledString: StyledString) {
            updateMaxMonospaceLengthsIfNecessary(index, styledString.maxLineMonospaceLength)
        }

        fun updateMaxMonospaceLengths(columns: Iterable<IndexedValue<StyledString>>) {
            columns.forEach { (index, styledString) ->
                updateMaxMonospaceLengthsIfNecessary(index, styledString)
            }
        }

        fun updateMaxMonospaceLengths(child: OutputNode) =
            updateMaxMonospaceLengths(child.columns.withIndex())

        // if we have children without own columns (i.e. invisible groups) then we need to take their children
        // into account as well.
        val childrenToTakeIntoAccount = node.children.asSequence().flatMap {
            if (it.columns.isEmpty() && it.definesOwnLevel.not()) {
                it.children.asSequence()
            } else sequenceOf(it)
        }

        childrenToTakeIntoAccount
            .filterNot { it.definesOwnLevel }
            .forEach(::updateMaxMonospaceLengths)

        // we are still interested in the first column of children which define an own level, as the prefix (which is
        // the first column after indent of this node) still counts towards the alignment of this node. Now, if the
        // child should be further indented (i.e. prefix is after node.indentLevel + 1) then it doesn't count towards
        // the lengths of this node
        childrenToTakeIntoAccount
            .filter { it.definesOwnLevel }
            .flatMap {
                it.columns.asSequence().withIndex().take(node.indentLevel + 1)
            }
            .forEach { (index, styledString) ->
                updateMaxMonospaceLengthsIfNecessary(index, styledString)
            }

        val columns = node.columns
        if (node.mergeColumns > 0) {
            val (mergedColumns, rest) = columns.withIndex().partition {
                it.index >= node.startMergeAtColumn && it.index <= node.startMergeAtColumn + node.mergeColumns
            }
            val (lastIndex, lastStyledString) = mergedColumns.last()

            // If all children definesOnwLevel=true and itself indent more than the node + 1 then the columns between
            // indent and startMergeAtColumn were not calculated yet, i.e. are not defined yet in maxMonospaceLengths,
            // we initialise them here with 0 in such as case
            (maxMonospaceLengths.size until node.startMergeAtColumn).forEach { index ->
                maxMonospaceLengths.add(index, 0)
            }

            // if we merge columns and the children's indent is in the range of the merged columns then those columns
            // would have a monospaceLength of 0 if we don't calculate the monospaceLength of those columns as well.
            (node.startMergeAtColumn until lastIndex).forEach { index ->
                updateMaxMonospaceLengthsIfNecessary(index, columns[index])
            }

            val mergedMonospaceLength = mergedColumns
                .take(mergedColumns.size - 1)
                .sumOf { (index, value) ->
                    value.monospaceLength - maxMonospaceLengths[index]
                } + lastStyledString.monospaceLength


            updateMaxMonospaceLengthsIfNecessary(lastIndex, mergedMonospaceLength)
            updateMaxMonospaceLengths(rest)
        } else {
            updateMaxMonospaceLengths(columns.withIndex())
        }

        return maxMonospaceLengths
    }
}
