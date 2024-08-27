package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.core.polyfills.appendln
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.proofs.*
import ch.tutteli.atrium.reporting.reportables.*
import ch.tutteli.kbox.ifWithinBound
import ch.tutteli.kbox.takeIf
import com.github.ajalt.mordant.rendering.*
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.reflect.KClass

/**
 * Represents a reporter which reports about [Reportable]s.
 */
//TODO 1.3.0 deprecate
interface Reporter {

    /**
     * Reports about the given [assertion], using the given [sb] where the actual
     * implementation will decide whether the given [assertion] is noteworthy to be reported.
     *
     * @param sb The [StringBuilder] which can be used for reporting.
     * @param assertion The assertion which should be considered for reporting.
     */
    @Deprecated(
        "switch from Assertion to Proof based reporting, will be removed with 2.0.0 at the latest",
        ReplaceWith("sb.append(this.createReport(assertion))")
    )
    fun format(@Suppress("DEPRECATION") assertion: Assertion, sb: StringBuilder)

    /**
     * Creates a report about the given [reportable].
     *
     * @param reportable The [Reportable] for which a report shall be created.
     *
     * @return The report as StringBuilder
     */
    //TODO 2.0.0 remove default implementation, only here to retain source compatibility
    //TODO 1.3.0 require a proof instead?
    fun createReport(reportable: Reportable): StringBuilder =
        throw UnsupportedOperationException("Either you are still using OnlyFailureReporter which is deprecated and cannot be used for Proof-based reporting (switch to TextReporter) or you implemented a custom reporter which is still based on Assertion instead of Proof.")
}

interface TextReporter : Reporter {
    @Deprecated(
        "use createReport, will be removed with 2.0.0 at the latest",
        ReplaceWith("sb.append(this.createReport(assertion))"),
        level = DeprecationLevel.ERROR
    )
    override fun format(@Suppress("DEPRECATION") assertion: Assertion, sb: StringBuilder) =
        throw UnsupportedOperationException("no longer supported")
}

/**
 * A [Reporter] which reports only failing assertions.
 */
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

        fun indentWithSpaces(untilColumn: Int) {
            (0 until untilColumn).forEach { i ->
                sb.appendSpaces(maxLengths[i])
            }
        }

        fun appendColumn(
            columns: List<StyledString>,
            index: Int,
            indentLevel: Int
        ) {
            val styledString = columns[index]
            if (styledString.noLineBreak) {
                sb.append(styledString.pad(maxLengths[index]))
            } else {
                val lines = styledString.unstyled.split(Regex("\r\n|\n"))
                if (lines.size == 1) {
                    // no newline, we can just pad the styledString
                    sb.append(styledString.pad(maxLengths[index]))
                } else {
                    val styledLines = styledString.maybeStyled.fold(
                        {
                            lines.asSequence().map { it.noStyle() }
                        },
                        {
                            it.split('\n').asSequence().zip(lines.asSequence()).map { (styled, unstyled) ->
                                StyledString(
                                    unstyled,
                                    Some(styled),
                                    noLineBreak = true,
                                    align = styledString.align,
                                )
                            }
                        }
                    )
                    sb.append(styledLines.first().pad(maxLengths[index]))
                    styledLines.drop(1).forEach { styledLine ->
                        sb.appendln()
                        indentWithSpaces(untilColumn = indentLevel + index)
                        sb.append(styledLine.pad(maxLengths[index]))
                    }
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
                    //TODO what about wrapping throw an error as well?
                    sb.append(styledString.pad(indentLevels[i]))
                }

                val startIndex = if (mergeColumns > 0) {
                    (indentLevel until node.startMergeAtColumn).forEach { i ->
                        // we want to merge columns but apparently not directly after the indent, i.e. append those
                        // columns first
                        appendColumn(columns, indentLevel = indentLevel, index = i)
                    }

                    var additionalPadding = 0
                    var index = node.startMergeAtColumn
                    //TODO take alignment into account, we should merge all the styled String into one
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
                    appendColumn(columns, indentLevel = indentLevel, index = i)
                }
                val lastColumn = columns[lastIndex]
                if (lastColumn.noLineBreak) {
                    sb.append(lastColumn.result())
                } else {
                    val lines = lastColumn.result().split(Regex("\r\n|\n"))
                    sb.append(lines.first())
                    lines.drop(1).forEach { line ->
                        sb.appendln()
                        indentWithSpaces(untilColumn = indentLevel + lastIndex)
                        sb.append(line)
                    }
                }
            }
        }

        appendColumns(outputNode)
        outputNode.children.forEachIndexed { index, child ->
            if (outputNode.columns.isNotEmpty() || index == 1) {
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

    private fun StyledString.getMaxUnstyledLineLength() =
        if (noLineBreak) unstyled.length
        else unstyled.split("\n").maxOf { it.length }

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
}


data class PreRenderControlObject(
    val prefix: Icon,
    val indentLevel: Int,
    private val controller: PreRenderController,
    val reportableFilter: ReportableFilter,
) : PreRenderController by controller {
    fun includeInReporting(reportable: Reportable) = reportableFilter.includeInReporting(reportable)
}

interface TextPreRenderer {
    fun canTransform(reportable: Reportable): Boolean
    fun transform(reportable: Reportable, controlObject: PreRenderControlObject): List<OutputNode>
}

interface TextDesignationPreRenderer {
    fun canTransform(description: Reportable): Boolean
    fun transform(
        description: Reportable,
        representation: Any,
        prefixDescriptionColumns: List<StyledString>,
        suffixDescriptionColumns: List<StyledString>,
        startMergeAtColumn: Int,
        children: List<OutputNode>,
        controlObject: PreRenderControlObject,
    ): List<OutputNode>
}

abstract class TypedTextPreRenderer<R : Reportable>(private val kClass: KClass<R>) : TextPreRenderer {
    final override fun canTransform(reportable: Reportable): Boolean = kClass.isInstance(reportable)
    final override fun transform(reportable: Reportable, controlObject: PreRenderControlObject): List<OutputNode> =
        transformIt(kClass.cast(reportable), controlObject)

    protected abstract fun transformIt(reportable: R, controlObject: PreRenderControlObject): List<OutputNode>
}

abstract class TypedDesignatorPreRenderer<R : Reportable>(private val kClass: KClass<R>) :
    TextDesignationPreRenderer {
    final override fun canTransform(description: Reportable): Boolean = kClass.isInstance(description)
    override fun transform(
        description: Reportable,
        representation: Any,
        prefixDescriptionColumns: List<StyledString>,
        suffixDescriptionColumns: List<StyledString>,
        startMergeAtColumn: Int,
        children: List<OutputNode>,
        controlObject: PreRenderControlObject,
    ): List<OutputNode> = transformIt(
        kClass.cast(description),
        representation,
        prefixDescriptionColumns,
        suffixDescriptionColumns,
        startMergeAtColumn,
        children,
        controlObject
    )

    protected abstract fun transformIt(
        description: R,
        representation: Any,
        prefixDescriptionColumns: List<StyledString>,
        suffixDescriptionColumns: List<StyledString>,
        startMergeAtColumn: Int,
        children: List<OutputNode>,
        controlObject: PreRenderControlObject,
    ): List<OutputNode>
}


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
        require(columns.isNotEmpty() || children.isNotEmpty()) { "at least one of columns/children needs to have elements" }
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
}

interface ThemeProvider {
    fun render(s: String, styleId: String): String?
}

interface TextThemeProvider : ThemeProvider {
    val supportsAnsi: Boolean
}

abstract class MordantBasedThemeProvider(
    private val terminal: Terminal
) : TextThemeProvider {
    protected abstract val styleIdToTextStyle: Map<String, TextStyle>

    override val supportsAnsi: Boolean = terminal.info.ansiLevel != AnsiLevel.NONE

    override fun render(unstyledString: String, styleId: String): String? =
        styleIdToTextStyle[styleId]?.let { textStyle ->
            val spans = mutableListOf<Span>()
            var sb = StringBuilder()
            var isSbForWhitespace = false
            unstyledString.forEach {
                val isWhiteSpace = it.isWhitespace()
                if (isSbForWhitespace != isWhiteSpace) {
                    if (sb.isNotEmpty()) {
                        spans.add(Span.word(sb.toString(), textStyle))
                        sb = StringBuilder()
                    }
                    isSbForWhitespace = isWhiteSpace
                }
                sb.append(it)
            }
            spans.add(Span.word(sb.toString(), textStyle))
            terminal.render(Lines(listOf(Line(spans))))
        }
}

class DefaultThemeProvider(terminal: Terminal) : MordantBasedThemeProvider(terminal) {
    override val styleIdToTextStyle: Map<String, TextStyle> = mapOf(
        Style.BULB.styleId to TextStyle(TextColors.yellow, bold = true),
        Style.DEBUG_INFO.styleId to TextColors.blue,
        Style.FAILURE.styleId to TextColors.red,
        Style.FEATURE.styleId to TextColors.cyan,
        Style.INFORMATION_SOURCE.styleId to TextStyle(TextColors.brightBlue, bold = true),
        Style.SUCCESS.styleId to TextColors.green,
    )
}

enum class Style(val styleId: String) {
    BULB("BULB"),
    DEBUG_INFO("DEBUG_INFO"),
    FAILURE("FAILURE"),
    FEATURE("FEATURE"),
    INFORMATION_SOURCE("INFORMATION_SOURCE"),
    SUCCESS("SUCCESS"),
}

interface TextStyler {
    fun style(unstyledString: String, maybeStyle: Option<Style>, noLineBreak: Boolean = true): StyledString =
        maybeStyle.fold(
            { unstyledString.noStyle(noLineBreak) },
            { styledString -> style(unstyledString, styledString, noLineBreak) }
        )

    fun style(
        unstyledString: String,
        style: Style,
        noLineBreak: Boolean = true,
        align: HorizontalAlignment = HorizontalAlignment.LEFT
    ): StyledString = style(unstyledString, style.styleId, noLineBreak, align)

    fun style(
        unstyledString: String,
        styleId: String,
        noLineBreak: Boolean = true,
        align: HorizontalAlignment = HorizontalAlignment.LEFT
    ): StyledString
}


class DefaultTextStyler(
    private val textThemeProvider: TextThemeProvider,
) : TextStyler {

    override fun style(
        unstyledString: String,
        styleId: String,
        noLineBreak: Boolean,
        align: HorizontalAlignment
    ): StyledString {
        val maybeStyled = takeIf(textThemeProvider.supportsAnsi) {
            textThemeProvider.render(unstyledString, styleId)?.let { Some(it) }
        } ?: None
        return StyledString(unstyledString, maybeStyled = maybeStyled, noLineBreak = noLineBreak, align = align)
    }
}


enum class HorizontalAlignment {
    LEFT,
    CENTER,
    RIGHT,
}

//TODO 1.3.0 KDOC
interface StyledString {
    val unstyled: String
    val maybeStyled: Option<String>
    val noLineBreak: Boolean
    val align: HorizontalAlignment
    fun result(): String

    fun withoutLineBreaks(): StyledString
    fun appendUnstyled(unstyledString: String): StyledString

    companion object {
        operator fun invoke(
            unstyled: String,
            maybeStyled: Option<String>,
            noLineBreak: Boolean = true,
            align: HorizontalAlignment = HorizontalAlignment.LEFT,
        ): StyledString = DefaultStyledString(
            unstyled = unstyled,
            maybeStyled = maybeStyled,
            noLineBreak = noLineBreak,
            align = align,
        )

        val EMPTY_STRING = object : StyledString {
            override val unstyled: String = ""
            override val maybeStyled: Option<String> = None
            override val noLineBreak: Boolean = true
            override val align: HorizontalAlignment = HorizontalAlignment.LEFT

            override fun withoutLineBreaks() = this
            override fun appendUnstyled(unstyledString: String): StyledString =
                unstyledString.noStyle()

            override fun result(): String = unstyled

            override fun toString(): String = "SS()"
        }
    }
}

private fun checkIsNoLineBreakDueToMergeColumns(styledString: StyledString) {
    if (styledString.noLineBreak.not()) {
        throw UnsupportedOperationException("cannot merge columns which allow line breaks at the same time, behaviour not defined yet, please open a feature request $BUG_REPORT_URL")
    }
}


data class DefaultStyledString(
    override val unstyled: String,
    override val maybeStyled: Option<String>,
    override val noLineBreak: Boolean,
    override val align: HorizontalAlignment,
) : StyledString {

    override fun toString(): String = "SS(u=$unstyled, n=$noLineBreak, a=${align.name})"

    override fun result(): String {
        val s = maybeStyled.getOrElse { unstyled }
        return replaceWrap(s)
    }

//    override operator fun plus(s: StyledString): StyledString =
//        DefaultStyledString(
//        unstyled + s.unstyled,
//        if (maybeStyled.isDefined() || s.maybeStyled.isDefined()) {
//            Some(result() + s.result())
//        } else {
//            None
//        }
//    )

    override fun withoutLineBreaks(): StyledString = if (this.noLineBreak) this else this.copy(noLineBreak = true)
    override fun appendUnstyled(unstyledString: String) =
        copy(unstyled = unstyled + unstyledString, maybeStyled = maybeStyled.map { it + unstyledString })

}

fun String.noStyle(noLineBreak: Boolean = true): StyledString =
    if (this == "") StyledString.EMPTY_STRING
    else StyledString(this, None, noLineBreak = noLineBreak)

fun StringBuilder.appendSpaces(numberOfSpaces: Int) = repeat(numberOfSpaces) { append(' ') }
fun StyledString.pad(length: Int): String =
    maybeStyled.fold(
        {
            val txt = replaceWrap(unstyled)
            when (align) {
                HorizontalAlignment.LEFT -> txt.padEnd(length)
                HorizontalAlignment.CENTER -> txt.padStart(length / 2).padEnd(length)
                HorizontalAlignment.RIGHT -> txt.padStart(length)
            }
        },
        { styled ->
            // Note, if someone should define ansi colours in the unstyled string -- for instance
            // `Text("\u001b[31mred\u001b[0m")` -- then the length is wrong, i.e. we don't handle/support this for now
            val unstyledLength = unstyled.length
            val styleDiff = styled.length - unstyledLength
            val sb = StringBuilder(length + styleDiff)
            val pad = length - unstyledLength



            when (align) {
                HorizontalAlignment.LEFT -> {
                    // no padding before
                }

                HorizontalAlignment.CENTER -> {
                    // pad half before
                    sb.appendSpaces(pad / 2)
                }

                HorizontalAlignment.RIGHT -> {
                    // pad all before
                    sb.appendSpaces(pad)
                }
            }

            // append text as such
            sb.append(replaceWrap(styled))

            when (align) {
                HorizontalAlignment.LEFT -> {
                    // pad all after
                    sb.appendSpaces(pad)
                }

                HorizontalAlignment.CENTER -> {
                    // pad half after
                    sb.appendSpaces(pad - (pad / 2))
                }

                HorizontalAlignment.RIGHT -> {
                    // no padding after
                }
            }

            sb.toString()
        }
    )

private fun StyledString.replaceWrap(s: String) =
    if (noLineBreak) s.replace('\n', ' ') else s

interface ReportableFilter {
    fun includeInReporting(reportable: Reportable): Boolean
}

object IncludeAllReportableFilter : ReportableFilter {
    override fun includeInReporting(reportable: Reportable): Boolean = true
}

object FailingProofsAndOthers : ReportableFilter {
    override fun includeInReporting(reportable: Reportable): Boolean =
        when (reportable) {
            is AssertionGroup -> {
                reportable.holds().not() || reportable.type is DoNotFilterAssertionGroupType
            }

            is Proof -> reportable.holds().not()
            else -> true
        }
}

interface PreRenderController {
    // TODO 1.3.0 do we ever use the indentNewline?
    fun transformRoot(reportable: Reportable, indentNewLine: Boolean = true): List<OutputNode>

    fun transformChildIncludingIndentationAndPrefix(
        child: Reportable,
        controlObject: PreRenderControlObject
    ): List<OutputNode>

    fun transformChild(child: Reportable, controlObject: PreRenderControlObject): List<OutputNode>

    fun transformGroup(
        reportableGroupWithDescription: ReportableGroupWithDescription,
        controlObject: PreRenderControlObject,
        prefixDescriptionColumns: List<StyledString> = emptyList(),
        suffixDescriptionColumns: List<StyledString> = emptyList(),
        startMergeAtColumn: Int = 0,
        childTransformer: (child: Reportable) -> List<OutputNode>,
    ) = transformGroup(
        reportableGroupWithDescription.description,
        Text.EMPTY,
        controlObject,
        reportableGroupWithDescription.children.flatMap(childTransformer),
        prefixDescriptionColumns,
        suffixDescriptionColumns,
        startMergeAtColumn
    )

    fun <T> transformGroup(
        reportableGroupWithDesignation: T,
        controlObject: PreRenderControlObject,
        prefixDescriptionColumns: List<StyledString> = emptyList(),
        suffixDescriptionColumns: List<StyledString> = emptyList(),
        startMergeAtColumn: Int = 0,
        childTransformer: (child: Reportable) -> List<OutputNode>,
    ) where T : ReportableGroup, T : ReportableWithDesignation = transformGroup(
        reportableGroupWithDesignation.description,
        reportableGroupWithDesignation.representation,
        controlObject,
        reportableGroupWithDesignation.children.flatMap(childTransformer),
        prefixDescriptionColumns,
        suffixDescriptionColumns,
        startMergeAtColumn
    )

    fun transformGroup(
        reportableWithDesignation: ReportableWithDesignation,
        controlObject: PreRenderControlObject,
        prefixDescriptionColumns: List<StyledString> = emptyList(),
        suffixDescriptionColumns: List<StyledString> = emptyList(),
        startMergeAtColumn: Int = 0,
    ) = transformGroup(
        reportableWithDesignation.description,
        reportableWithDesignation.representation,
        controlObject,
        children = emptyList(),
        prefixDescriptionColumns,
        suffixDescriptionColumns,
        startMergeAtColumn
    )

    fun transformGroup(
        description: Reportable,
        representation: Any,
        controlObject: PreRenderControlObject,
        children: List<OutputNode>,
        prefixDescriptionColumns: List<StyledString> = emptyList(),
        suffixDescriptionColumns: List<StyledString> = emptyList(),
        startMergeAtColumn: Int = 0,
    ): List<OutputNode>
}

interface TextPreRenderController : PreRenderController


class DefaultTextPreRenderController(
    //TODO 1.3.0 does it make sense to use Sequence, why not List, looks like we don't do operations with it
    private val preRenderers: Sequence<TextPreRenderer>,
    private val designationPreRenderer: Sequence<TextDesignationPreRenderer>,
    private val iconStyler: TextIconStyler,
    private val reportableFilter: ReportableFilter,
) : TextPreRenderController {

    override fun transformRoot(reportable: Reportable, indentNewLine: Boolean): List<OutputNode> =
        transformChildIncludingIndentationAndPrefix(
            reportable,
            PreRenderControlObject(Icon.EMPTY_STRING, 0, controller = this, reportableFilter)
        )

    override fun transformChildIncludingIndentationAndPrefix(
        child: Reportable,
        controlObject: PreRenderControlObject
    ): List<OutputNode> = transformChild(child, controlObject).map { node ->
        indentAndPrefix(node, controlObject)
    }

    override fun transformChild(child: Reportable, controlObject: PreRenderControlObject): List<OutputNode> =
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
        controlObject: PreRenderControlObject,
        children: List<OutputNode>,
        prefixDescriptionColumns: List<StyledString>,
        suffixDescriptionColumns: List<StyledString>,
        startMergeAtColumn: Int
    ): List<OutputNode> {
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
        controlObject: PreRenderControlObject
    ): OutputNode {
        val columns = node.columns
        //TODO 1.3.0 remove this check if we assure that columns can never be empty
        val newColumns = if (columns.isNotEmpty()) {
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
        } else {
            emptyList()
        }

        val newIndentLevel = node.indentLevel + controlObject.indentLevel
        return node.copy(
            columns = newColumns,
            indentLevel = newIndentLevel,
            //TODO 1.3.0 not happy with this and DefaultFeatureProofGroupTextPreRenderer, does not make sense
            // that DefaultFeatureProofGroupTextPreRenderer defines startMergeAtColumn = 1 because in theory it cannot
            // know how many columns the prefix spans and it should be here were we define it
            startMergeAtColumn = takeIf(node.mergeColumns > 0) { newIndentLevel + node.startMergeAtColumn } ?: 0
        )
    }
}

enum class Icon : InlineElement {
    BULB,
    BANGBANG,
    DEBUG_INFO,
    EMPTY_STRING,
    FAILURE,
    FEATURE,
    FEATURE_BULLET_POINT,
    GROUPING_BULLET_POINT,
    LIST_BULLET_POINT,
    INFORMATION_SOURCE,
    PROOF_EXPLANATION_BULLET_POINT,
    ROOT_BULLET_POINT,
    SUCCESS,
}

interface IconStyler {
    fun style(icon: Icon): StyledString
}

interface TextIconStyler : IconStyler
interface Utf8SupportDeterminer {
    val isSupported: Boolean
}

class MordantBasedUtf8SupportDeterminer(terminal: Terminal) : Utf8SupportDeterminer {
    override val isSupported: Boolean = run {
        // doesn't need to be true at all, we simply assume that if the terminal supports the following AnsiLevel,
        // then utf-8 is supported as well.
        terminal.info.ansiLevel == AnsiLevel.ANSI256 || terminal.info.ansiLevel == AnsiLevel.TRUECOLOR
    }
}

class DefaultTextIconStyler(textStyler: TextStyler, utf8SupportDeterminer: Utf8SupportDeterminer) : TextIconStyler {
    private val icons = run {
        val utf8IsSupported = utf8SupportDeterminer.isSupported

        fun styleIcon(utf8String: String, utf8Space: String, fallbackAsciiString: String, style: Style) =
            textStyler.style(
                if (utf8IsSupported) utf8String else fallbackAsciiString,
                style,
                align = HorizontalAlignment.CENTER
            ).let {
                if (utf8IsSupported) it.appendUnstyled(utf8Space) else it
            }


        mapOf(
            // \uFE0F means animate emoji according to terminal
            //TODO 1.3.0 animated emojis look nicer but they don't have a mono width which makes alignment sometimes
            // a bit ugly - keep or use only the text variation (which also means we can choose the colour as we like)
            Icon.BULB to styleIcon("💡\uFE0F", " ", "(i) ", Style.BULB),
            Icon.BANGBANG to styleIcon("❗❗", " ", "(!) ", Style.FAILURE),
            Icon.DEBUG_INFO to styleIcon("🔎", " ", "(d) ", Style.DEBUG_INFO),
            Icon.EMPTY_STRING to StyledString.EMPTY_STRING,
            Icon.FAILURE to styleIcon("✘", " ", "(f) ", Style.FAILURE),
            Icon.FEATURE to styleIcon("▶", " ", "> ", Style.FEATURE),
            Icon.FEATURE_BULLET_POINT to "- ".noStyle(),
            Icon.GROUPING_BULLET_POINT to "# ".noStyle(),
            Icon.LIST_BULLET_POINT to "⚬ ".noStyle(),
            Icon.INFORMATION_SOURCE to styleIcon("ℹ\uFE0F", " ", "(i) ", Style.INFORMATION_SOURCE),
            Icon.PROOF_EXPLANATION_BULLET_POINT to "» ".noStyle(),
            Icon.ROOT_BULLET_POINT to "◆ ".noStyle(),
            Icon.SUCCESS to styleIcon("✔", " ", "(s)", Style.SUCCESS)
        )
    }

    override fun style(icon: Icon): StyledString =
        icons[icon] ?: throw IllegalArgumentException("unsupported icon: $icon")
}


private fun determineChildControlObject(
    controlObject: PreRenderControlObject,
    child: Reportable,
    childPrefix: Icon,
    additionalIndent: Int = 0
): PreRenderControlObject {
    val indentLevel = controlObject.indentLevel + additionalIndent
    return when (child) {
        //TODO remove with 2.0.0 latest
        is ExplanatoryAssertion -> controlObject.copy(prefix = childPrefix, indentLevel = indentLevel)

        is Proof -> if (child.holds()) {
            controlObject.copy(prefix = Icon.SUCCESS, indentLevel = indentLevel)
        } else {
            controlObject.copy(prefix = Icon.FAILURE, indentLevel = indentLevel)
        }

        else -> controlObject.copy(prefix = childPrefix, indentLevel = indentLevel)
    }
}


//TODO 1.3.0 merge with TextObjectFormatter
interface TextObjFormatter {
    fun format(value: Any?): StyledString
}

class DefaultTextObjFormatter(private val styler: TextStyler) : TextObjFormatter {
    override fun format(value: Any?): StyledString =
        when (value) {
            is TextElement -> value.string.noStyle(noLineBreak = false)
            is String -> formatString(value)
            else -> value.toString().noStyle(noLineBreak = false)
        }

    private fun formatString(value: String): StyledString =
        if (value.contains(Regex("[\n\"]"))) {
            val s = "\"\"\"\n" +
                value + "\n" +
                "\"\"\""
            s.noStyle(noLineBreak = false)
        } else {
            "\"$value\"".noStyle(noLineBreak = false)
        }
}

class DefaultRootProofGroupTextPreRenderer : TypedTextPreRenderer<RootProofGroup>(RootProofGroup::class) {

    override fun transformIt(reportable: RootProofGroup, controlObject: PreRenderControlObject): List<OutputNode> =
        controlObject.transformGroup(reportable, controlObject) { child ->
            val newControlObject = determineChildControlObject(controlObject, child, Icon.ROOT_BULLET_POINT)
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }.let {
            // usually a group has a prefix icon (or an own prefix) in which case, for root we get an empty_string as
            // icon but we don't want to show it as column, instead this first column shall be merged with the columns
            // needed for the description
            val first = it.first()
            (sequenceOf(first.copy(mergeColumns = first.mergeColumns + 1)) + it.asSequence().drop(1)).toList()
        }
}

class DefaultFeatureProofGroupTextPreRenderer(
    private val iconStyler: TextIconStyler
) : TypedTextPreRenderer<FeatureProofGroup>(FeatureProofGroup::class) {

    override fun transformIt(
        reportable: FeatureProofGroup,
        controlObject: PreRenderControlObject
    ): List<OutputNode> =
        controlObject.transformGroup(
            reportable,
            controlObject,
            // we use an empty string as additional colum because we want to indent the children by 2 and still
            // span the description over the indent + prefix
            prefixDescriptionColumns = listOf(iconStyler.style(Icon.FEATURE), StyledString.EMPTY_STRING),
            suffixDescriptionColumns = listOf(),
            startMergeAtColumn = 1 // because we have a prefix from the parent group
        ) { child ->
            val newControlObject = determineChildControlObject(
                controlObject,
                child,
                Icon.FEATURE_BULLET_POINT,
                // indent by two because we want that the children are after Icon.FEATURE
                // (1 additional indent for the x prefix of the feature group
                additionalIndent = 2
            )
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }
}

class DefaultInvisibleProofGroupTextPreRenderer :
    TypedTextPreRenderer<InvisibleProofGroup>(InvisibleProofGroup::class) {
    override fun transformIt(
        reportable: InvisibleProofGroup,
        controlObject: PreRenderControlObject
    ): List<OutputNode> =
        reportable.children.flatMap { child ->
            controlObject.transformChild(child, controlObject)
        }
}


class DefaultDebugGroupTextPreRenderer(
    private val iconStyler: TextIconStyler
) : TypedTextPreRenderer<DebugGroup>(DebugGroup::class) {
    override fun transformIt(
        reportable: DebugGroup,
        controlObject: PreRenderControlObject
    ): List<OutputNode> = controlObject.transformGroup(
        reportable,
        controlObject,
        prefixDescriptionColumns = listOf(iconStyler.style(Icon.DEBUG_INFO))
    ) { child ->
        val newControlObject =
            determineChildControlObject(controlObject, child, Icon.LIST_BULLET_POINT, additionalIndent = 1)
        controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
    }.let {
        listOf(it.first().copy(usesOwnPrefix = true)) + it.drop(1)
    }
}

abstract class PrefixChangingReportableGroupWithoutColumns<R : ReportableGroup>(
    kClass: KClass<R>,
    private val icon: Icon
) : TypedTextPreRenderer<R>(kClass) {
    override fun transformIt(
        reportable: R,
        controlObject: PreRenderControlObject
    ): List<OutputNode> = reportable.children.flatMap { child ->
        controlObject.transformChild(child, controlObject.copy(prefix = icon))
    }
}

class DefaultUsageHintGroupTextPreRenderer : PrefixChangingReportableGroupWithoutColumns<UsageHintGroup>(
    UsageHintGroup::class, Icon.BULB
)


class DefaultFallbackProofGroupWithDesignationTextPreRenderer :
    TypedTextPreRenderer<ProofGroupWithDesignation>(ProofGroupWithDesignation::class) {
    override fun transformIt(
        reportable: ProofGroupWithDesignation,
        controlObject: PreRenderControlObject
    ): List<OutputNode> =
        controlObject.transformGroup(reportable, controlObject) { child ->
            val newControlObject =
                determineChildControlObject(controlObject, child, Icon.LIST_BULLET_POINT, additionalIndent = 1)
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }
}

class DefaultFallbackReportableGroupWithDesignationTextPreRenderer :
    TypedTextPreRenderer<ProofGroupWithDesignation>(ProofGroupWithDesignation::class) {
    override fun transformIt(
        reportable: ProofGroupWithDesignation,
        controlObject: PreRenderControlObject
    ): List<OutputNode> {
        val newControlObject = controlObject.copy(prefix = Icon.LIST_BULLET_POINT)
        return controlObject.transformGroup(reportable, controlObject) { child ->
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }
    }
}

@Suppress("DEPRECATION")
@Deprecated("Switch from Assertion to Proof, was introduced to ease the migration to Proof, will be removed with 2.0.0 at the latest")
class DefaultAssertionGroupTextPreRenderer(private val iconStyler: TextIconStyler) : TextPreRenderer {
    override fun canTransform(reportable: Reportable): Boolean = reportable is AssertionGroup

    override fun transform(reportable: Reportable, controlObject: PreRenderControlObject): List<OutputNode> =
        (reportable as AssertionGroup).let { assertionGroup ->
            when (assertionGroup.type) {
                is RootAssertionGroupType -> controlObject.transformGroup(reportable, controlObject) { child ->
                    val newControlObject = determineChildControlObject(controlObject, child, Icon.ROOT_BULLET_POINT)
                    controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
                }.let {
                    // usually a group has a prefix icon (or an own prefix) in which case, for root we get an empty_string as
                    // icon but we don't want to show it as column, instead this first column shall be merged with the columns
                    // needed for the description
                    val first = it.first()
                    (sequenceOf(first.copy(mergeColumns = first.mergeColumns + 1)) + it.asSequence().drop(1)).toList()
                }

                is InvisibleAssertionGroupType -> assertionGroup.children.flatMap { child ->
                    controlObject.transformChild(child, controlObject)
                }

                is FeatureAssertionGroupType -> controlObject.transformGroup(
                    reportable,
                    controlObject,
                    // we use an empty string as additional colum because we want to indent the children by 2 and still
                    // span the description over the indent + prefix
                    prefixDescriptionColumns = listOf(iconStyler.style(Icon.FEATURE), StyledString.EMPTY_STRING),
                    suffixDescriptionColumns = listOf(),
                    startMergeAtColumn = 1 // because we have a prefix from the parent group
                ) { child ->
                    val newControlObject = determineChildControlObject(
                        controlObject,
                        child,
                        Icon.FEATURE_BULLET_POINT,
                        // indent by two because we want that the children are after Icon.FEATURE
                        // (1 additional indent for the x prefix of the feature group
                        additionalIndent = 2
                    )
                    controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
                }

                is ListAssertionGroupType -> controlObject.transformGroup(reportable, controlObject) { child ->
                    val newControlObject = determineChildControlObject(
                        controlObject,
                        child,
                        Icon.LIST_BULLET_POINT,
                        additionalIndent = 1
                    )
                    controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
                }

                is GroupingAssertionGroupType -> controlObject.transformGroup(reportable, controlObject) { child ->
                    val newControlObject = determineChildControlObject(
                        controlObject,
                        child,
                        Icon.GROUPING_BULLET_POINT,
                        // indent by two because we want that the children are after Icon.FEATURE
                        // (1 additional indent for the x prefix of the feature group
                    )
                    controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
                }

                else -> throw UnsupportedOperationException("Unsupported assertionGroupType ${assertionGroup.type}")
            }
        }
}


class DefaultSimpleProofTextPreRenderer :
    TypedTextPreRenderer<SimpleProof>(SimpleProof::class) {
    override fun transformIt(reportable: SimpleProof, controlObject: PreRenderControlObject): List<OutputNode> {
        // we kind of misuse transformGroup to re-use the logic for TextDesignationPreRenderer
        // but we need to set definesOwnLevel to false as a SimpleProof does not define an own level
        return listOf(
            controlObject.transformGroup(reportable, controlObject).single().copy(definesOwnLevel = false)
        )
    }
}


class DefaultTextElementTextPreRenderer : TypedTextPreRenderer<TextElement>(TextElement::class) {
    override fun transformIt(reportable: TextElement, controlObject: PreRenderControlObject): List<OutputNode> =
        listOf(
            OutputNode(
                columns = listOf(reportable.string.noStyle(noLineBreak = false)),
                emptyList(),
                definesOwnLevel = true
            )
        )
}


@Suppress("DEPRECATION")
@Deprecated("Switch from Assertion to Proof, was introduced to ease the migration to Proof, will be removed with 2.0.0 at the latest")
class DefaultDescriptiveAssertionTextPreRenderer :
    TypedTextPreRenderer<DescriptiveAssertion>(DescriptiveAssertion::class) {
    override fun transformIt(
        reportable: DescriptiveAssertion,
        controlObject: PreRenderControlObject
    ): List<OutputNode> {
        // we kind of misuse transformGroup to re-use the logic for TextDesignationPreRenderer
        // but we need to set definesOwnLevel to false as a SimpleProof does not define an own level
        return listOf(
            controlObject.transformGroup(reportable, controlObject).single().copy(definesOwnLevel = false)
        )
    }
}

@Suppress("DEPRECATION")
@Deprecated("Switch from Assertion to Proof, was introduced to ease the migration to Proof, will be removed with 2.0.0 at the latest")
class DefaultRepresentationOnlyAssertionTextPreRenderer(private val objFormatter: TextObjFormatter) :
    TypedTextPreRenderer<RepresentationOnlyAssertion>(RepresentationOnlyAssertion::class) {
    override fun transformIt(
        reportable: RepresentationOnlyAssertion,
        controlObject: PreRenderControlObject
    ): List<OutputNode> = listOf(
        OutputNode(
            arrayOf(objFormatter.format(reportable.representation)).asList(),
            children = emptyList(),
            definesOwnLevel = true
        )
    )
}

@Suppress("DEPRECATION")
@Deprecated("Switch from Assertion to Proof, was introduced to ease the migration to Proof, will be removed with 2.0.0 at the latest")
class DefaultExplanatoryAssertionTextPreRenderer(private val objFormatter: TextObjFormatter) :
    TypedTextPreRenderer<ExplanatoryAssertion>(ExplanatoryAssertion::class) {
    override fun transformIt(
        reportable: ExplanatoryAssertion,
        controlObject: PreRenderControlObject
    ): List<OutputNode> = listOf(
        OutputNode(
            arrayOf(objFormatter.format(reportable.explanation)).asList(),
            children = emptyList(),
            definesOwnLevel = true
        )
    )
}

@Suppress("DEPRECATION")
@Deprecated("Switch from Assertion to Proof, was introduced to ease the migration to Proof, will be removed with 2.0.0 at the latest")
class DefaultExplanatoryAssertionGroupTextPreRenderer : TextPreRenderer {
    override fun canTransform(reportable: Reportable): Boolean = reportable is ExplanatoryAssertionGroup

    override fun transform(
        reportable: Reportable,
        controlObject: PreRenderControlObject
    ): List<OutputNode> = (reportable as ExplanatoryAssertionGroup).let { assertion ->
        val (icon, additionalIndent) = when (assertion.type) {
            is DefaultExplanatoryAssertionGroupType -> Icon.PROOF_EXPLANATION_BULLET_POINT to 1
            is WarningAssertionGroupType -> Icon.BANGBANG to 1
            is InformationAssertionGroupType -> Icon.INFORMATION_SOURCE to if (assertion.type.withIndent) 1 else 0
            is HintAssertionGroupType -> Icon.DEBUG_INFO to 1
            else -> throw UnsupportedOperationException("Unsupported assertionGroupType ${assertion.type}")
        }

        listOf(
            OutputNode(
                columns = emptyList(),
                children = assertion.assertions.asSequence().flatMap { child ->
                    val newControlObject = controlObject.copy(
                        prefix = icon,
                        indentLevel = controlObject.indentLevel + additionalIndent,
                        reportableFilter = object : ReportableFilter {
                            override fun includeInReporting(reportable: Reportable): Boolean = true
                        },
                    )
                    controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
                }.toList(),
                definesOwnLevel = true,
                usesOwnPrefix = true
            )
        )
    }
}


//class DefaultIconPreRenderer(private val iconStyler: TextIconStyler) : TypedTextPreRenderer<Icon>(Icon::class) {
//    override fun transformIt(reportable: Icon, controlObject: PreRenderControlObject): List<OutputNode> =
//        OutputNode.singleInline(iconStyler.style(reportable))
//}

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
        controlObject: PreRenderControlObject,
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
                    add(" : ".noStyle())
                    add(objectFormatter.format(representation))
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
        controlObject: PreRenderControlObject
    ): List<StyledString> {
        val nodes = controlObject.transformChild(description, controlObject)
        val node = takeIf(nodes.size == 1) {
            nodes[0]
        } ?: errorDueToBug(
            "transformChild for InlineElement $description returned ${nodes.size} nodes, expected 1"
        )

        failWithBugErrorIf(node.columns.isEmpty()) {
            "transformChild for InlineElement $description returned 0 columns"
        }

        // we are going to merge the columns, hence ensure noLineBreak=true
        return node.columns.map { it.withoutLineBreaks() }
    }
}
