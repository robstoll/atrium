package ch.tutteli.atrium.reporting

import ch.tutteli.atrium._core
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.creating.changeSubject
import ch.tutteli.atrium.creating.impl.DefaultComponentFactoryContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.Reportable
import kotlin.test.Test

@OptIn(ExperimentalComponentFactoryContainer::class)
class ReporterTest {

    @Test
    fun createReport_Text() {
        val reporter = DefaultComponentFactoryContainer.build<TextReporter>()


        expect(reporter.createReport(Text("bla"))).asString().toEqual(
            """
            |bla
            """.trimMargin()
        )
    }

    @Test
    fun createReport_SimpleProof() {
        val reporter = DefaultComponentFactoryContainer.build<TextReporter>()

        expect(reporter.createReport(Proof.simple(Text("bla"), 1) { false })).asString().toEqual(
            """
            |bla : 1
            """.trimMargin()
        )
    }

    @Test
    fun createReport_RootProofGroupWithSimpleProofAndRepresentation() {
        val reporter = DefaultComponentFactoryContainer.build<TextReporter>()

        expect(
            reporter.createReport(
                Proof.rootGroup(
                    Text("a verb"), "representation",
                    children = listOf(
                        Proof.simple(Text("bla"), 1) { false },
                        Text("some text")
                    )
                )
            )
        ).asString().toEqual(
            """
            |a verb : "representation"
            |✘ bla  : 1
            |
            """.trimMargin()
        )
    }

}

//TODO 1.4.0 also add to atrium
fun <T : StringBuilder> Expect<T>.asString() = _core.changeSubject.unreported { it.toString() }
