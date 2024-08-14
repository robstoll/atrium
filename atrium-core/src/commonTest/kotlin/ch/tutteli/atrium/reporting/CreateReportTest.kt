package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.impl.ComponentFactoryContainerImpl
import ch.tutteli.atrium.creating.impl.DefaultComponentFactoryContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.Reportable
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.test.Test

@OptIn(ExperimentalComponentFactoryContainer::class)
class CreateReportTest {
    private val x = TextColors.red("✘")

    @Test
    fun text() {
        val reportable = Text("bla")
        val expectedResult = "bla"

        expectForReporterWithAnsi(reportable, expectedResult)
        expectForReporterWithoutAnsi(reportable, expectedResult)
    }


    @Test
    fun simpleProof() {
        val reportable = Proof.simple(Text("bla"), 1) { false }
        val expectedResult = "bla : 1"

        expectForReporterWithAnsi(reportable, expectedResult)
        expectForReporterWithoutAnsi(reportable, expectedResult)
    }

    @Test
    fun rootProofGroupWithSimpleProofAndRepresentation() {
        val reportable = Proof.rootGroup(
            Text("a verb"), "representation",
            children = listOf(
                Proof.simple(Text("to equal"), 1) { false },
                Text("some text")
            )
        )
        expectForReporterWithAnsi(
            reportable,
            """
            |a verb     : "representation"
           |$x to equal : 1
            |◆ some text
            """.trimMargin()
        )
        expectForReporterWithoutAnsi(
            reportable,
            """
            |a verb       : "representation"
            |(f) to equal : 1
            |◆ some text
            """.trimMargin()
        )
    }

    @Test
    fun rootVerbLongerThanExpectations() {
        val reportable = Proof.rootGroup(
            Text("I expected that the subject which was"),
            2,
            listOf(
                Proof.simple(Text("to equal"), 3) { false },
                Proof.simple(Text("to be less than"), 3) { false },
                Proof.simple(Text("to greater than"), 10) { false }
            )
        )

        expectForReporterWithAnsi(
            reportable,
            """
            |I expected that the subject which was : 2
           |$x to equal                            : 3
           |$x to be less than                     : 3
           |$x to greater than                     : 10
            """.trimMargin()
        )

        expectForReporterWithoutAnsi(
            reportable,
            """
            |I expected that the subject which was : 2
            |(f) to equal                          : 3
            |(f) to be less than                   : 3
            |(f) to greater than                   : 10
            """.trimMargin()
        )
    }

    @Test
    fun representationWithNewLineIsWrappedCorrectly() {
        val reportable = Proof.rootGroup(
            Text("verb\nalways not wrapped"),
            "a string with new line\nas representation is wrapped\nmaxLength calculated correctly",
            listOf(
                Proof.simple(Text("test"), 1) { false },
                Columns(
                    "(i) ",
                    "first column",
                    " : ",
                    "second longer than longest line of representation",
                    " : ",
                    "1",
                    usesOwnPrefix = true
                )
            )
        )

        expectForReporterWithAnsi(
            reportable,
            """
            |verb always not wrapped : ""${"\""}
            |                          a string with new line
            |                          as representation is wrapped
            |                          maxLength calculated correctly
            |                          ""${"\""}
           |$x   test                : 1
            |(i) first column        : second longer than longest line of representation : 1
            """.trimMargin()
        )

        expectForReporterWithoutAnsi(
            reportable,
            """
            |verb always not wrapped : ""${"\""}
            |                          a string with new line
            |                          as representation is wrapped
            |                          maxLength calculated correctly
            |                          ""${"\""}
            |(f) test                : 1
            |(i) first column        : second longer than longest line of representation : 1
            """.trimMargin()
        )
    }


    private fun expectForReporterWithAnsi(reportable: Reportable, expectedResult: String) {
        val reporter = componentFactoryContainer().build<TextReporter>()
        expect(reporter.createReport(reportable).toString()).toEqual(expectedResult)
    }

    private fun expectForReporterWithoutAnsi(reportable: Reportable, expectedResult: String) {
        val reporter = reporterWithoutAnsi()
        expect(reporter.createReport(reportable).toString()).toEqual(expectedResult)
    }

    private fun reporterWithoutAnsi(): TextReporter {
        val reporter = componentFactoryContainer().merge(
            ComponentFactoryContainerImpl(
                mapOf(
                    Terminal::class to ComponentFactory({ c ->
                        Terminal(ansiLevel = AnsiLevel.NONE)
                    }, producesSingleton = false)
                ),
                emptyMap()
            )
        ).build<TextReporter>()
        return reporter
    }

    private fun componentFactoryContainer() = DefaultComponentFactoryContainer.merge(
        ComponentFactoryContainerImpl(
            emptyMap(),
            mapOf(
                TextPreRenderer::class to sequenceOf(
                    ComponentFactory({ _ ->
                        DefaultColumnsPreRenderer()
                    }, producesSingleton = false)
                )
            )
        )
    )

}

class Columns(
    val columns: List<StyledString>,
    val mergeColumns: Int = 0,
    val definesOwnLevel: Boolean = false,
    val usesOwnPrefix: Boolean = false,
) : Reportable {
    constructor(
        vararg strings: String,
        mergeColumns: Int = 0,
        definesOwnLevel: Boolean = false,
        usesOwnPrefix: Boolean = false
    ) : this(
        strings.map { it.noStyle(noWrap = false) },
        mergeColumns = mergeColumns,
        definesOwnLevel = definesOwnLevel,
        usesOwnPrefix = usesOwnPrefix
    )
}

class DefaultColumnsPreRenderer : TypedTextPreRenderer<Columns>(Columns::class) {
    override fun transformIt(reportable: Columns, controlObject: PreRenderControlObject): List<OutputNode> =
        listOf(
            OutputNode(
                columns = reportable.columns,
                children = emptyList(),
                definesOwnLevel = reportable.definesOwnLevel,
                mergeColumns = reportable.mergeColumns,
                usesOwnPrefix = reportable.usesOwnPrefix
            )
        )
}
