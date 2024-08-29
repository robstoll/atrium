package ch.tutteli.atrium.reporting

import ch.tutteli.atrium._core
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.BasicExplanatoryAssertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroup
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.impl.ComponentFactoryContainerImpl
import ch.tutteli.atrium.creating.impl.DefaultComponentFactoryContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.EntryPointProofBuilder
import ch.tutteli.atrium.creating.proofs.buildProof
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.test.Test

@OptIn(ExperimentalComponentFactoryContainer::class)
class CreateReportTest {
    private val x = TextColors.red("✘")
    private val f = TextColors.cyan("▶")
    private val i = TextStyle(TextColors.brightBlue, bold = true).invoke("ℹ\uFE0F") + " "
    private val d = TextColors.blue("🔎")

    @Test
    fun text() {
        val reportable = Text("bla")
        val expectedResult = "bla"

        expectForReporterWithoutAnsi(reportable, expectedResult)
        expectForReporterWithAnsi(reportable, expectedResult)
    }

    @Test
    fun simpleProof() {
        val reportable = Proof.simple(Text("bla"), 1) { false }
        val expectedResult = "bla : 1"

        expectForReporterWithoutAnsi(reportable, expectedResult)
        expectForReporterWithAnsi(reportable, expectedResult)
    }

    @Test
    fun rootWithSimpleProofAndRepresentation() {
        val builder = buildRootGroup {
            simpleProof(Text("to equal"), 1) { false }
            add(Text("some text"))
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb       : "representation"
            |(f) to equal : 1
            |◆ some text
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb     : "representation"
           |$x to equal : 1
            |◆ some text
            """.trimMargin()
        )
    }

    @Test
    fun rootWithTwoSimpleProof_onlyOneFailing_calculatesColumnsOnlyBasedOnVisible() {
        val builder = buildRootGroup {
            simpleProof(Text("to equal"), 1) { false }
            simpleProof(Text("a longer text than to equal"), 1) { true }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb       : "representation"
            |(f) to equal : 1
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb     : "representation"
           |$x to equal : 1
            """.trimMargin()
        )
    }

    @Test
    fun featureProofGroup() {
        val builder = buildRootGroup(verb = Text("my expectations"), representation = Text.EMPTY) {
            feature(Text("verb"), 2) {
                feature(Text("name"), "Robert") {
                    simpleProof(Text("to equal"), "Peter") { false }
                }
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |my expectations :${" "}
            |(f) > verb : 2
            |      (f) > name         : "Robert"
            |            (f) to equal : "Peter"
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |my expectations :${" "}
           |$x $f verb : 2
           |    $x $f name       : "Robert"
            |        $x to equal : "Peter"
            """.trimMargin()
        )
    }

    @Test
    fun featureProofGroupWithMultilineDescription() {
        //TODO 1.3.0 use case method call with multiple arguments
    }

    @Test
    fun invisibleGroup_childrenPrefixedAccordingToType_notJustAsFailure() {
        val builder = buildRootGroup {
            invisibleGroup {
                simpleProof(Text("simple"), 1) { false }
                add(Text("text"))
            }
            // so that the above invisibleGroup is not unwrapped
            simpleProof(Text("another"), 2) { false }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb      : "representation"
            |(f) simple  : 1
            |◆ text
            |(f) another : 2
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb    : "representation"
           |$x simple  : 1
            |◆ text
           |$x another : 2
            """.trimMargin()
        )
    }

    @Test
    fun rootVerbLongerThanExpectations() {
        val reportable = buildRootGroup(verb = Text("I expected that the subject which was"), representation = 2) {
            simpleProof(Text("to equal"), 3) { false }
            simpleProof(Text("to be less than"), 3) { false }
            simpleProof(Text("to greater than"), 10) { false }
        }

        expectForReporterWithoutAnsi(
            reportable,
            """
            |I expected that the subject which was : 2
            |(f) to equal                          : 3
            |(f) to be less than                   : 3
            |(f) to greater than                   : 10
            """.trimMargin()
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

    }

    @Test
    fun representationWithNewLineIsWrappedCorrectly() {
        val builder = buildRootGroup(
            verb =
            Text("verb always\nwithout line break"),
            representation = "a string with new line\nas representation is wrapped\nmaxLength calculated correctly",
        ) {
            simpleProof(Text("test"), 1) { false }
            add(
                //TODO 1.3.0 add ownPrefix to row?
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
        }

        expectForReporterWithoutAnsi(
            builder,
            """
            |verb always without line break : ""${"\""}
            |                                 a string with new line
            |                                 as representation is wrapped
            |                                 maxLength calculated correctly
            |                                 ""${"\""}
            |(f) test                       : 1
            |(i) first column               : second longer than longest line of representation : 1
            """.trimMargin()
        )

        expectForReporterWithAnsi(
            builder,
            """
            |verb always without line break : ""${"\""}
            |                                 a string with new line
            |                                 as representation is wrapped
            |                                 maxLength calculated correctly
            |                                 ""${"\""}
           | $x  test                       : 1
            |(i) first column               : second longer than longest line of representation : 1
            """.trimMargin()
        )
    }

    @Suppress("DEPRECATION")
    @Test
    fun explanatoryAssertionGroupNested() {
        val reportable = Proof.rootGroup(
            Text("verb"), 1,
            listOf(
                ExplanatoryAssertionGroup(
                    InformationAssertionGroupType(withIndent = false), listOf(
                        BasicExplanatoryAssertion(Text("properties of the unknown Exception")),
                        ExplanatoryAssertionGroup(
                            DefaultExplanatoryAssertionGroupType, listOf(
                                BasicDescriptiveAssertion(
                                    DescriptionThrowableExpectation.OCCURRED_EXCEPTION_MESSAGE,
                                    "bla"
                                ) { true },
                                BasicAssertionGroup(
                                    DefaultListAssertionGroupType,
                                    DescriptionThrowableExpectation.OCCURRED_EXCEPTION_STACKTRACE,
                                    Text.EMPTY,
                                    listOf(
                                        BasicExplanatoryAssertion(Text("test")),
                                        BasicExplanatoryAssertion(Text("lines"))
                                    )
                                )
                            ), holds = true
                        )
                    ), holds = false
                )
            )
        )
        expectForReporterWithoutAnsi(
            reportable,
            """
            |verb : 1
            |(i) properties of the unknown Exception
            |    » message : "bla"
            |    » stacktrace :${" "}
            |      ⚬ test
            |      ⚬ lines
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            reportable,
            """
            |verb : 1
            |${i}properties of the unknown Exception
            |   » message : "bla"
            |   » stacktrace :${" "}
            |     ⚬ test
            |     ⚬ lines
            """.trimMargin()
        )
    }

    @Test
    fun debugGroup() {
        val builder = buildRootGroup(representation = Text("/usr/bin/noprogram")) {
            simpleProof(Text("to"), Text("exist")) { false }
            debugGroup(Text("properties of unexpected exception")) {
                row {
                    column(Text("message"))
                    column(Text("oho..."))
                }
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : /usr/bin/noprogram
            |(f) to : exist
            |(d) properties of unexpected exception :${" "}
            |    ⚬ message : oho...
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb : /usr/bin/noprogram
           |$x  to  : exist
            |$d properties of unexpected exception :${" "}
            |   ⚬ message : oho...
            """.trimMargin()
        )
    }

    @Test
    fun usageHintGroup() {
        val reportable = Proof.rootGroup(
            Text("verb"), Text("/usr/bin/noprogram"),
            listOf(
                Proof.simple(Text("to"), Text("exist")) { false },
                Reportable.debugGroup(
                    Text("the closest existing parent directory is /usr/bin"), emptyList()
                )
            )
        )
        expectForReporterWithoutAnsi(
            reportable,
            """
            |verb   : /usr/bin/noprogram
            |(f) to : exist
            |(d) the closest existing parent directory is /usr/bin
            """.trimMargin()
        )
        expectForReporterWithoutAnsi(
            reportable,
            """
            |verb   : /usr/bin/noprogram
            |(f) to : exist
            |(d) the closest existing parent directory is /usr/bin
            """.trimMargin()
        )
    }

    @Test
    fun mergingColumnsWithAlignment() {
        val reportable = Reportable.group(
            Text("description always\n without line break"),
            "a string with new line\nas representation is broken\nmaxLength calculated correctly",
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
        //TODO 1.3.0 finish test
    }


    private fun buildRootGroup(
        verb: InlineElement = Text("a verb"),
        representation: Any = "representation",
        setup: EntryPointProofBuilder<*>.() -> Unit,
    ): Expect<*>.() -> Proof = {
        Proof.rootGroup(verb, representation, listOf(this._core.buildProof(setup)))
    }

    private fun expectForReporterWithoutAnsi(builder: Expect<*>.() -> Proof, expectedResult: String) =
        expectForReporterWithoutAnsi(expect(Unit).builder(), expectedResult)

    private fun expectForReporterWithoutAnsi(reportable: Reportable, expectedResult: String) {
        val reporter = reporterWithoutAnsi()
        expect(reporter.createReport(reportable).toString()).toEqual(expectedResult)
    }

    private fun reporterWithoutAnsi(): TextReporter {
        val reporter = componentFactoryContainer().merge(
            ComponentFactoryContainerImpl(
                mapOf(
                    Terminal::class to ComponentFactory({ _ ->
                        Terminal(ansiLevel = AnsiLevel.NONE)
                    }, producesSingleton = false)
                ),
                emptyMap()
            )
        ).build<TextReporter>()
        return reporter
    }

    private fun expectForReporterWithAnsi(builder: Expect<*>.() -> Proof, expectedResult: String) =
        expectForReporterWithAnsi(expect(Unit).builder(), expectedResult)

    private fun expectForReporterWithAnsi(reportable: Reportable, expectedResult: String) {
        val reporter = componentFactoryContainer().build<TextReporter>()
        val subject = reporter.createReport(reportable).toString()
        expect(subject).toEqual(expectedResult)
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
        strings.map { it.noStyle(noLineBreak = false) },
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
