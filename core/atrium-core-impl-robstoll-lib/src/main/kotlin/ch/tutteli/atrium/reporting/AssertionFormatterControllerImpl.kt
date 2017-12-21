package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import java.util.*

/**
 * Represents an [AssertionFormatterController] which does nothing special in addition
 * but just the job of the controller :)
 *
 * @see AssertionFormatterController
 */
class AssertionFormatterControllerImpl : AssertionFormatterController {
    private val assertionFormatters = ArrayDeque<AssertionFormatter>()

    override fun format(assertion: Assertion, methodObject: AssertionFormatterMethodObject) {
        if (noNeedToFormat(assertion, methodObject)) return

        val assertionFormatter = assertionFormatters
            .firstOrNull { it.canFormat(assertion) }
            ?: AssertionFormatterController.noSuitableAssertionFormatterFound(assertion)

        when (assertion) {
            is AssertionGroup -> formatGroup(assertion, assertionFormatter, methodObject)
            else -> assertionFormatter.format(assertion, methodObject)
        }
    }

    private fun noNeedToFormat(assertion: Assertion, methodObject: AssertionFormatterMethodObject): Boolean {
        //assertionFilter only applies if:
        // - we are not in an assertion group which should not be filtered (e.g. explanatory or summary group) and
        // - if the given assertion is not an explanatory assertion group either.
        return methodObject.isNotInDoNotFilterGroup()
            && !isExplanatoryAssertionGroup(assertion)
            && !methodObject.assertionFilter(assertion)
    }

    private fun formatGroup(assertionGroup: AssertionGroup, assertionFormatter: AssertionFormatter, methodObject: AssertionFormatterMethodObject) {
        assertionFormatter.formatGroup(assertionGroup, methodObject) { childMethodObject, formatAssertionInGroup ->
            assertionGroup.assertions
                .filter { !noNeedToFormat(it, childMethodObject) }
                .forEach(formatAssertionInGroup)
        }
    }

    override fun register(assertionFormatter: AssertionFormatter) {
        assertionFormatters.add(assertionFormatter)
    }
}
