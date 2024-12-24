package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderController
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.ReportableGroup

@ExperimentalNewExpectTypes
@OptIn(ExperimentalComponentFactoryContainer::class)
internal class RootExpectImpl<T>(
    maybeSubject: Option<T>,
    private val expectationVerb: InlineElement,
    private val representation: Any?,
    override val components: ComponentFactoryContainer
) : BaseExpectImpl<T>(maybeSubject), RootExpect<T> {

    constructor(
        maybeSubject: Option<T>,
        expectationVerb: InlineElement,
        options: RootExpectOptions<T>?
    ) : this(
        maybeSubject,
        options?.expectationVerb ?: expectationVerb,
        determineRepresentation(
            options?.representationInsteadOfSubject,
            maybeSubject
        ),
        DefaultComponentFactoryContainer.merge(options?.componentFactoryContainer)
    )

    constructor(previous: RootExpectImpl<T>, options: RootExpectOptions<T>) : this(
        previous.maybeSubject,
        previous.expectationVerb,
        options.copy(
            representationInsteadOfSubject = options.representationInsteadOfSubject
                ?: previous.representation?.let { r ->
                    val provider: (T) -> Any = { _ -> r }
                    provider
                },
            componentFactoryContainer = previous.components.merge(options.componentFactoryContainer)
        )
    )

    /**
     * All made assertions so far.
     * This list is intentionally not thread-safe, this class is not intended for multi-thread usage.
     */
    private val proofs: MutableList<Proof> = mutableListOf()

    @Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
    override fun append(assertion: ch.tutteli.atrium.assertions.Assertion): Expect<T> = append(assertion as Proof)

    override fun append(proof: Proof): Expect<T> {
        proofs.add(proof)
        if (!proof.holds()) {
            val rootProofGroup = Proof.rootGroup(expectationVerb, representation, proofs)

//            val a = components.buildOrNull(TextPreRenderController::class)!!.transformRoot(rootProofGroup)
//            println(a)
//
//            fun printProof(r: Reportable, level: Int) {
//                when (r) {
//                    is AssertionGroup -> {
//                        println("  ".repeat(level) + "$level." + r::class.simpleName + " type=${r.type}, description=${r.description}, representation=${r.representation}, holds=${r.holds()} children:")
//                        r.children.forEach {
//                            printProof(it, level + 1)
//                        }
//                    }
//
//                    is ReportableGroup -> {
//                        println("  ".repeat(level) + "$level." + r::class.simpleName + " children:")
//                        r.children.forEach {
//                            printProof(it, level + 1)
//                        }
//                    }
//
//                    else -> println("  ".repeat(level) + "$level." + r::class.simpleName +" "+ r)
//                }
//            }
//            printProof(rootProofGroup, 0)

            val sb = components.build<Reporter>().createReport(rootProofGroup)

            throw AtriumError.create(sb.toString(), rootProofGroup, components.build<AtriumErrorAdjuster>())
        }
        return this
    }


}
