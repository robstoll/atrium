package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [ITranslatable] which is not translatable but has only a fixed [name] which serves as [getDefault].
 * Its [Locale] is therefore [Locale.ROOT].
 *
 * Use this class to represent identifiers (method/property names etc.) and the like.
 */
class Untranslatable(override val name: String) : ITranslatable {
    override fun getDefault() = name
    override val locale: Locale = Locale.ROOT
}
