package ch.tutteli.atrium.reporting.prerendering.text

import ch.tutteli.atrium.reporting.theming.text.StyledString

// TODO 1.3.0 KDOC
data class OutputNode(
    val columns: List<StyledString>,
    val children: List<OutputNode>,
    val definesOwnLevel: Boolean,
    val indentLevel: Int = 0,
    val mergeColumns: Int = 0,
    val startMergeAtColumn: Int = indentLevel,
    //TODO 1.3.0 check if this can be removed again, is this functionality used? I guess we will use it for manual info points as in `because`
    val usesOwnPrefix: Boolean = false,
) {
    init {
        if (columns.isEmpty()) {
            require(children.isNotEmpty()) { "a node without columns need to define children" }
            val childWithoutColumnsNotOwnLevel = children.filter { it.definesOwnLevel.not() && it.columns.isEmpty() }
            require(childWithoutColumnsNotOwnLevel.isEmpty()) {
                "a node without columns cannot define children with definesOwnLevel=false which don't have columns (they should be flattened instead). Following children don't define own columns:\n${
                    childWithoutColumnsNotOwnLevel.joinToString(
                        ", "
                    )
                }"
            }
        }
        require(indentLevel >= 0) { "cannot define a negative indent level, was $indentLevel" }
        require(mergeColumns >= 0) { "cannot merge columns backwards, mergeColumns was $mergeColumns" }
        val mergeUntil = startMergeAtColumn + mergeColumns
        require(mergeUntil == 0 || mergeUntil < columns.size) { "cannot merge more than available columns. startMergeAtColumn: $startMergeAtColumn, mergeColumns: $mergeColumns, columns size: ${columns.size} (column index is 0 based)" }
        if (mergeColumns > 0) {
            require(startMergeAtColumn >= indentLevel) { "cannot start merging columns at $startMergeAtColumn needs to be >= indentLevel which is $indentLevel" }
        } else {
            require(startMergeAtColumn == 0) { "cannot define startMergeAtColumn > 0 (was $startMergeAtColumn) if we don't want to merge columns (mergeColumns was $mergeColumns)" }
        }
    }

    companion object {
        fun singleWithoutColumnsNotOwnLevel(children: List<OutputNode>) =
            listOf(
                OutputNode(
                    columns = emptyList(),
                    children = children.flatMap {
                        if (it.definesOwnLevel.not() && it.columns.isEmpty()) it.children
                        else listOf(it)
                    },
                    definesOwnLevel = false,
                )
            )
    }
}
