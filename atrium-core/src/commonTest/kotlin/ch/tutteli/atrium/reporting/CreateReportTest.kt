package ch.tutteli.atrium.reporting

import ch.tutteli.atrium._core
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.impl.fixedClaimGroup.FixedClaimAssertionGroup
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.ComponentFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.creating.impl.ComponentFactoryContainerImpl
import ch.tutteli.atrium.creating.impl.DefaultComponentFactoryContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.EntryPointProofBuilder
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.*
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDocumentationUtil
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof.OCCURRED_EXCEPTION_STACKTRACE
import ch.tutteli.atrium.reporting.text.TextReporter
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.impl.StringLengthMonospaceLengthCalculator
import ch.tutteli.atrium.reporting.theming.text.noStyle
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.specs.lineSeparator
import ch.tutteli.atrium.translations.DescriptionBasic
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.test.Test

@OptIn(ExperimentalComponentFactoryContainer::class)
class CreateReportTest {
    private val g = TextColors.red("🚩\uFE0F")
    private val x = TextColors.red("🚫\uFE0F")
    private val f = TextColors.cyan("▶")
    private val i = TextStyle(TextColors.brightBlue, bold = true).invoke("ℹ\uFE0F")
    private val d = TextColors.blue("🔎\uFE0F")
    private val u = TextStyle(TextColors.yellow, bold = true).invoke("💡\uFE0F")
    private val bb = TextColors.red("❗❗")

    @Test
    fun text() {
        val reportable = Text("bla")
        val expectedResult = "bla"

        expectForReporterWithoutAnsi(reportable, expectedResult)
        expectForReporterWithAnsi(reportable, expectedResult)

        //TODO 1.3 also check how it looks like if utf8 is not supported but colour is?
    }

    @Test
    fun simpleProof() {
        val reportable = Proof.simple(Text("bla"), 1) { false }
        val expectedResult = "bla : 1"

        expectForReporterWithoutAnsi(reportable, expectedResult)
        expectForReporterWithAnsi(reportable, expectedResult)
    }

    @Test
    fun root_SimpleProofAndRepresentation() {
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
            |a verb      : "representation"
            |$x to equal : 1
            |◆ some text
            """.trimMargin()
        )
    }

    @Test
    fun root_twoSimpleProof_onlyOneFailing_calculatesColumnsOnlyBasedOnVisible() {
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
            |a verb      : "representation"
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
            |$g $f verb : 2
             |     $g $f name        : "Robert"
              |          $x to equal : "Peter"
            """.trimMargin()
        )
    }

    @Test
    fun twoFeatureGroup() {
        val builder = buildRootGroup(
            verb = Text("I expected subject"),
            representation = Text("Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.Person <1234789>)")
        ) {
            feature(Text("its.definedIn(FeatureExtractorSpec.kt:54)"), "Robert") {
                simpleProof(DescriptionCharSequenceProof.TO_START_WITH, "Pe") { false }
                simpleProof(DescriptionCharSequenceProof.TO_END_WITH, "er") { false }
            }
            feature(Text("its.definedIn(FeatureExtractorSpec.kt:60)"), "Stoll") {
                simpleProof(DescriptionAnyProof.TO_EQUAL, "Dummy") { false }
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
            |$g $f its.definedIn(FeatureExtractorSpec.kt:54) : "Robert"
             |     $x to start with                          : "Pe"
             |     $x to end with                            : "er"
            |$g $f its.definedIn(FeatureExtractorSpec.kt:60) : "Stoll"
             |     $x to equal                               : "Dummy"
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
            |a verb     : "representation"
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
            |$g at least one expectation defined : false
            |   $x to equal                      : true
            |      $u You forgot to create expectations in the expectationCreator-lambda
            |      $u Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
            """.trimMargin()
        )
    }


    @Test
    fun failureExplanationGroup_withSimpleProofFollowedByFeatureProofGroup() {
        val builder = buildRootGroup(verb = Text("my expectations"), representation = Text.EMPTY) {
            // searched example, does not exist in reality, but we want to be sure the simple proof is well aligned ...
            failureExplanationGroup(Text("following elements were found")) {
                row {
                    column(Text("bla"))
                    column(Text("1"))
                }
                simpleProof(Text("bli"), 2) { false }
            }
            // ... and the feature afterwards as well
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
            |(!) following elements were found :${" "}
            |    • bla : 1
            |    (f) bli                       : 2
            |(f) > verb : 2
            |      (f) > name         : "Robert"
            |            (f) to equal : "Peter"
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |my expectations :${" "}
            |$bb following elements were found :${" "}
            |    • bla : 1
            |    $x bli                        : 2
            |$g $f verb : 2
             |     $g $f name        : "Robert"
              |          $x to equal : "Peter"
            """.trimMargin()
        )
    }

    @Test
    fun informationGroup_withSimpleProofFollowedBySimpleProof() {
        val builder = buildRootGroup(representation = 9) {
            // searched example, does not exist in reality, but we want to be sure the simple proofs are well aligned
            informationGroup(Text("following elements were found")) {
                row {
                    column(Text("bla"))
                    column(Text("1"))
                }
                simpleProof(Text("bli"), 2) { false }
            }
            simpleProof(Text("to be greater than"), 10) { false }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb                 : 9
            |(i) following elements were found :${" "}
            |    • bla : 1
            |    (f) bli                       : 2
            |(f) to be greater than : 10
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb                : 9
            |$i following elements were found :${" "}
            |   • bla : 1
            |   $x bli                        : 2
            |$x to be greater than : 10
            """.trimMargin()
        )
    }


    @Test
    fun debugGroup_withSimpleProofFollowedBySimpleProof() {
        val builder = buildRootGroup(representation = 9) {
            // searched example, does not exist in reality, but we want to be sure the simple proofs are well aligned
            debugGroup(Text("following elements were found")) {
                row {
                    column(Text("bla"))
                    column(Text("1"))
                }
                simpleProof(Text("bli"), 2) { false }
            }
            simpleProof(Text("to be greater than"), 10) { false }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb                 : 9
            |(d) following elements were found :${" "}
            |    • bla : 1
            |    (f) bli                       : 2
            |(f) to be greater than : 10
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb                : 9
            |$d following elements were found :${" "}
            |   • bla : 1
            |   $x bli                        : 2
            |$x to be greater than : 10
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
                    column(Diagnostic.representation("oho..."))
                }
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : /usr/bin/noprogram
            |(f) to : exist
            |(d) properties of unexpected exception :${" "}
            |    • message : "oho..."
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb : /usr/bin/noprogram
            |$x to  : exist
            |$d properties of unexpected exception :${" "}
            |   • message : "oho..."
            """.trimMargin()
        )
    }

    @Test
    fun debugGroup_withReportableGroup() {
        val builder = buildRootGroup(representation = Text("/usr/bin/noprogram")) {
            simpleProof(Text("to"), Text("exist")) { false }
            debugGroup(Text("properties of unexpected exception")) {
                diagnosticGroup(OCCURRED_EXCEPTION_STACKTRACE, Text.EMPTY) {
                    text("com.example.MyClass:32:8")
                }
                diagnosticGroup(DescriptionThrowableProof.OCCURRED_EXCEPTION_CAUSE, IllegalStateException("oho..")) {
                    row {
                        column(Text("message"))
                        column(Diagnostic.representation("oho..."))
                    }
                }
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : /usr/bin/noprogram
            |(f) to : exist
            |(d) properties of unexpected exception :${" "}
            |    • stacktrace :${" "}
            |      • com.example.MyClass:32:8
            |    • cause : ${IllegalStateException::class.fullName}
            |      • message : "oho..."
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb : /usr/bin/noprogram
            |$x to  : exist
            |$d properties of unexpected exception :${" "}
            |   • stacktrace :${" "}
            |     • com.example.MyClass:32:8
            |   • cause : ${IllegalStateException::class.fullName}
            |     • message : "oho..."
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
            |$x to  : exist
            |   $u You forgot to create expectations in the expectationCreator-lambda
            |   $u Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
            """.trimMargin()
        )
    }

    @Test
    fun proofExplanation_simpleProof_showsRepresentation() {
        val builder = buildRootGroup {
            feature(Text("get(10)"), Text("❗❗ Index out of bounds")) {
                invisibleFailingProofGroup {
                    proofExplanation {
                        simpleProof(Text("to start with"), "Ro") { false }
                    }
                }
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : "representation"
            |(f) > get(10)         : ❗❗ Index out of bounds
            |      » to start with : "Ro"
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb : "representation"
            |$x $f get(10)         : ❗❗ Index out of bounds
             |     » to start with : "Ro"
            """.trimMargin()
        )
    }

    @Test
    fun proofExplanation_feature_doesNotShowRepresentation() {
        val builder = buildRootGroup {
            feature(Text("get(10)"), Text("❗❗ Index out of bounds")) {
                invisibleFailingProofGroup {
                    proofExplanation {
                        feature(Text("firstName"), Text("Cannot show representation")) {
                            simpleProof(Text("to start with"), "Ro") { false }
                        }
                    }
                }
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : "representation"
            |(f) > get(10) : ❗❗ Index out of bounds
            |      » > firstName       :${' '}
            |          • to start with : "Ro"
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb : "representation"
            |$x $f get(10) : ❗❗ Index out of bounds
             |     » $f firstName       :${' '}
              |         • to start with : "Ro"
            """.trimMargin()
        )
    }

    @Test
    fun proofExplanation_withFailureExplanation() {
        val builder = buildRootGroup {
            proofGroup(Text("not to contain"), Text.EMPTY) {
                proofGroup(Text("an element which needs"), Text.EMPTY) {
                    invisibleFailingProofGroup {
                        proofExplanation {
                            simpleProof(Text("to be greater than"), 2) { false }
                            failureExplanationGroup(Text("following elements were found")) {
                                row {
                                    column(Text("index 0"))
                                    column(Diagnostic.representation(4))
                                }
                                row {
                                    column(Text("index 2"))
                                    column(Diagnostic.representation(3))
                                }
                                row {
                                    column(Text("index 6"))
                                    column(Diagnostic.representation(10))
                                }
                            }
                        }
                    }
                }
                proofGroup(Text("an element which needs"), Text.EMPTY) {
                    invisibleFailingProofGroup {
                        proofExplanation {
                            simpleProof(Text("to be less than"), 1) { false }
                            simpleProof(Text("to be greater than"), -5) { false }
                            failureExplanationGroup(Text("following elements were found")) {
                                row {
                                    column(Text("index 3"))
                                    column(Diagnostic.representation(0))
                                }
                                row {
                                    column(Text("index 4"))
                                    column(Diagnostic.representation(-4))
                                }
                            }
                        }
                    }
                }
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : "representation"
            |(f) not to contain :${' '}
            |    (f) an element which needs :${' '}
            |        » to be greater than   : 2
            |        (!) following elements were found :${' '}
            |            • index 0 : 4
            |            • index 2 : 3
            |            • index 6 : 10
            |    (f) an element which needs :${' '}
            |        » to be less than      : 1
            |        » to be greater than   : -5
            |        (!) following elements were found :${' '}
            |            • index 3 : 0
            |            • index 4 : -4
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb : "representation"
            |$g not to contain :${' '}
            |   $x an element which needs :${' '}
            |      » to be greater than   : 2
            |      $bb following elements were found :${' '}
            |          • index 0 : 4
            |          • index 2 : 3
            |          • index 6 : 10
            |   $x an element which needs :${' '}
            |      » to be less than      : 1
            |      » to be greater than   : -5
            |      $bb following elements were found :${' '}
            |          • index 3 : 0
            |          • index 4 : -4
            """.trimMargin()
        )
    }

    @Test
    fun proofExplanation_withFailureExplanationAndSubProofs_showsOnlyFailingProofs() {
        val builder = buildRootGroup {
            proofGroup(Text("elements need all"), Text.EMPTY) {
                invisibleFailingProofGroup {
                    proofExplanation {
                        feature(Text("login"), Text("should not be shown")) {
                            feature(Text("length"), Text("should not be shown")) {
                                simpleProof(Text("to be greater than"), 1) { false }
                            }
                        }
                        feature(Text("password"), Text("should not be shown")) {
                            simpleProof(Text("not to equal"), "qwerty") { false }
                            row(icon = Icon.INFORMATION_SOURCE, includingBorder = false) {
                                column(Text("because"))
                                column(Text.SPACE)
                                column(Text("password should be secure"))
                            }
                        }
                        failureExplanationGroup(Text("following elements were mismatched")) {
                            proofGroup(Text("index 0"), Text("User(login=joe, password=qwerty)")) {
                                feature(Text("password"), "qwerty") {
                                    simpleProof(Text("not to equal"), "qwerty") { false }
                                    row(icon = Icon.INFORMATION_SOURCE, includingBorder = false) {
                                        column(Text("because"))
                                        column(Text.SPACE)
                                        column(Text("password should be secure"))
                                    }
                                }
                                feature(Text("login"), "q") {
                                    feature(Text("length"), Text("should not be shown")) {
                                        simpleProof(Text("to be greater than"), 1) { true }
                                    }
                                }
                            }
                            proofGroup(Text("index 1"), Text("User(login=q, password=qwerty1)")) {
                                feature(Text("password"), "qwerty") {
                                    simpleProof(Text("not to equal"), "qwerty") { true }
                                    row(icon = Icon.INFORMATION_SOURCE, includingBorder = false) {
                                        column(Text("because"))
                                        column(Text.SPACE)
                                        column(Text("password should be secure"))
                                    }
                                }
                                feature(Text("login"), "q") {
                                    feature(Text("length"), Text("should not be shown")) {
                                        simpleProof(Text("to be greater than"), 1) { false }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : "representation"
            |(f) elements need all :${' '}
            |    » > login :${' '}
            |        • > length               :${' '}
            |            • to be greater than : 1
            |    » > password       :${' '}
            |        • not to equal : "qwerty"
            |        (i) because password should be secure
            |    (!) following elements were mismatched :${' '}
            |        • index 0 : User(login=joe, password=qwerty)
            |          (f) > password         : "qwerty"
            |                (f) not to equal : "qwerty"
            |                (i) because password should be secure
            |        • index 1 : User(login=q, password=qwerty1)
            |          (f) > login : "q"
            |                (f) > length                 : should not be shown
            |                      (f) to be greater than : 1
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            builder,
            """
            |a verb : "representation"
            |$g elements need all :${' '}
            |   » $f login :${' '}
             |       • $f length               :${' '}
             |           • to be greater than : 1
            |   » $f password       :${' '}
             |       • not to equal : "qwerty"
             |       $i because password should be secure
            |   $bb following elements were mismatched :${' '}
            |       • index 0 : User(login=joe, password=qwerty)
            |         $g $f password        : "qwerty"
             |              $x not to equal : "qwerty"
             |              $i because password should be secure
            |       • index 1 : User(login=q, password=qwerty1)
            |         $g $f login : "q"
             |              $g $f length                : should not be shown
              |                   $x to be greater than : 1
            """.trimMargin()
        )
    }

    @Test
    fun row_withIconAndWithoutBorder() {
        val builder = buildRootGroup {
            simpleProof(Text("to equal"), 0) { false }
            row(icon = Icon.INFORMATION_SOURCE, includingBorder = false) {
                column(DescriptionDocumentationUtil.BECAUSE)
                column(Text.SPACE)
                column(Text("? is not allowed in file names on Windows"))
            }
        }

        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb       : "representation"
            |(f) to equal : 0
            |(i) because ? is not allowed in file names on Windows
            """.trimMargin()
        )

        expectForReporterWithAnsi(
            builder,
            """
            |a verb      : "representation"
            |$x to equal : 0
           |$i because ? is not allowed in file names on Windows
            """.trimMargin()
        )
    }

    @Test
    fun row_withIconAndBorder() {
        val builder = buildRootGroup {
            simpleProof(Text("to equal"), 0) { false }
            row(icon = Icon.INFORMATION_SOURCE) {
                column(Text("whatever info"))
                column(Text("? is not allowed in file names on Windows"))
            }
        }

        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb       : "representation"
            |(f) to equal : 0
            |(i) whatever info : ? is not allowed in file names on Windows
            """.trimMargin()
        )

        expectForReporterWithAnsi(
            builder,
            """
            |a verb      : "representation"
            |$x to equal : 0
           |$i whatever info : ? is not allowed in file names on Windows
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
            |$g $f firstname   : "Robert"
             |     $x to equal : 1
            |$g $f lastname              : "Stoll"
             |     $x to be greater than : 2
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
            |$x to equal                           : 3
            |$x to be less than                    : 3
            |$x to be greater than                 : 10
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
            |$x  test                       : 1
            |(i) first column               : second longer than longest line of representation : 1
            """.trimMargin()
        )
    }

    @Test
    fun representationWithNewLineAndAnsiColours_isWrappedAndIndentedCorrectly() {
        val representation = """
        I expected subject : 1
        [31m🚫️[39m at least one expectation defined : false
           [31m🚫️[39m to equal                       : true
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
            |                        $x to equal                       : true
            |                        $u You forgot to create expectations in the expectationCreator-lambda
            |                        $u Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
            |                     ""${'"'}
            |      (f) to equal : "a"
            """.trimMargin()
        )

        expectForReporterWithAnsi(
            builder,
            """
            |a verb : "representation"
            |$g $f to throw    : ""${'"'}
             |                   I expected subject : 1
             |                   $x at least one expectation defined : false
             |                      $x to equal                       : true
             |                      $u You forgot to create expectations in the expectationCreator-lambda
             |                      $u Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
             |                   ""${'"'}
             |     $x to equal : "a"
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
            |$g $f to throw : 1
             |     $g $f message     : ""${'"'}
             |                        line
             |                        another line
             |                        ""${'"'}
              |          $x to equal : "a"
            """.trimMargin()
        )
    }

    @Test
    fun mergingColumnsWithAlignment() {
        Diagnostic.group(
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
                                    ch.tutteli.atrium.translations.DescriptionThrowableExpectation.OCCURRED_EXCEPTION_MESSAGE,
                                    "bla"
                                ) { true },
                                BasicAssertionGroup(
                                    DefaultListAssertionGroupType,
                                    ch.tutteli.atrium.translations.DescriptionThrowableExpectation.OCCURRED_EXCEPTION_STACKTRACE,
                                    Text.EMPTY,
                                    listOf(
                                        BasicExplanatoryAssertion(Text("test")),
                                        BasicExplanatoryAssertion(Text("lines"))
                                    )
                                ),
                                BasicAssertionGroup(
                                    DefaultListAssertionGroupType,
                                    ch.tutteli.atrium.translations.DescriptionThrowableExpectation.OCCURRED_EXCEPTION_CAUSE,
                                    IllegalStateException("oho.. error occurred"),
                                    listOf(
                                        ExplanatoryAssertionGroup(
                                            DefaultExplanatoryAssertionGroupType, listOf(
                                                BasicDescriptiveAssertion(
                                                    ch.tutteli.atrium.translations.DescriptionThrowableExpectation.OCCURRED_EXCEPTION_MESSAGE,
                                                    "oho.. error occurred"
                                                ) { true },
                                                BasicAssertionGroup(
                                                    DefaultListAssertionGroupType,
                                                    ch.tutteli.atrium.translations.DescriptionThrowableExpectation.OCCURRED_EXCEPTION_STACKTRACE,
                                                    Text.EMPTY,
                                                    listOf(
                                                        BasicExplanatoryAssertion(Text("some other line"))
                                                    )
                                                ),
                                            ), holds = true
                                        )
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
            |      • test
            |      • lines
            |    » cause : ${IllegalStateException::class.fullName}
            |      » message : "oho.. error occurred"
            |      » stacktrace :${" "}
            |        • some other line
            """.trimMargin()
        )
        expectForReporterWithAnsi(
            reportable,
            """
            |verb : 1
            |$i properties of the unknown Exception
            |   » message : "bla"
            |   » stacktrace :${" "}
            |     • test
            |     • lines
            |   » cause : ${IllegalStateException::class.fullName}
            |     » message : "oho.. error occurred"
            |     » stacktrace :${" "}
            |       • some other line
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
            |a verb      : "representation"
            |$x to equal : 0
           |$i because : ? is not allowed in file names on Windows
            """.trimMargin()
        )
    }

    @Suppress("DEPRECATION")
    @Test
    fun featureAssertionGroup() {
        val builder = buildRootGroup {
            add(
                BasicAssertionGroup(
                    DefaultFeatureAssertionGroupType,
                    ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES,
                    Text("3"),
                    listOf(
                        BasicDescriptiveAssertion(
                            ch.tutteli.atrium.translations.DescriptionAnyExpectation.TO_EQUAL,
                            0
                        ) { false }
                    ))
            )
        }

        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : "representation"
            |(f) > number of matches : 3
            |      (f) to equal      : 0
            """.trimMargin()
        )

        expectForReporterWithAnsi(
            builder,
            """
            |a verb : "representation"
            |$g $f number of matches : 3
             |     $x to equal       : 0
            """.trimMargin()
        )
    }

    @Suppress("DEPRECATION")
    @Test
    fun explanatoryGroupDirectlyNested() {

        val builder = buildRootGroup {
            add(
                FixedClaimAssertionGroup(
                    DefaultListAssertionGroupType,
                    DescriptionBasic.TO,
                    object : StringBasedTranslatable {
                        override val value: String = "exist"
                        override val name: String = "EXIST"
                    },
                    listOf(
                        ExplanatoryAssertionGroup(
                            DefaultExplanatoryAssertionGroupType,
                            listOf(
                                ExplanatoryAssertionGroup(
                                    DefaultExplanatoryAssertionGroupType,
                                    listOf(
                                        BasicDescriptiveAssertion(
                                            Untranslatable("failure at parent path"),
                                            "C:\\Users\\RUNNER~1\\AppData\\Local\\Temp\\spek5297204625390679776\\startFile"
                                        ) {
                                            false
                                        },
                                        ExplanatoryAssertionGroup(
                                            DefaultExplanatoryAssertionGroupType,
                                            listOf(
                                                BasicExplanatoryAssertion(
                                                    TranslatableWithArgs(
                                                        Untranslatable("was %s instead of %s"),
                                                        Untranslatable("a file"),
                                                        Untranslatable("a directory")
                                                    )
                                                )
                                            ),
                                            true
                                        )
                                    ),
                                    true
                                )
                            ),
                            true
                        )
                    ),
                    false
                )
            )
        }
        expectForReporterWithoutAnsi(
            builder,
            """
            |a verb : "representation"
            |(f) to : exist
            |    » failure at parent path : "C:\Users\RUNNER~1\AppData\Local\Temp\spek5297204625390679776\startFile"
            |    » was a file instead of a directory
            """.trimMargin()
        )
        //TODO 1.3.0 should be $x and not $g
        expectForReporterWithAnsi(
            builder,
            """
            |a verb : "representation"
            |$g to : exist
            |   » failure at parent path : "C:\Users\RUNNER~1\AppData\Local\Temp\spek5297204625390679776\startFile"
            |   » was a file instead of a directory
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
        expect(reporter.createReport(reportable).toString()).toEqual(modifyLineEnding(expectedResult))
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
        expect(subject).toEqual(modifyLineEnding(expectedResult))
    }

    private fun modifyLineEnding(expectedResult: String) =
        if (lineSeparator == "\n") expectedResult else Regex("\n").replace(expectedResult, lineSeparator)

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
        strings.map { it.noStyle(StringLengthMonospaceLengthCalculator, noLineBreak = false) },
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
