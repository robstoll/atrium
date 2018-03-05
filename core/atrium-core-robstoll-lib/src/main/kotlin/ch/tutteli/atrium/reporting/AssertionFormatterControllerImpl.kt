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

    override fun format(assertion: Assertion, parameterObject: AssertionFormatterParameterObject) {
        if (noNeedToFormat(assertion, parameterObject)) return

        val assertionFormatter = assertionFormatters
            .firstOrNull { it.canFormat(assertion) }
            ?: AssertionFormatterController.noSuitableAssertionFormatterFound(assertion)

        when (assertion) {
            is AssertionGroup -> formatGroup(assertion, assertionFormatter, parameterObject)
            else -> assertionFormatter.format(assertion, parameterObject)
        }
    }

    private fun noNeedToFormat(assertion: Assertion, parameterObject: AssertionFormatterParameterObject): Boolean {
        //assertionFilter only applies if:
        // - we are not in an assertion group which should not be filtered (e.g. explanatory or summary group) and
        // - if the given assertion is not an explanatory assertion group either.
        return parameterObject.isNotInDoNotFilterGroup()
            && !isExplanatoryAssertionGroup(assertion)
            && !parameterObject.assertionFilter(assertion)
    }

    private fun formatGroup(assertionGroup: AssertionGroup, assertionFormatter: AssertionFormatter, parameterObject: AssertionFormatterParameterObject) {
        assertionFormatter.formatGroup(assertionGroup, parameterObject) { childParameterObject, formatAssertionInGroup ->
            assertionGroup.assertions
                .filter { !noNeedToFormat(it, childParameterObject) }
                .forEach(formatAssertionInGroup)
        }
    }

    override fun register(assertionFormatter: AssertionFormatter) {
        assertionFormatters.add(assertionFormatter)
    }
}
