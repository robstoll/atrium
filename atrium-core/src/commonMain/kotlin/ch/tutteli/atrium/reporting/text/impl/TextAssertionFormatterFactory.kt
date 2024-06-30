//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.text.TextAssertionFormatter

@Deprecated("Switch to Proof based reporting, will be removed with 2.0.0 at the latest")
interface TextAssertionFormatterFactory {
    fun build(assertionFormatterController: AssertionFormatterController): TextAssertionFormatter

    companion object {
        operator fun invoke(
            f: (AssertionFormatterController) -> TextAssertionFormatter
        ): TextAssertionFormatterFactory = DefaultTextAssertionFormatterFactory(f)
    }
}

@Deprecated("Switch to Proof based reporting, will be removed with 2.0.0 at the latest")
private class DefaultTextAssertionFormatterFactory(
    private val f: (AssertionFormatterController) -> TextAssertionFormatter
) : TextAssertionFormatterFactory {

    override fun build(assertionFormatterController: AssertionFormatterController): TextAssertionFormatter =
        f(assertionFormatterController)
}

