package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.ITranslatable

/**
 * A default implementation for [IOneMessageAssertion].
 *
 * @constructor
 * @param description The [Message.description].
 * @param representation The [Message.representation].
 * @param check The check which determines [Message.holds].
 */
class OneMessageAssertion(description: ITranslatable, representation: Any, check: () -> Boolean) : IOneMessageAssertion {

    /**
     * @param description The [Message.description].
     * @param representation The [Message.representation].
     * @param holds The [Message.holds].
     */
    constructor(description: ITranslatable, representation: Any, holds: Boolean)
        : this(description, representation, { holds })

    override val message by lazy {
        Message(description, representation, check())
    }

    /**
     * Delegates to [message]'s [Message.toString].
     *
     * @return The result of [Message.toString].
     */
    override fun toString() = message.toString()
}
