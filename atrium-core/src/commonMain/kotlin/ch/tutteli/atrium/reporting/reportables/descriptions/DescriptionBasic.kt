package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains the [Description]s which are so basic that one does not want to use
 * a different wording in two different expectation functions.
 */
enum class DescriptionBasic(override val string: String) : Description {

    /** @since 1.3.0 (but was in DescriptionBasic of atrium-translations before) */
    TO_BE("to be"),

    /** @since 1.3.0 (but was in DescriptionBasic of atrium-translations before) */
    NOT_TO_BE("not to be"),

    /** @since 1.3.0 (but was since 0.18.0 in DescriptionBasic of atrium-translations before) */
    TO_HAVE("to have"),

    /** @since 1.3.0 (but was since 0.18.0 in DescriptionBasic of atrium-translations before) */
    NOT_TO_HAVE("not to have"),
}
