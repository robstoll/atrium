package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.core.polyfills.appendln
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.proofs.FeatureProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.RootProofGroup
import ch.tutteli.atrium.creating.proofs.SimpleProof
import ch.tutteli.atrium.reporting.reportables.*
import ch.tutteli.kbox.forEachRemaining
import ch.tutteli.kbox.takeIf
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
    fun format(@Suppress("DEPRECATION") assertion: ch.tutteli.atrium.assertions.Assertion, sb: StringBuilder)

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

class AssertionToProofConvertingPreRenderController(
    private val preRenderController: TextPreRenderController,
    private val reportableFilter: ReportableFilter
) : TextPreRenderController {
    override fun transformRoot(reportable: Reportable, indentNewLine: Boolean): List<OutputNode> =
        transformChildIncludingIndentationAndPrefix(
            convertReportableIfNecessary(reportable),
            PreRenderControlObject(Icon.EMPTY_STRING, 0, controller = this, reportableFilter)
        )

    override fun transformChildIncludingIndentationAndPrefix(
        child: Reportable,
        controlObject: PreRenderControlObject
    ): List<OutputNode> =
        preRenderController.transformChildIncludingIndentationAndPrefix(
            convertReportableIfNecessary(child),
            controlObject
        )

    override fun transformChild(child: Reportable, controlObject: PreRenderControlObject): List<OutputNode> =
        preRenderController.transformChild(convertReportableIfNecessary(child), controlObject)

    override fun transformGroup(
        description: Reportable,
        representation: Any,
        controlObject: PreRenderControlObject,
        children: List<OutputNode>,
        prefixColumns: List<StyledString>
    ): List<OutputNode> = preRenderController.transformGroup(
        convertReportableIfNecessary(description),
        representation,
        controlObject,
        children,
        prefixColumns
    )


    private fun convertReportableIfNecessary(reportable: Reportable): Reportable = when (reportable) {
        is @kotlin.Suppress("DEPRECATION") ch.tutteli.atrium.assertions.Assertion -> convertAssertionToProof(reportable)
        else -> reportable
    }


    @Suppress("DEPRECATION")
    private fun convertAssertionToProof(assertion: ch.tutteli.atrium.assertions.Assertion): Reportable =
        when (assertion) {
            is ch.tutteli.atrium.assertions.BasicDescriptiveAssertion -> Proof.simple(
                assertion.description,
                assertion.representation,
                assertion.test
            )

            else -> {
                // who knows maybe there is a pre-renderer for this type of Assertion
                assertion
            }
        }
}

interface TextReporter : Reporter {
    @Deprecated(
        "use createReport, will be removed with 2.0.0 at the latest",
        ReplaceWith("sb.append(this.createReport(assertion))"),
        level = DeprecationLevel.ERROR
    )
    override fun format(@Suppress("DEPRECATION") assertion: ch.tutteli.atrium.assertions.Assertion, sb: StringBuilder) =
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
            } should always result in one ${OutputNode::class.simpleName}"
        )
        val rootNode = rootNodes[0]
        val sb = StringBuilder()
        format(rootNode, emptyList(), sb)
        return sb
    }

    private fun format(outputNode: OutputNode, indentLevels: List<Int>, sb: StringBuilder) {
        val maxLengths = calculateMaxLengths(outputNode)
        fun appendColumns(node: OutputNode) {
            val columns = node.columns
            val size = columns.size
            if (size > 0) {
                val indentLevel = node.indentLevel
                val span = node.span

                (0 until indentLevel).forEach { i ->
                    val s = columns[i]
                    sb.append(s.padEnd(indentLevels[i]))
                }
                val startIndex = if (span > 0) {
                    var additionalPadding = 0
                    var index = indentLevel
                    repeat(span) {
                        val s = columns[index]
                        sb.append(s.result())
                        additionalPadding += maxLengths[index] - s.unstyled.length
                        ++index
                    }
                    val s = columns[index]
                    sb.append(s.padEnd(additionalPadding + maxLengths[index] + indentLevels.drop(indentLevel).sum()))
                    ++index
                } else {
                    indentLevel
                }
                (startIndex until size - 1).forEach { i ->
                    val s = columns[i]
                    sb.append(s.padEnd(maxLengths[i]))
                }
                sb.append(columns[size - 1].result())
            }
        }
        appendColumns(outputNode)
        outputNode.children.forEach { child ->
            sb.appendln()
            if (child.definesOwnLevel) {
                format(child, indentLevels + maxLengths.drop(indentLevels.size).first(), sb)
            } else {
                appendColumns(child)
            }
        }
    }

    private fun calculateMaxLengths(node: OutputNode): List<Int> {
        val maxLengths = mutableListOf<Int>()
        fun updateMaxLengths(child: OutputNode) {
            //TODO take span into account. Is not 100% correct yet.
            // e.g. `expected that subject` = 20, first column is empty
            // there is no need to pad `expected that subject` with 22 in this particular case because 20 is the longest
            // and no child is longer than 20 combining column 1 + 2
            val currentSize = maxLengths.size
            child.columns.forEachIndexed { index, s ->
                val length = s.unstyled.length
                if (index < currentSize) {
                    if (maxLengths[index] < length) {
                        maxLengths[index] = length
                    }
                } else {
                    maxLengths.add(index, length)
                }
            }
        }
        updateMaxLengths(node)
        node.children
            .asSequence()
            .filterNot { it.definesOwnLevel }
            .forEach(::updateMaxLengths)
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
        prefixColumns: List<StyledString>,
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

abstract class TypedDesignatorPreRenderer<R : Reportable>(private val kClass: KClass<R>) : TextDesignationPreRenderer {
    final override fun canTransform(description: Reportable): Boolean = kClass.isInstance(description)
    override fun transform(
        description: Reportable,
        representation: Any,
        prefixColumns: List<StyledString>,
        children: List<OutputNode>,
        controlObject: PreRenderControlObject,
    ): List<OutputNode> = transformIt(kClass.cast(description), representation, prefixColumns, children, controlObject)

    protected abstract fun transformIt(
        description: R,
        representation: Any,
        prefixColumns: List<StyledString>,
        children: List<OutputNode>,
        controlObject: PreRenderControlObject,
    ): List<OutputNode>
}


data class OutputNode(
    val columns: List<StyledString>,
    val children: List<OutputNode>,
    val definesOwnLevel: Boolean,
    val indentLevel: Int = 0,
    val span: Int = 0,
    val usesOwnPrefix: Boolean = false
) {
    init {
        require(columns.isNotEmpty() || children.isNotEmpty()) { "at least one of columns/children needs to have elements" }
        require(span < columns.size) { "cannot span more than available columns. span: $span, columns size: ${columns.size}" }
    }

    companion object {
        fun singleInline(vararg columns: StyledString) = singleInline(columns.asList())
        fun singleInline(columns: List<StyledString>) = listOf(inline(columns))
        fun inline(vararg columns: StyledString) = inline(columns.toList())
        fun inline(columns: List<StyledString>) = OutputNode(columns, children = emptyList(), definesOwnLevel = false)
    }
}


interface ThemeProvider

interface TextThemeProvider : ThemeProvider {

    fun findColorCode(styleId: String): String?

    @Deprecated(
        "use findColorCode instead to give the return value more meaning",
        ReplaceWith("findColorCode(styleId)")
    )
    fun findStyle(styleId: String): String? = findColorCode(styleId)
}

abstract class MapBasedTextThemeProvider : TextThemeProvider {
    protected abstract val styleIdToColorCode: Map<String, String>

    override fun findColorCode(styleId: String): String? = styleIdToColorCode[styleId]
}

class DefaultAnsi8ColoursThemeProvider : MapBasedTextThemeProvider() {
    override val styleIdToColorCode = mapOf(
        Style.SUCCESS.styleId to "\u001b[32m",
        Style.FAILURE.styleId to "\u001b[31m",
        Style.INFORMATION_SOURCE.styleId to "\u001b[1m\u001b[94m",
        Style.BULB.styleId to "\u001B[1m\u001b[33;1m"
    )
}

enum class Style(val styleId: String) {
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE"),
    INFORMATION_SOURCE("INFORMATION_SOURCE"),
    BULB("BULB")
}

interface TextStyler {
    fun supportsAnsi(): Boolean
    fun style(s: String, maybeStyle: Option<Style>, noWrap: Boolean = true): StyledString =
        maybeStyle.fold({ s.noStyle(noWrap) }) { style(s, it, noWrap) }

    fun style(s: String, style: Style, noWrap: Boolean = true): StyledString
    fun style(s: String, styleId: String, noWrap: Boolean = true): StyledString
}

class DefaultTextStyler(
    private val textThemeProvider: TextThemeProvider
) : TextStyler {

    private val couldSupportAnsi by lazy {
        true // System.console() != null
    }

    override fun supportsAnsi(): Boolean = couldSupportAnsi

    override fun style(s: String, style: Style, noWrap: Boolean): StyledString =
        style(s, style.styleId, noWrap)

    override fun style(s: String, styleId: String, noWrap: Boolean): StyledString {
        val maybeStyled = takeIf(couldSupportAnsi) {
            textThemeProvider.findStyle(styleId)?.let { colorCode -> Some(colorCode + s + RESET) }
        } ?: None
        return StyledString(s, noWrap = noWrap, maybeStyled = maybeStyled)
    }


    companion object {
        const val RESET = "\u001B[0m"
    }
}


enum class HorizontalAlignment {
    LEFT,
    CENTER,
    RIGHT,
}

data class StyledString(
    val unstyled: String,
    val maybeStyled: Option<String>,
    val span: Int = 0,
    val noWrap: Boolean = true,
    //TODO take this one into account as well
    val align: HorizontalAlignment = HorizontalAlignment.LEFT
) {

    override fun toString(): String = "SS(u=$unstyled)"

    fun result(): String {
        val s = maybeStyled.getOrElse { unstyled }
        return replaceWrap(s)
    }

    private fun replaceWrap(s: String) =
        if (noWrap) s.replace('\n', ' ') else s

    operator fun plus(s: StyledString) = StyledString(
        unstyled + s.unstyled,
        if (maybeStyled.isDefined() || s.maybeStyled.isDefined()) {
            Some(result() + s.result())
        } else {
            None
        }
    )

    fun padEnd(length: Int): String =
        maybeStyled.fold({ replaceWrap(unstyled).padEnd(length) }) { styled ->
            val unstyledLength = unstyled.length
            val styleDiff = styled.length - unstyledLength

            val sb = StringBuilder(length + styleDiff)
            sb.append(replaceWrap(styled))
            repeat((length - unstyledLength)) { sb.append(' ') }
            sb.toString()
        }

    companion object {
        val EMPTY_STRING = "".noStyle()
    }
}

fun String.noStyle(noWrap: Boolean = true): StyledString = StyledString(this, None, noWrap = noWrap)


interface ReportableFilter {
    fun includeInReporting(reportable: Reportable): Boolean
}

object FailingProofsAndOthers : ReportableFilter {
    override fun includeInReporting(reportable: Reportable): Boolean =
        when (reportable) {
            is Proof -> reportable.holds().not()
            else -> true
        }
}

interface PreRenderController {
    fun transformRoot(reportable: Reportable, indentNewLine: Boolean = true): List<OutputNode>

    fun transformChildIncludingIndentationAndPrefix(
        child: Reportable,
        controlObject: PreRenderControlObject
    ): List<OutputNode>

    fun transformChild(child: Reportable, controlObject: PreRenderControlObject): List<OutputNode>

    fun <T> transformGroup(
        reportableGroupWithDesignation: T,
        controlObject: PreRenderControlObject,
        prefixColumns: List<StyledString> = emptyList(),
        childTransformer: (child: Reportable) -> List<OutputNode>
    ) where T : ReportableGroup, T : ReportableWithDesignation = transformGroup(
        reportableGroupWithDesignation.description,
        reportableGroupWithDesignation.representation,
        controlObject,
        reportableGroupWithDesignation.children.flatMap(childTransformer),
        prefixColumns
    )

    fun transformGroup(
        reportableWithDesignation: ReportableWithDesignation,
        controlObject: PreRenderControlObject,
        prefixColumns: List<StyledString> = emptyList()
    ) = transformGroup(
        reportableWithDesignation.description,
        reportableWithDesignation.representation,
        controlObject,
        children = emptyList(),
        prefixColumns
    )

    fun transformGroup(
        description: Reportable,
        representation: Any,
        controlObject: PreRenderControlObject,
        children: List<OutputNode>,
        prefixColumns: List<StyledString> = emptyList()
    ): List<OutputNode>
}

interface TextPreRenderController : PreRenderController
//
//interface InlineElementPreRenderController{
//    fun transformRoot(reportable: Reportable, controlObject: PreRenderControlObject): List<>
//}
//interface TextInlineElementPreRenderController: InlineElementPreRenderController


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
        prefixColumns: List<StyledString>
    ): List<OutputNode> {
        val preRenderer = designationPreRenderer
            .firstOrNull { it.canTransform(description) }
            ?: throw UnsupportedOperationException("no suitable ${TextDesignationPreRenderer::class.simpleName} found for the given description: $description")
        return preRenderer.transform(description, representation, prefixColumns, children, controlObject)
    }

    private fun indentAndPrefix(
        node: OutputNode,
        controlObject: PreRenderControlObject
    ): OutputNode {
        var list = ArrayList<StyledString>(controlObject.indentLevel + 1 + node.columns.size)
        (0 until controlObject.indentLevel).forEach { i ->
            list.add(i, StyledString.EMPTY_STRING)
        }
        // start inserting nodes after indent
        var index = controlObject.indentLevel
        val iterator = node.columns.iterator()
        fun addToList(styledString: StyledString) = list.add(index++, styledString)

        if (node.usesOwnPrefix.not()) {
            addToList(iconStyler.style(controlObject.prefix))
        }

        var maybeNodes: Option<ArrayList<OutputNode>> = None

        iterator.forEachRemaining {
            if (it.noWrap) {
                addToList(it)
            } else {
                val lines = it.result().split('\n')
                if (lines.size == 1) {
                    addToList(it)
                } else {
                    val nodes = ArrayList<OutputNode>(lines.size);
                    lines.forEach { line ->
                        addToList(line.noStyle())
                        nodes.add(
                            node.copy(
                                columns = list,
                                indentLevel = node.indentLevel + controlObject.indentLevel,
                                children = emptyList(),
                                definesOwnLevel = node.definesOwnLevel && nodes.isEmpty() && !maybeNodes.isDefined()
                            )
                        )
                        // prepare for the next line, indent until we reach the same column
                        --index
                        val tmpList = ArrayList<StyledString>(controlObject.indentLevel + 1 + node.columns.size)
                        repeat(list.size - 1) {
                            tmpList.add(StyledString.EMPTY_STRING)
                        }
                        list = tmpList
                    }
                    maybeNodes = maybeNodes.fold({ Some(nodes) }, { existingNodes ->
                        //side effect, addToExisting
                        existingNodes.addAll(nodes)
                        Some(existingNodes)
                    })
                }
            }
        }

        return maybeNodes.fold({
            node.copy(columns = list, indentLevel = node.indentLevel + controlObject.indentLevel)
        }, { nodes ->
            nodes.first().copy(children = nodes.drop(1) + node.children)
        })
    }
}


//interface InlineElementPreRenderController {
//    fun transformRoot(reportable: Reportable): OutputNode
//}
//
//interface TextInlineElementPreRenderController : InlineElementPreRenderController
//
//class DefaultTextInlineElementPreRenderController(
//    private val preRenderers: Sequence<TextInlineElementPreRenderer>,
//) : TextInlineElementPreRenderController{
//    override fun transformRoot(reportable: Reportable): OutputNode {
//        TODO("Not yet implemented")
//    }
//
//}

enum class Icon : InlineElement {
    BULB,
    BANGBANG,
    DEBUG_INFO,

    //TODO 1.3.0 looks fishy to me, why do we need such an Icon?
    EMPTY_STRING,
    FAILURE,
    FEATURE,
    FEATURE_BULLET_POINT,
    INFORMATION_SOURCE,
    ROOT_BULLET_POINT,
    SUCCESS
}

interface IconStyler {
    fun style(icon: Icon): StyledString
}

interface TextIconStyler : IconStyler

class DefaultTextIconStyler(textStyler: TextStyler) : TextIconStyler {
    private val icons = mapOf(
        Icon.BULB to textStyler.style("💡 ", Style.BULB),
        Icon.BANGBANG to textStyler.style("❗❗ ", Style.FAILURE),
        Icon.DEBUG_INFO to "🔎 ".noStyle(),
        Icon.EMPTY_STRING to StyledString.EMPTY_STRING,
        Icon.FAILURE to textStyler.style("✘ ", Style.FAILURE),
        Icon.FEATURE to "▶ ".noStyle(),
        Icon.FEATURE_BULLET_POINT to "- ".noStyle(),
        Icon.INFORMATION_SOURCE to textStyler.style("ℹ️", Style.INFORMATION_SOURCE),
        Icon.ROOT_BULLET_POINT to "◆ ".noStyle(),
        Icon.SUCCESS to textStyler.style("✔ ", Style.SUCCESS)
    )

    override fun style(icon: Icon): StyledString =
        icons[icon] ?: throw IllegalArgumentException("unsupported icon: $icon")
}


private fun determineChildControlObject(
    controlObject: PreRenderControlObject,
    child: Reportable,
    childPrefix: Icon,
    shallIndentChildren: Boolean
): PreRenderControlObject {
    val indentLevel = if (shallIndentChildren) controlObject.indentLevel + 1 else controlObject.indentLevel
    return when (child) {
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
            is Text -> value.string.noStyle(noWrap = false)
            is String -> "\"$value\"".noStyle(noWrap = false)
            else -> value.toString().noStyle(noWrap = false)
        }
}

class DefaultRootProofGroupTextPreRenderer : TypedTextPreRenderer<RootProofGroup>(RootProofGroup::class) {

    override fun transformIt(reportable: RootProofGroup, controlObject: PreRenderControlObject): List<OutputNode> =
        controlObject.transformGroup(reportable, controlObject) { child ->
            val newControlObject = determineChildControlObject(
                controlObject, child, Icon.ROOT_BULLET_POINT, shallIndentChildren = false
            )
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
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
            prefixColumns = listOf(iconStyler.style(Icon.FEATURE)),
        ) { child ->
            val newControlObject = determineChildControlObject(
                controlObject, child, Icon.FEATURE_BULLET_POINT, shallIndentChildren = true
            )
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }
}

//class DefaultParagraphPreRenderer : TypedPreRenderer<Paragraph>(Paragraph::class) {
//
//    override fun transformIt(reportable: Paragraph, controlObject: PreRenderControlObject): List<OutputNode> {
//        val tmpColumns = reportable.children
//            .flatMap { controlObject.controller.transformRoot(it) }
//            .flatMap {
//                it.columns
//            }
//        val paragraphColumns = if (reportable.withIndent) {
//            val list = ArrayList<StyledString>(1 + tmpColumns.size)
//            list.add(StyledString.EMPTY_STRING)
//            list.addAll(tmpColumns)
//            list
//        } else {
//            tmpColumns
//        }
//        return listOf(
//            OutputNode(
//                paragraphColumns,
//                children = emptyList(),
//                definesOwnLevel = true,
//                useEmptyPrefix = true,
//                indentLevel = if (reportable.withIndent) 1 else 0
//            )
//        )
//    }
//}

class DefaultTextElementTextPreRenderer : TypedTextPreRenderer<TextElement>(TextElement::class) {
    override fun transformIt(reportable: TextElement, controlObject: PreRenderControlObject): List<OutputNode> =
        listOf(
            OutputNode(
                columns = listOf(reportable.string.noStyle(noWrap = false)),
                emptyList(),
                definesOwnLevel = false
            )
        )
}

class DefaultSimpleProofTextPreRenderer :
    TypedTextPreRenderer<SimpleProof>(SimpleProof::class) {
    override fun transformIt(reportable: SimpleProof, controlObject: PreRenderControlObject): List<OutputNode> =
        // we kind of misuse transformGroup to re-use the logic for TextDesignationPreRenderer
        // but we need to set definesOwnLevel to true
        listOf(controlObject.transformGroup(reportable, controlObject).single().copy(definesOwnLevel = false))
}

class DefaultIconPreRenderer(private val iconStyler: TextIconStyler) : TypedTextPreRenderer<Icon>(Icon::class) {
    override fun transformIt(reportable: Icon, controlObject: PreRenderControlObject): List<OutputNode> =
        OutputNode.singleInline(iconStyler.style(reportable))
}

class DefaultInlineDesignatorPreRenderer(
    private val objectFormatter: TextObjFormatter
) : TypedDesignatorPreRenderer<InlineElement>(InlineElement::class) {

    override fun transformIt(
        description: InlineElement,
        representation: Any,
        prefixColumns: List<StyledString>,
        children: List<OutputNode>,
        controlObject: PreRenderControlObject,
    ): List<OutputNode> {
        val descriptionColumns = getDescriptionColumns(description, controlObject)

        // one column is normal and does not require span, hence -1
        val span = (prefixColumns.size + descriptionColumns.size) - 1

        return listOf(
            OutputNode(
                // 1 the description + span if it spans over multiple columns + 2 as we add : and the representation
                columns = ArrayList<StyledString>(1 + span + 2).apply {
                    addAll(prefixColumns)
                    addAll(descriptionColumns)
                    add(" : ".noStyle())
                    add(objectFormatter.format(representation))
                },
                children = children,
                // a group defines an own level
                definesOwnLevel = true,
                span = span
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
            "transformChildIncludingIndentationAndPrefix for InlineElement $description returned ${nodes.size} nodes, expected 1"
        )

        failWithBugErrorIf(node.columns.isEmpty()) {
            "transformChildIncludingIndentationAndPrefix for InlineElement $description returned 0 columns"
        }

        return node.columns
    }
}
