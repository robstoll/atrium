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
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import java.lang.IllegalStateException
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
    private val textPreRenderController: TextPreRenderController,
    private val styler: Styler
) : NewReporter {

    override fun createReport(root: Root): StringBuilder {
        val rootNodes = textPreRenderController.transform(root)
        if (rootNodes.size != 1) throw IllegalStateException("${Root::class.simpleName} transformation should always result in one ${OutputNode::class.simpleName}")
        val rootNode = rootNodes[0]
        val sb = StringBuilder()
        format(rootNode, sb)
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

    private fun format(node: OutputNode, sb: StringBuilder) {
        val maxLengths = calculateMaxLengths(node)
        fun appendColumns(columns: List<StyledString>) {
            val size = columns.size
            (0 until size - 1).forEach { i ->
                val s = columns[i]
                sb.append(s.padEnd(maxLengths[i]))
            }
            sb.append(columns[size - 1].result())
            sb.appendln()
        }
        appendColumns(node.columns)

        node.children.forEach { child ->
            if (child.children.isEmpty()) {
                appendColumns(child.columns)
            } else {
                format(child, sb)
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
                        maxLengths.add(index, length)
                    }
                } else {
                    maxLengths.add(index, length)
                }
            }
        }
        updateMaxLengths(node)
        node.children
            .asSequence()
            .filter { it.children.isEmpty() }
            .forEach(::updateMaxLengths)
        return maxLengths.toList()
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

fun main(args: Array<String>) {
    val root = Root(
        Untranslatable("expected the subject"), "1", listOf(
            SimpleProof(Untranslatable("starts with"), "hello") { false },
            SimpleProof(Untranslatable("ends with"), "test") { true },
            Text2("hello"),
            FeatureGroup(
                Untranslatable("name"),
                Text2("robert", Style.FAILURE),
                listOf(Text2("starts with"), SimpleProof(Untranslatable("to equal"), 1) { false })
            )
        )
    )
    val translator = UsingDefaultTranslator()
//    val objectFormatter = DefaultTextObjectFormatter(translator)
    val styler = DefaultStyler(Ansi8Colours())
    val objFormatter = DefaultTextObjFormatter(styler)
    val sb = DefaultTextReporter(
        DefaultPreRenderController(
            FailingProofsAndOthers,
            sequenceOf(
                DefaultSimpleProofPreRenderer(translator, objFormatter),
                DefaultTextPreRenderer(styler),
                DefaultFeatureGroupPreRenderer(translator, objFormatter),
                DefaultRootPreRenderer(translator, objFormatter, styler)
            )
        ),
        styler
    ).createReport(root)
    println(sb)
}

data class PreRenderControlObject(
    val prefix: StyledString,
    val indentLevel: Int,
    val controller: TextPreRenderController
)

interface TextPreRenderController {
    fun transform(reportable: Reportable): List<OutputNode>
    fun transformChildren(children: List<Reportable>, controlObject: PreRenderControlObject): List<OutputNode>
    fun transformChild(child: Reportable, controlObject: PreRenderControlObject): List<OutputNode>
}

class DefaultPreRenderController(
    private val reportableFilters: ReportableFilter,
    private val preRenderers: Sequence<PreRenderer>
) : TextPreRenderController {

    override fun transform(reportable: Reportable): List<OutputNode> =
        transformChildren(listOf(reportable), PreRenderControlObject("".noStyle(), 0, this))

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
                val list = ArrayList<StyledString>(controlObject.indentLevel + node.columns.size)
                (0 until controlObject.indentLevel).forEach { i ->
                    list.add(i, "".noStyle())
                }
                val iterator = node.columns.iterator()
                list.add(controlObject.indentLevel, controlObject.prefix + iterator.next())
                var index = controlObject.indentLevel + 1
                while (iterator.hasNext()) {
                    list.add(index, iterator.next())
                    index++
                }
                node.copy(columns = list)
            }
        } else {
            emptyList()
        }
}


interface PreRenderer {
    fun canTransform(reportable: Reportable): Boolean
    fun transform(reportable: Reportable, controlObject: PreRenderControlObject): List<OutputNode>

}

data class OutputNode(val columns: List<StyledString>, val children: List<OutputNode>) {
    init {
        require(columns.isNotEmpty()) { "it seems like you forgot to specify the columns" }
    }
}

abstract class TypedPreRenderer<R : Reportable>(private val kClass: KClass<R>) : PreRenderer {
    override fun canTransform(reportable: Reportable): Boolean = kClass.isInstance(reportable)
    override fun transform(reportable: Reportable, controlObject: PreRenderControlObject): List<OutputNode> =
        transformIt(kClass.cast(reportable), controlObject)

    protected abstract fun transformIt(reportable: R, controlObject: PreRenderControlObject): List<OutputNode>
}

object FailingProofsAndOthers : ReportableFilter {
    override fun includeInReporting(reportable: Reportable): Boolean =
        when (reportable) {
            is Proof -> !reportable.holds()
            else -> true
        }
}

interface ThemeProvider {
    val styles: Map<String, String>
}

enum class Style(val styleId: String) {
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE")
}

class Ansi8Colours : ThemeProvider {
    override val styles = mapOf(
        Style.SUCCESS.styleId to "\u001b[32m",
        Style.FAILURE.styleId to "\u001b[31m"
    )
}

interface Styler {
    fun supportsAnsi(): Boolean
    fun style(s: String, maybeStyle: Option<Style>): StyledString =
        maybeStyle.fold({ s.noStyle() }) { style(s, it) }

    fun style(s: String, style: Style): StyledString
    fun style(s: String, styleId: String): StyledString
}

data class StyledString(val unstyled: String, val maybeStyled: Option<String>) {

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


class DefaultRootPreRenderer(
    private val translator: Translator,
    private val objectFormatter: DefaultTextObjFormatter,
    private val styler: Styler
) : TypedPreRenderer<Root>(Root::class) {

    override fun transformIt(reportable: Root, controlObject: PreRenderControlObject): List<OutputNode> {
        val successfulControlObject = controlObject.copy(prefix = styler.style("✔ ", Style.SUCCESS))
        val failingControlObject = controlObject.copy(prefix = styler.style("✘ ", Style.FAILURE))
        val other = controlObject.copy(prefix = "◆ ".noStyle())
        return listOf(
            OutputNode(
                listOf(
                    translator.translate(reportable.description).noStyle(),
                    " : ".noStyle(),
                    objectFormatter.format(reportable.representation)
                ),
                reportable.children.flatMap { child ->
                    val newControlObject = when (child) {
                        is Proof -> if (child.holds()) successfulControlObject else failingControlObject
                        else -> other
                    }
                    controlObject.controller.transformChild(child, newControlObject)
                }
            )
        )
    }
}

class DefaultFeatureGroupPreRenderer(
    private val translator: Translator,
    private val objectFormatter: DefaultTextObjFormatter
) : TypedPreRenderer<FeatureGroup>(FeatureGroup::class) {

    override fun transformIt(reportable: FeatureGroup, controlObject: PreRenderControlObject): List<OutputNode> =
        listOf(
            OutputNode(
                listOf(
                    "▶ ".noStyle(),
                    translator.translate(reportable.description).noStyle(),
                    " : ".noStyle(),
                    objectFormatter.format(reportable.representation)
                ),
                controlObject.controller.transformChildren(
                    reportable.children,
                    controlObject.copy(prefix = "- ".noStyle(), indentLevel = controlObject.indentLevel + 1)
                )
            )
        )
}

class DefaultTextPreRenderer(private val styler: Styler) : TypedPreRenderer<Text2>(Text2::class) {
    override fun transformIt(reportable: Text2, controlObject: PreRenderControlObject): List<OutputNode> =
        listOf(OutputNode(listOf(styler.style(reportable.txt, reportable.maybeStyle)), emptyList()))
}

class DefaultSimpleProofPreRenderer(
    private val translator: Translator,
    private val objectFormatter: DefaultTextObjFormatter
) : TypedPreRenderer<SimpleProof>(SimpleProof::class) {
    override fun transformIt(reportable: SimpleProof, controlObject: PreRenderControlObject): List<OutputNode> =
        listOf(
            OutputNode(
                listOf(
                    translator.translate(reportable.description).noStyle(),
                    " : ".noStyle(),
                    objectFormatter.format(reportable.representation)
                ),
                emptyList()
            )
        )
}

data class SimpleProof(
    val description: Translatable, val representation: Any, val test: () -> Boolean
) : Single, Proof {

    private val itHolds by lazy(LazyThreadSafetyMode.NONE) { test() }

    override fun holds(): Boolean = itHolds
}

interface Reportable
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

interface Single : Reportable

abstract class ProofGroup : Group, Proof {

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

data class FeatureGroup(
    override val description: Translatable,
    override val representation: Any,
    override val children: List<Reportable>
) : ProofGroup()

data class Root(
    override val description: Translatable,
    override val representation: Any,
    override val children: List<Reportable>
) : ProofGroup()

data class Text2(val txt: String, val maybeStyle: Option<Style>) : Reportable {
    constructor(txt: String, style: Style) : this(txt, Some(style))
    constructor(txt: String) : this(txt, None)
}

interface Proof {
    fun holds(): Boolean
}
