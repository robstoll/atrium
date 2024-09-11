package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.theming.text.*

class MarkdownTextIconStyler(utf8SupportDeterminer: Utf8SupportDeterminer) : TextIconStyler {
    private val icons = run {
        val utf8IsSupported = utf8SupportDeterminer.isSupported

        fun styleIcon(utf8String: String, fallbackAsciiString: String) =
            (if (utf8IsSupported) utf8String else fallbackAsciiString).let {
                "$it ".noStyle(horizontalAlignment = HorizontalAlignment.CENTRE)
            }

        mapOf(
            // \uFE0F means animate emoji according to terminal
            //TODO 1.3.0 animated emojis look nicer but they don't have a mono width which makes alignment sometimes
            // a bit ugly - keep or use only the text variation (which also means we can choose the colour as we like)
            Icon.BULB to styleIcon("💡\uFE0F", "(u)"),
            Icon.BANGBANG to styleIcon("❗❗", "(!)"),
            Icon.DEBUG_INFO to styleIcon("🔎", "(d)"),
            Icon.EMPTY_STRING to StyledString.EMPTY_STRING,
            Icon.FAILURE to styleIcon("✘", "(f)"),
            Icon.FEATURE to styleIcon("▶", ">"),
            Icon.INFORMATION_SOURCE to styleIcon("ℹ\uFE0F", "(i)"),
            Icon.SUCCESS to styleIcon("✔", "(s)"),

            // bullet points
            Icon.GROUPING_BULLET_POINT to "# ".noStyle(),
            Icon.ROOT_BULLET_POINT to "◆ ".noStyle(),
            Icon.FEATURE_BULLET_POINT to "- ".noStyle(),
            Icon.LIST_BULLET_POINT to "⚬ ".noStyle(),
            Icon.PROOF_EXPLANATION_BULLET_POINT to "» ".noStyle(),
        )
    }

    override fun style(icon: Icon): StyledString =
        icons[icon] ?: throw IllegalArgumentException("unsupported icon: $icon")
}
