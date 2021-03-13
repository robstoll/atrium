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
    private val textPreRenderController: TextPreRenderController
) : NewReporter {

    override fun createReport(root: Root): StringBuilder {
        val rootNodes = textPreRenderController.transform(root)
        if (rootNodes.size != 1) throw IllegalStateException("${Root::class.simpleName} transformation should always result in one ${OutputNode::class.simpleName}")
        val rootNode = rootNodes[0]
        val sb = StringBuilder()
        format(rootNode, emptyList(), sb)
        return sb
//
//
//    private fun noNeedToFormat(assertion: Assertion, parameterObject: AssertionFormatterParameterObject): Boolean {
//        //assertionFilter only applies if:
//        // - we are not in an assertion group which should not be filtered (e.g. explanatory or summary group) and
//        // - if the given assertion is not an explanatory assertion group either.
//        return parameterObject.isNotInDoNotFilterGroup()
//            && !isExplanatoryAssertionGroup(assertion)
//            && !parameterObject.assertionFilter(assertion)
//    }
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
            is Text2 -> styler.style(value.txt, value.maybeStyle)
            is String -> "\"$value\"".noStyle()
            else -> value.toString().noStyle()
        }
}

data class PreRenderControlObject(
    val prefix: Icon,
    val indentLevel: Int,
    val controller: TextPreRenderController
)

interface TextPreRenderController {
    fun transform(reportable: Reportable): List<OutputNode>
    fun transformChildren(children: List<Reportable>, controlObject: PreRenderControlObject): List<OutputNode>
    fun transformChild(child: Reportable, controlObject: PreRenderControlObject): List<OutputNode>

    fun indent(node: OutputNode, controlObject: PreRenderControlObject): OutputNode
}

class DefaultPreRenderController(
    private val reportableFilters: ReportableFilter,
    private val preRenderers: Sequence<PreRenderer>,
    private val iconStyler: IconStyler
) : TextPreRenderController {

    override fun transform(reportable: Reportable): List<OutputNode> =
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
                indent(node, controlObject)
            }
        } else {
            emptyList()
        }

    override fun indent(
        node: OutputNode,
        controlObject: PreRenderControlObject
    ): OutputNode {
        val list = ArrayList<StyledString>(controlObject.indentLevel + 1 + node.columns.size)
        (0 until controlObject.indentLevel).forEach { i ->
            list.add(i, StyledString.EMPTY_STRING)
        }
        val iterator = node.columns.iterator()
        var index = controlObject.indentLevel
        list.add(index++, iconStyler.style(controlObject.prefix))
        list.add(index++, iterator.next())
        while (iterator.hasNext()) {
            list.add(index++, iterator.next())
        }
        return node.copy(columns = list, indentLevel = node.indentLevel + controlObject.indentLevel)
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
    val useIndentInsteadPrefix: Boolean = false
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
    INFORMATION_SOURCE,
    BULB,
    DEBUG_INFO,
    BANGBANG,
    SUCCESS,
    FAILURE,
    FEATURE,
    EMPTY_STRING,
    BULLET_POINT
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
    fun style(s: String, maybeStyle: Option<Style>): StyledString =
        maybeStyle.fold({ s.noStyle() }) { style(s, it) }

    fun style(s: String, style: Style): StyledString
    fun style(s: String, styleId: String): StyledString
}

data class StyledString(val unstyled: String, val maybeStyled: Option<String>, val span: Int = 0) {

    fun result() = maybeStyled.getOrElse { unstyled }

    operator fun plus(s: StyledString) = StyledString(
        unstyled + s.unstyled,
        if (maybeStyled.isDefined() || s.maybeStyled.isDefined()) {
            Some(result() + s.result())
        } else {
            None
        }
    )

    fun padEnd(length: Int): String =
        maybeStyled.fold({ unstyled.padEnd(length) }) { styled ->
            val unstyledLength = unstyled.length
            val styleDiff = styled.length - unstyledLength

            val sb = StringBuilder(length + styleDiff)
            sb.append(styled)
            repeat((length - unstyledLength)) { sb.append(' ') }
            sb.toString()
        }

    companion object {
        val EMPTY_STRING = "".noStyle()
    }
}


fun String.noStyle(): StyledString = StyledString(this, None)

class DefaultStyler(
    private val themeProvider: ThemeProvider
) : Styler {

    private val couldSupportAnsi by lazy {
        true // System.console() != null
    }

    override fun supportsAnsi(): Boolean = couldSupportAnsi

    override fun style(s: String, style: Style): StyledString =
        style(s, style.styleId)

    override fun style(s: String, styleId: String): StyledString =
        if (couldSupportAnsi) {
            val colorCode = themeProvider.styles[styleId]
            if (colorCode != null) {
                StyledString(s, Some(colorCode + s + RESET))
            } else {
                s.noStyle()
            }
        } else {
            s.noStyle()
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
        // check syntax for fallthrough or |
        is Feature -> controlObject.copy(prefix = EMPTY_STRING, indentLevel = indentLevel)
        is Paragraph -> controlObject.copy(prefix = EMPTY_STRING, indentLevel = indentLevel)
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
                        controlObject, child, EMPTY_STRING, shallIndentChildren = false
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
                        controlObject, child, BULLET_POINT, shallIndentChildren = true
                    )
                    controlObject.controller.transformChild(child, newControlObject)
                },
                definesOwnLevel = true,
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
                // does not work either because
                indentLevel = if (reportable.withIndent) 1 else 0
            )
        )
    }
}

class DefaultTextPreRenderer(private val styler: Styler) : TypedPreRenderer<Text2>(Text2::class) {
    override fun transformIt(reportable: Text2, controlObject: PreRenderControlObject): List<OutputNode> =
        singleOutputNode(styler.style(reportable.txt, reportable.maybeStyle))
}

interface IconStyler {
    fun style(icon: Icon): StyledString
}

class DefaultIconStyler(styler: Styler) : IconStyler {
    private val icons = mapOf(
        INFORMATION_SOURCE to styler.style("ℹ️", Style.INFORMATION_SOURCE),
        BULB to styler.style("💡 ", Style.BULB),
        DEBUG_INFO to "🔎 ".noStyle(),
        BANGBANG to styler.style("❗❗ ", Style.FAILURE),
        SUCCESS to styler.style("✔ ", Style.SUCCESS),
        FAILURE to styler.style("✘ ", Style.FAILURE),
        FEATURE to "▶ ".noStyle(),
        EMPTY_STRING to StyledString.EMPTY_STRING,
        BULLET_POINT to "- ".noStyle()
    )

    override fun style(icon: Icon): StyledString =
        icons[icon] ?: throw IllegalArgumentException("unsupported icon: $icon")
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
            listOf(
                translator.translate(reportable.description).noStyle(),
                " : ".noStyle(),
                objectFormatter.format(reportable.representation)
            )
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
