package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import java.util.*

/**
 * Represents an [IAssertionFormatterController] which does nothing special in addition
 * but just the job of the controller :)
 *
 * @see IAssertionFormatterController
 */
class AssertionFormatterController : IAssertionFormatterController {
    private val assertionFormatters = ArrayDeque<IAssertionFormatter>()

    override fun format(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) {
        if (noNeedToFormat(assertion, methodObject)) return

        val assertionFormatter = assertionFormatters
            .firstOrNull { it.canFormat(assertion) }
            ?: IAssertionFormatterController.noSuitableAssertionFormatterFound(assertion)

        when (assertion) {
            is IAssertionGroup -> formatGroup(assertion, assertionFormatter, methodObject)
            else -> assertionFormatter.format(assertion, methodObject)
        }
    }

    private fun noNeedToFormat(assertion: IAssertion, methodObject: AssertionFormatterMethodObject): Boolean {
        //assertionFilter only applies if:
        // - we are not in an explanatory assertion group and
        // - if the given assertion isn't one either.
        return methodObject.isNotInDoNotFilterGroup()
            && !isExplanatoryAssertionGroup(assertion)
            && !methodObject.assertionFilter(assertion)
    }

    private fun formatGroup(assertionGroup: IAssertionGroup, assertionFormatter: IAssertionFormatter, methodObject: AssertionFormatterMethodObject) {
        assertionFormatter.formatGroup(assertionGroup, methodObject) { childMethodObject, formatAssertionInGroup ->
            assertionGroup.assertions
                .filter { !noNeedToFormat(it, childMethodObject) }
                .forEach(formatAssertionInGroup)
        }
    }

    override fun register(assertionFormatter: IAssertionFormatter) {
        assertionFormatters.add(assertionFormatter)
    }
}
