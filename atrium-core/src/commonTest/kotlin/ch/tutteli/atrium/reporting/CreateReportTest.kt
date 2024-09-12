package ch.tutteli.atrium.reporting

import ch.tutteli.atrium._core
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.ComponentFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.creating.impl.ComponentFactoryContainerImpl
import ch.tutteli.atrium.creating.impl.DefaultComponentFactoryContainer
import ch.tutteli.atrium.creating.proofs.EntryPointProofBuilder
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.buildProof
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof
import ch.tutteli.atrium.reporting.reportables.descriptions.ErrorMessages
import ch.tutteli.atrium.reporting.text.TextReporter
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.noStyle
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionAnyExpectation
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
    private val u = TextStyle(TextColors.yellow, bold = true).invoke("💡\uFE0F")

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
            text("some text")
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
    fun twoFeatureGroup(){
        val builder = buildRootGroup(
            verb = Text("I expected subject"),
            representation = Text("Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.Person <1234789>)")
        ) {
            feature(Text("its.definedIn(FeatureExtractorSpec.kt:54)"), "Robert"){
                simpleProof(DescriptionCharSequenceProof.TO_START_WITH, "Pe"){ false }
                simpleProof(DescriptionCharSequenceProof.TO_END_WITH, "er"){ false }
            }
            feature(Text("its.definedIn(FeatureExtractorSpec.kt:60)"), "Stoll") {
                simpleProof(DescriptionAnyProof.TO_EQUAL, "Dummy"){ false }
            }
        }

        expectForReporterWithoutAnsi(
            builder,
            """
            |I expected subject : Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.Person <1234789>)
            |(f) > its.definedIn(FeatureExtractorSpec.kt:54) : "Robert"
            |      (f) to start with                         : "Pe"
            |      (f) to end with                           : "er"
            |(f) > its.definedIn(FeatureExtractorSpec.kt:60) : "Stoll"
            |      (f) to equal                              : "Dummy"
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |I expected subject : Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.Person <1234789>)
           |$x $f its.definedIn(FeatureExtractorSpec.kt:54) : "Robert"
            |    $x to start with                           : "Pe"
            |    $x to end with                             : "er"
           |$x $f its.definedIn(FeatureExtractorSpec.kt:60) : "Stoll"
            |    $x to equal                                : "Dummy"
            """.trimMargin()
        )
    }

    @Test
    fun invisibleGroup_childrenPrefixedAccordingToType_notJustAsFailure() {
        val builder = buildRootGroup {
            invisibleGroup {
                simpleProof(Text("simple"), 1) { false }
                text("text")
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
    fun fixedClaimGroup() {
        val builder = buildRootGroup {
            fixedClaimGroup(
                ErrorMessages.AT_LEAST_ONE_EXPECTATION_DEFINED,
                representation = false,
                holds = false
            ) {
                simpleProof(DescriptionAnyProof.TO_EQUAL, true) { false }
                usageHintGroup {
                    addAll(
                        ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION,
                        ErrorMessages.DEFAULT_HINT_AT_LEAST_ONE_EXPECTATION_DEFINED
                    )
                }
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : "representation"
            |(f) at least one expectation defined : false
            |    (f) to equal                     : true
            |        (u) You forgot to create expectations in the expectationCreator-lambda
            |        (u) Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
            """.trimMargin()
        )

        expectForReporterWithAnsi(
            builder,
            """
            |a verb : "representation"
           |$x at least one expectation defined : false
           |  $x to equal                       : true
            |    $u You forgot to create expectations in the expectationCreator-lambda
            |    $u Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
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
        val builder = buildRootGroup(representation = Text("/usr/bin/noprogram")) {
            simpleProof(Text("to"), Text("exist")) { false }
            usageHintGroup {
                add(ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION)
                add(ErrorMessages.DEFAULT_HINT_AT_LEAST_ONE_EXPECTATION_DEFINED)
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : /usr/bin/noprogram
            |(f) to : exist
            |    (u) You forgot to create expectations in the expectationCreator-lambda
            |    (u) Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb : /usr/bin/noprogram
           |$x to   : exist
            |  $u You forgot to create expectations in the expectationCreator-lambda
            |  $u Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
            """.trimMargin()
        )
    }

    @Test
    fun veryShortVerb_withMultipleProofsDefiningOwnLevel() {
        // due to the short verb the merged 2 columns of the rootGroup is less in size than the prefix of the feature
        // this test ensures we don't use minus numbers for indent levels
        val builder = buildRootGroup(verb = Text("a"), 2) {
            feature(Text("firstname"), "Robert") {
                simpleProof(Text("to equal"), 1) { false }
            }
            feature(Text("lastname"), "Stoll") {
                simpleProof(Text("to be greater than"), 2) { false }
            }

        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a : 2
            |(f) > firstname    : "Robert"
            |      (f) to equal : 1
            |(f) > lastname               : "Stoll"
            |      (f) to be greater than : 2
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a : 2
           |$x $f firstname  : "Robert"
            |    $x to equal : 1
           |$x $f lastname             : "Stoll"
            |    $x to be greater than : 2
            """.trimMargin()
        )
    }

    @Test
    fun featureProofGroupWithMultilineDescription() {
        //TODO 1.3.0 use case method call with multiple arguments
    }


    @Test
    fun rootVerbLongerThanExpectations_alignmentCorrect() {
        val builder = buildRootGroup(verb = Text("I expected that the subject which was"), representation = 2) {
            simpleProof(Text("to equal"), 3) { false }
            simpleProof(Text("to be less than"), 3) { false }
            simpleProof(Text("to be greater than"), 10) { false }
        }

        expectForReporterWithoutAnsi(
            builder,
            """
            |I expected that the subject which was : 2
            |(f) to equal                          : 3
            |(f) to be less than                   : 3
            |(f) to be greater than                : 10
            """.trimMargin()
        )

        expectForReporterWithAnsi(
            builder,
            """
            |I expected that the subject which was : 2
           |$x to equal                            : 3
           |$x to be less than                     : 3
           |$x to be greater than                  : 10
            """.trimMargin()
        )

    }

    @Test
    fun representationWithNewLine_isWrappedAndIndentedCorrectly() {
        val builder = buildRootGroup(
            verb =
            Text("verb always\nwithout line break"),
            representation = "a string with new line\nas representation is wrapped\nmaxLength calculated correctly",
        ) {
            simpleProof(Text("test"), 1) { false }
            //TODO 1.3.0 use row{ } instead?
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

    @Test
    fun representationWithNewLineAndAnsiColours_isWrappedAndIndentedCorrectly() {
        val representation = """
        I expected subject : 1
        [31m✘[39m at least one expectation defined : false
          [31m✘[39m to equal                       : true
          [33;1m💡️[39;22m You forgot to create expectations in the expectationCreator-lambda
          [33;1m💡️[39;22m Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
        """.trimIndent()
        val builder = buildRootGroup {
            feature(Text("to throw"), representation = representation) {
                simpleProof(Text("to equal"), "a") { false }
            }
        }

        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : "representation"
            |(f) > to throw     : ""${'"'}
            |                     I expected subject : 1
            |                     $x at least one expectation defined : false
            |                       $x to equal                       : true
            |                       $u You forgot to create expectations in the expectationCreator-lambda
            |                       $u Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
            |                     ""${'"'}
            |      (f) to equal : "a"
            """.trimMargin()
        )

        expectForReporterWithAnsi(
            builder,
            """
            |a verb : "representation"
           |$x $f to throw   : ""${'"'}
            |                 I expected subject : 1
            |                 $x at least one expectation defined : false
            |                   $x to equal                       : true
            |                   $u You forgot to create expectations in the expectationCreator-lambda
            |                   $u Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
            |                 ""${'"'}
            |    $x to equal : "a"
            """.trimMargin()
        )
    }

    @Test
    fun representationWithNewLineInNestedFeature_isWrappedAndIndentedCorrectly() {
        val builder = buildRootGroup {
            feature(Text("to throw"), 1) {
                feature(Text("message"), "line\nanother line") {
                    simpleProof(Text("to equal"), "a") { false }
                }
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : "representation"
            |(f) > to throw : 1
            |      (f) > message      : ""${'"'}
            |                           line
            |                           another line
            |                           ""${'"'}
            |            (f) to equal : "a"
            """.trimMargin()
        )

        expectForReporterWithAnsi(
            builder,
            """
            |a verb : "representation"
           |$x $f to throw : 1
            |    $x $f message    : ""${'"'}
            |                     line
            |                     another line
            |                     ""${'"'}
            |        $x to equal : "a"
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


// --------------------------------------------------------------------------------------------------------------
// Backwards compatibility with Assertions, regression tests
// --------------------------------------------------------------------------------------------------------------

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

    @Suppress("DEPRECATION")
    @Test
    fun explanatoryAssertionGroupAsSecondChild() {
        val builder = buildRootGroup {
            simpleProof(DescriptionAnyProof.TO_EQUAL, 0) { false }
            add(
                ExplanatoryAssertionGroup(
                    InformationAssertionGroupType(withIndent = false),
                    listOf(
                        BasicDescriptiveAssertion(
                            ch.tutteli.atrium.translations.DescriptionAnyExpectation.BECAUSE,
                            Text("? is not allowed in file names on Windows")
                        ) { false }),
                    holds = true
                )
            )
        }

        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb       : "representation"
            |(f) to equal : 0
            |(i) because : ? is not allowed in file names on Windows
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb     : "representation"
           |$x to equal : 0
            |${i}because : ? is not allowed in file names on Windows
            """.trimMargin()
        )
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
            mapOf(
                Terminal::class to ComponentFactory({ _ ->
                    Terminal(ansiLevel = AnsiLevel.TRUECOLOR)
                }, producesSingleton = false)
            ),
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
    val additionalIndent: Int = 0,
) : Reportable {
    constructor(
        vararg strings: String,
        mergeColumns: Int = 0,
        definesOwnLevel: Boolean = false,
        usesOwnPrefix: Boolean = false,
        additionalIndent: Int = 0,
    ) : this(
        strings.map { it.noStyle(noLineBreak = false) },
        mergeColumns = mergeColumns,
        definesOwnLevel = definesOwnLevel,
        usesOwnPrefix = usesOwnPrefix,
        additionalIndent = additionalIndent
    )
}

class DefaultColumnsPreRenderer : TypedTextPreRenderer<Columns>(Columns::class) {
    override fun transformIt(reportable: Columns, controlObject: TextPreRenderControlObject): List<OutputNode> =
        listOf(
            OutputNode(
                columns = reportable.columns,
                children = emptyList(),
                definesOwnLevel = reportable.definesOwnLevel,
                mergeColumns = reportable.mergeColumns,
                usesOwnPrefix = reportable.usesOwnPrefix,
                indentLevel = reportable.additionalIndent
            )
        )
}
