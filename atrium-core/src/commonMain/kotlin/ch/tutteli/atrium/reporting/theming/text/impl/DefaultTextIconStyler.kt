package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.theming.text.*

internal class DefaultTextIconStyler(textStyler: TextStyler, utf8SupportDeterminer: Utf8SupportDeterminer) : TextIconStyler {
    private val icons = run {
        val utf8IsSupported = utf8SupportDeterminer.isSupported

        fun styleIcon(utf8String: String, utf8Space: String, fallbackAsciiString: String, style: Style) =
            textStyler.style(
                if (utf8IsSupported) utf8String else fallbackAsciiString,
                style,
                horizontalAlignment = HorizontalAlignment.CENTRE
            ).let {
                if (utf8IsSupported) it.appendUnstyled(utf8Space) else it
            }


        mapOf(
            // \uFE0F means animate emoji according to terminal
            //TODO 1.3.0 animated emojis look nicer but they don't have a mono width which makes alignment sometimes
            // a bit ugly - keep or use only the text variation (which also means we can choose the colour as we like)
            Icon.BULB to styleIcon("💡\uFE0F", " ", "(u) ", Style.USAGE_HINT),
            Icon.BANGBANG to styleIcon("❗❗", " ", "(!) ", Style.FAILURE),
            Icon.DEBUG_INFO to styleIcon("🔎", " ", "(d) ", Style.DEBUG_INFO),
            Icon.EMPTY_STRING to StyledString.EMPTY_STRING,
            Icon.FAILURE to styleIcon("✘", " ", "(f) ", Style.FAILURE),
            Icon.FEATURE to styleIcon("▶", " ", "> ", Style.FEATURE),
            Icon.INFORMATION_SOURCE to styleIcon("ℹ\uFE0F", " ", "(i) ", Style.INFORMATION_SOURCE),
            Icon.SUCCESS to styleIcon("✔", " ", "(s)", Style.SUCCESS),

            // bullet points
            Icon.GROUPING_BULLET_POINT to "# ".noStyle( horizontalAlignment = HorizontalAlignment.CENTRE),
            Icon.ROOT_BULLET_POINT to "◆ ".noStyle( horizontalAlignment = HorizontalAlignment.CENTRE),
            Icon.FEATURE_BULLET_POINT to "• ".noStyle( horizontalAlignment = HorizontalAlignment.CENTRE),
            Icon.LIST_BULLET_POINT to "• ".noStyle( horizontalAlignment = HorizontalAlignment.CENTRE),
            Icon.PROOF_EXPLANATION_BULLET_POINT to "» ".noStyle( horizontalAlignment = HorizontalAlignment.CENTRE),
        )
    }

    override fun style(icon: Icon): StyledString =
        icons[icon] ?: throw IllegalArgumentException("unsupported icon: $icon")
}
