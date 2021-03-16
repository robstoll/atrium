@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.text.impl.Icon.*
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Translator
import kotlin.reflect.KClass


interface ReportableFilter {
    fun includeInReporting(reportable: Reportable): Boolean
}


/**
 * Represents a reporter which reports about [Reportable]s.
 */
interface NewReporter {

    /**
     * Reports about the given [reportable] by appending the result to the given [sb] where the actual
     * implementation will decide whether and how the given [reportable] is reported.
     *
     * @param sb The [StringBuilder] which can be used for reporting.
     * @param reportable The [Reportable] which should be considered for reporting.
     */
    fun createReport(root: Root): StringBuilder
}

interface TextReporter

/**
 * A [Reporter] which reports only failing assertions.
 */
class DefaultTextReporter(
    private val preRenderController: PreRenderController
) : NewReporter {

    override fun createReport(root: Root): StringBuilder {
        val rootNodes = preRenderController.transform(root)
        if (rootNodes.size != 1) throw IllegalStateException("${Root::class.simpleName} transformation should always result in one ${OutputNode::class.simpleName}")
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
                sb.appendln()
            }
        }
        appendColumns(outputNode)

        outputNode.children.forEach { child ->
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

interface TextObjFormatter {
    fun format(value: Any?): StyledString
}

class DefaultTextObjFormatter(private val styler: Styler) : TextObjFormatter {
    override fun format(value: Any?): StyledString =
        when (value) {
            is Text2 -> styler.style(value.txt, value.maybeStyle, noWrap = false)
            is String -> "\"$value\"".noStyle(noWrap = false)
            else -> value.toString().noStyle(noWrap = false)
        }
}

data class PreRenderControlObject(
    val prefix: Icon,
    val indentLevel: Int,
    val controller: PreRenderController
)

interface PreRenderController {
    fun transform(reportable: Reportable, indentNewLine: Boolean = true): List<OutputNode>
    fun transformChildren(children: List<Reportable>, controlObject: PreRenderControlObject): List<OutputNode>
    fun transformChild(child: Reportable, controlObject: PreRenderControlObject): List<OutputNode>
}

class DefaultPreRenderController(
    private val reportableFilters: ReportableFilter,
    private val preRenderers: Sequence<PreRenderer>,
    private val iconStyler: IconStyler
) : PreRenderController {

    override fun transform(reportable: Reportable, indentNewLine: Boolean): List<OutputNode> =
        transformChildren(listOf(reportable), PreRenderControlObject(EMPTY_STRING, 0, this))

    override fun transformChildren(
        children: List<Reportable>,
        controlObject: PreRenderControlObject
    ): List<OutputNode> = children.flatMap { transformChild(it, controlObject) }

    override fun transformChild(child: Reportable, controlObject: PreRenderControlObject): List<OutputNode> =
        if (reportableFilters.includeInReporting(child)) {
            val preRenderer = preRenderers
                .firstOrNull { it.canTransform(child) }
                ?: throw UnsupportedOperationException("no suitable ${PreRenderer::class.simpleName} found for the given reportable: $child")
            preRenderer.transform(child, controlObject).map { node ->
                indentAndPrefix(node, controlObject)
            }
        } else {
            emptyList()
        }

    private fun indentAndPrefix(
        node: OutputNode,
        controlObject: PreRenderControlObject
    ): OutputNode {
        var list = ArrayList<StyledString>(controlObject.indentLevel + 1 + node.columns.size)
        (0 until controlObject.indentLevel).forEach { i ->
            list.add(i, StyledString.EMPTY_STRING)
        }
        val iterator = node.columns.iterator()
        var index = controlObject.indentLevel

        fun addToList(styledString: StyledString) = list.add(index++, styledString)

        addToList(if (node.useEmptyPrefix) StyledString.EMPTY_STRING else iconStyler.style(controlObject.prefix))

        var maybeNodes: Option<ArrayList<OutputNode>> = None

        fun bla(s: StyledString) {
            if (s.noWrap) {
                addToList(s)
            } else {
                val lines = s.result().split('\n')
                if (lines.size == 1) {
                    addToList(s)
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
        bla(iterator.next())
        while (iterator.hasNext()) {
            bla(iterator.next())
        }
        return maybeNodes.fold({
            node.copy(columns = list, indentLevel = node.indentLevel + controlObject.indentLevel)
        }, { nodes ->
            nodes.first().copy(children = nodes.drop(1) + node.children)
        })
    }
}


interface PreRenderer {
    fun canTransform(reportable: Reportable): Boolean
    fun transform(reportable: Reportable, controlObject: PreRenderControlObject): List<OutputNode>
}

data class OutputNode(
    val columns: List<StyledString>,
    val children: List<OutputNode>,
    val definesOwnLevel: Boolean,
    val indentLevel: Int = 0,
    val span: Int = 0,
    val useEmptyPrefix: Boolean = false
) {
    init {
        require(columns.isNotEmpty() || children.isNotEmpty()) { "at least one of columns/children needs to have elements" }
        require(span < columns.size) { "cannot span more than available columns. span: $span, columns size: ${columns.size}" }
    }
}

fun singleOutputNode(columns: List<StyledString>) =
    listOf(OutputNode(columns, emptyList(), definesOwnLevel = false))

fun singleOutputNode(column1: StyledString) = singleOutputNode(listOf(column1))
fun singleOutputNode(column1: StyledString, column2: StyledString) = singleOutputNode(listOf2(column1, column2))
fun singleOutputNode(column1: StyledString, column2: StyledString, column3: StyledString) =
    singleOutputNode(listOf3(column1, column2, column3))

fun singleOutputNode(
    column1: StyledString,
    column2: StyledString,
    column3: StyledString,
    vararg columns: StyledString
) = singleOutputNode(listOfX(column1, column2, column3, columns))

abstract class TypedPreRenderer<R : Reportable>(private val kClass: KClass<R>) : PreRenderer {
    override fun canTransform(reportable: Reportable): Boolean = kClass.isInstance(reportable)
    override fun transform(reportable: Reportable, controlObject: PreRenderControlObject): List<OutputNode> =
        transformIt(kClass.cast(reportable), controlObject)

    protected abstract fun transformIt(reportable: R, controlObject: PreRenderControlObject): List<OutputNode>
}

object FailingProofsAndOthers : ReportableFilter {
    override fun includeInReporting(reportable: Reportable): Boolean =
        when (reportable) {
            is Proof -> true // TODO !reportable.holds()
            else -> true
        }
}

interface ThemeProvider {
    val styles: Map<String, String>
}

enum class Style(val styleId: String) {
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE"),
    INFORMATION_SOURCE("INFORMATION_SOURCE"),
    BULB("BULB")
}

enum class Icon : Single {
    BULB,
    BANGBANG,
    DEBUG_INFO,
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

class DefaultIconStyler(styler: Styler) : IconStyler {
    private val icons = mapOf(
        BULB to styler.style("💡 ", Style.BULB),
        BANGBANG to styler.style("❗❗ ", Style.FAILURE),
        DEBUG_INFO to "🔎 ".noStyle(),
        EMPTY_STRING to StyledString.EMPTY_STRING,
        FAILURE to styler.style("✘ ", Style.FAILURE),
        FEATURE to "▶ ".noStyle(),
        FEATURE_BULLET_POINT to "- ".noStyle(),
        INFORMATION_SOURCE to styler.style("ℹ️", Style.INFORMATION_SOURCE),
        ROOT_BULLET_POINT to "◆".noStyle(),
        SUCCESS to styler.style("✔ ", Style.SUCCESS)
    )

    override fun style(icon: Icon): StyledString =
        icons[icon] ?: throw IllegalArgumentException("unsupported icon: $icon")
}

class Ansi8Colours : ThemeProvider {
    override val styles = mapOf(
        Style.SUCCESS.styleId to "\u001b[32m",
        Style.FAILURE.styleId to "\u001b[31m",
        Style.INFORMATION_SOURCE.styleId to "\u001b[1m\u001b[94m",
        Style.BULB.styleId to "\u001B[1m\u001b[33;1m"
    )
}

interface Styler {
    fun supportsAnsi(): Boolean
    fun style(s: String, maybeStyle: Option<Style>, noWrap: Boolean = true): StyledString =
        maybeStyle.fold({ s.noStyle(noWrap) }) { style(s, it, noWrap) }

    fun style(s: String, style: Style, noWrap: Boolean = true): StyledString
    fun style(s: String, styleId: String, noWrap: Boolean = true): StyledString
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

    private fun replaceWrap(s: String) = if (noWrap) {
        s.replace('\n', ' ')
    } else {
        s
    }

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

class DefaultStyler(
    private val themeProvider: ThemeProvider
) : Styler {

    private val couldSupportAnsi by lazy {
        true // System.console() != null
    }

    override fun supportsAnsi(): Boolean = couldSupportAnsi

    override fun style(s: String, style: Style, noWrap: Boolean): StyledString =
        style(s, style.styleId, noWrap)

    override fun style(s: String, styleId: String, noWrap: Boolean): StyledString =
        if (couldSupportAnsi) {
            val colorCode = themeProvider.styles[styleId]
            if (colorCode != null) {
                StyledString(s, Some(colorCode + s + RESET), noWrap = noWrap)
            } else {
                s.noStyle(noWrap)
            }
        } else {
            s.noStyle(noWrap)
        }

    companion object {
        const val RESET = "\u001B[0m"
    }
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
            controlObject.copy(prefix = SUCCESS, indentLevel = indentLevel)
        } else {
            controlObject.copy(prefix = FAILURE, indentLevel = indentLevel)
        }
        else -> controlObject.copy(prefix = childPrefix, indentLevel = indentLevel)
    }
}


class DefaultRootPreRenderer(
    private val translator: Translator,
    private val objectFormatter: DefaultTextObjFormatter
) : TypedPreRenderer<Root>(Root::class) {

    override fun transformIt(reportable: Root, controlObject: PreRenderControlObject): List<OutputNode> {
        return listOf(
            OutputNode(
                listOf(
                    translator.translate(reportable.description).noStyle(),
                    " : ".noStyle(),
                    objectFormatter.format(reportable.representation)
                ),
                reportable.children.flatMap { child ->
                    val newControlObject = determineChildControlObject(
                        controlObject, child, ROOT_BULLET_POINT, shallIndentChildren = false
                    )
                    controlObject.controller.transformChild(child, newControlObject)
                },
                definesOwnLevel = true,
                span = 1
            )
        )
    }
}

class DefaultFeaturePreRenderer(
    private val translator: Translator,
    private val objectFormatter: DefaultTextObjFormatter,
    private val iconStyler: IconStyler
) : TypedPreRenderer<Feature>(Feature::class) {

    override fun transformIt(reportable: Feature, controlObject: PreRenderControlObject): List<OutputNode> {
        return listOf(
            OutputNode(
                listOf(
                    iconStyler.style(FEATURE),
                    translator.translate(reportable.description).noStyle(),
                    " : ".noStyle(),
                    objectFormatter.format(reportable.representation)
                ),
                reportable.children.flatMap { child ->
                    val newControlObject = determineChildControlObject(
                        controlObject, child, FEATURE_BULLET_POINT, shallIndentChildren = true
                    )
                    controlObject.controller.transformChild(child, newControlObject)
                },
                definesOwnLevel = true,
                useEmptyPrefix = true,
                span = 2
            )
        )
    }
}

class DefaultParagraphPreRenderer : TypedPreRenderer<Paragraph>(Paragraph::class) {

    override fun transformIt(reportable: Paragraph, controlObject: PreRenderControlObject): List<OutputNode> {
        val tmpColumns = reportable.children
            .flatMap { controlObject.controller.transform(it) }
            .flatMap {
                it.columns
            }
        val paragraphColumns = if (reportable.withIndent) {
            val list = ArrayList<StyledString>(1 + tmpColumns.size)
            list.add(StyledString.EMPTY_STRING)
            list.addAll(tmpColumns)
            list
        } else {
            tmpColumns
        }
        return listOf(
            OutputNode(
                paragraphColumns,
                emptyList(),
                definesOwnLevel = true,
                useEmptyPrefix = true,
                indentLevel = if (reportable.withIndent) 1 else 0
            )
        )
    }
}

class DefaultTextPreRenderer(private val styler: Styler) : TypedPreRenderer<Text2>(Text2::class) {
    override fun transformIt(reportable: Text2, controlObject: PreRenderControlObject): List<OutputNode> =
        listOf(
            OutputNode(
                listOf(styler.style(reportable.txt, reportable.maybeStyle, noWrap = false)),
                emptyList(),
                definesOwnLevel = true
            )
        )
}


class DefaultIconPreRenderer(private val iconStyler: IconStyler) : TypedPreRenderer<Icon>(Icon::class) {

    override fun transformIt(reportable: Icon, controlObject: PreRenderControlObject): List<OutputNode> =
        singleOutputNode(iconStyler.style(reportable))
}

class DefaultSimpleProofPreRenderer(
    private val translator: Translator,
    private val objectFormatter: DefaultTextObjFormatter
) : TypedPreRenderer<SimpleProof>(SimpleProof::class) {
    override fun transformIt(reportable: SimpleProof, controlObject: PreRenderControlObject): List<OutputNode> =
        singleOutputNode(
            translator.translate(reportable.description).noStyle(),
            " : ".noStyle(),
            objectFormatter.format(reportable.representation)
        )
}

data class SimpleProof(
    val description: Translatable, val representation: Any, val test: () -> Boolean
) : Proof, Reportable {

    private val itHolds by lazy(LazyThreadSafetyMode.NONE) { test() }
    override fun holds(): Boolean = itHolds
}

interface Reportable
interface Single : Reportable
interface Group : Reportable {
    /**
     * The description of the group.
     */
    val description: Translatable

    /**
     * A complementing representation to the description -- typically the subject for which the [proofs]
     * are defined.
     *
     * For instance, if the description is `index 0` then the representation shows what is at index 0.
     */
    val representation: Any

    val children: List<Reportable>
}

abstract class ProofGroup : Proof, Group {

    private val itHolds by lazy {
        children.all {
            when (it) {
                is Proof -> it.holds()
                else -> true
            }
        }
    }

    override fun holds(): Boolean = itHolds
}

data class InvisibleGroup(
    override val description: Translatable,
    override val representation: Any,
    override val children: List<Reportable>
) : Group

data class Feature(
    override val description: Translatable,
    override val representation: Any,
    override val children: List<Reportable>
) : ProofGroup()

data class Root(
    override val description: Translatable,
    override val representation: Any,
    override val children: List<Reportable>
) : ProofGroup()

interface DoNotFilterGroup : Group

data class DefaultDoNotFilterGroup(
    override val description: Translatable,
    override val representation: Any,
    override val children: List<Reportable>
) : DoNotFilterGroup

data class Paragraph(val children: List<Single>, val withIndent: Boolean) : Reportable {
    constructor(r1: Single, withIndent: Boolean = true) : this(listOf(r1), withIndent)
    constructor(r1: Single, r2: Single, withIndent: Boolean = true) : this(listOf2(r1, r2), withIndent)
    constructor(r1: Single, r2: Single, r3: Single, withIndent: Boolean = true) : this(
        listOf3(r1, r2, r3),
        withIndent
    )

    constructor(r1: Single, r2: Single, r3: Single, vararg rx: Single, withIndent: Boolean = true) :
        this(listOfX(r1, r2, r3, rx), withIndent)
}

inline fun <reified T> listOf2(t1: T, t2: T) = arrayOf(t1, t2).asList()
inline fun <reified T> listOf3(t1: T, t2: T, t3: T) = arrayOf(t1, t2, t3).asList()
inline fun <reified T> listOfX(t1: T, t2: T, t3: T, tx: Array<out T>) = (arrayOf(t1, t2, t3) + tx).asList()

data class Text2(val txt: String, val maybeStyle: Option<Style>) : Single {
    constructor(txt: String, style: Style) : this(txt, Some(style))
    constructor(txt: String) : this(txt, None)
}


interface Proof {
    fun holds(): Boolean
}
