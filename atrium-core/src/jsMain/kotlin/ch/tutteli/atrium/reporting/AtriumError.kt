package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.stackBacktrace

/**
 * Indicates that an assertion made by Atrium failed.
 *
 * Its `stack` might be filtered so that reporting does not include all stack frames.
 * This depends on the chosen [AtriumErrorAdjuster] - so theoretically more than the stack trace
 * could be adjusted.
 *
 * As side notice, `stack` is a property of Error which is currently not visible in Kotlin.
 *
 * To create such an error you need to use the [AtriumError.Companion.create][Companion.create] function.
 */
actual class AtriumError private constructor(
    private val internalMessage: String,
    actual val rootAssertion: Assertion,
    @Suppress("UNUSED_PARAMETER") dummyUnit: Unit
) : AssertionError(internalMessage) {

    internal actual constructor(message: String, rootAssertion: Assertion) : this(message, rootAssertion, Unit)



    override val message: String?
        get() {
            // workaround for intellij's double print exception message problem. Intellij is outputting the message once
            // as message and once as part of the stack-trace (no idea why they do it) -- see also
            val process = js("process.env._") as? String
            return process?.takeIf {
                // if there is a process.env and it contains idea, then we assume we deal with intellij-idea
                it.contains("idea")
            }?.takeIf {
                // Currently, kotlin calls `message` twice, once when populating the stacktrace (in this case the
                // first stacktrace does not contain AtriumError) and once when reporting the failure (here AtriumError
                // is in the first stacktrace). To work around the bug we return an empty string for the `message`
                // and instead print it when intellij most likely wants to report the failure
                // (with the benefit that we get coloured code).
                //
                // we tried to sneak the message into the stack (as it is a String) instead of printing it but it looks
                // like intellij-idea does a post-processing of the stack and double prints it again *sight*
                // in any case, the added benefit of a coloured output seems worth this hack.
                val stack = Error().stackBacktrace
                stack.first().startsWith("AtriumError") && stack.any { it.startsWith("Runner.fail") }
            }?.let {
                println("\n" + internalMessage)
                ""
            } ?: run {
                internalMessage
            }
        }

    actual companion object {
        /**
         * Creates an [AtriumError] and adjusts it with the given [atriumErrorAdjuster] before it is returned
         * (this might filter the `stack`).
         *
         * As side notice, `stack` is a property of Error which is currently not visible in Kotlin.
         *
         * @return The newly created [AtriumError]
         */
        actual fun create(
            message: String,
            rootAssertion: Assertion,
            atriumErrorAdjuster: AtriumErrorAdjuster
        ): AtriumError = createAtriumError(message, rootAssertion, atriumErrorAdjuster)
    }
}
