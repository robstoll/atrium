package ch.tutteli.atrium.reporting.translating

import java.util.*

object EmptyTranslationProvider : ITranslationProvider {
    override fun get() = emptyMap<Locale, Map<ITranslatable, String>>()
}
