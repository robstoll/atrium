package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * An [ISimpleTranslatable] with locale [Locale.ENGLISH].
 */
interface IEnTranslatable : ISimpleTranslatable {
    override val locale : Locale get() = Locale.ENGLISH
}
