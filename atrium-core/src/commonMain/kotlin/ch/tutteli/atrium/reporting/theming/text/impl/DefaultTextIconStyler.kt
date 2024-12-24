package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.theming.text.*

internal class DefaultTextIconStyler(
    textStyler: TextStyler,
    utf8SupportDeterminer: Utf8SupportDeterminer,
) : TextIconStyler {
    private val icons: Map<Icon, StyledString> = run {
        val utf8IsSupported = utf8SupportDeterminer.isSupported

        fun styleIcon(
            utf8String: String,
            utf8Space: String,
            uft8MonospaceLength: Int,
            fallbackAsciiString: String,
            style: Style
        ): StyledString {
            // +1 as we are going to add one space
            val monospaceLength = (if (utf8IsSupported) uft8MonospaceLength else fallbackAsciiString.length + 1)
            return textStyler.styleWithPredefinedMonospaceLength(
                if (utf8IsSupported) utf8String else fallbackAsciiString,
                style.styleId,
                spaceToAppend = if(utf8IsSupported) utf8Space else " ",
                monospaceLength = monospaceLength,
                maxLineMonospaceLength = monospaceLength,
                horizontalAlignment = HorizontalAlignment.CENTRE
            )
        }


        mapOf(
            // \uFE0F means animate emoji according to terminal
            Icon.BULB to styleIcon(
                "💡\uFE0F", utf8Space = " ", uft8MonospaceLength = 2,
                "(u)",
                Style.USAGE_HINT
            ),
            Icon.BANGBANG to styleIcon(
                "❗❗", utf8Space = " ", uft8MonospaceLength = 4,
                "(!)",
                Style.FAILURE
            ),
            Icon.DEBUG_INFO to styleIcon("🔎\uFE0F",utf8Space = " ",  uft8MonospaceLength = 3, "(d)", Style.DEBUG_INFO),
            Icon.EMPTY_STRING to StyledString.EMPTY_STRING,
            Icon.FAILING_GROUP to styleIcon("🚩\uFE0F",utf8Space = " ",  uft8MonospaceLength = 3, "(f)", Style.FAILURE),
            Icon.FAILURE to styleIcon("🚫\uFE0F", utf8Space = " ", uft8MonospaceLength = 3, "(f)", Style.FAILURE),
            Icon.FEATURE to styleIcon("▶", utf8Space = " ", uft8MonospaceLength = 2, ">", Style.FEATURE),
            Icon.INFORMATION_SOURCE to styleIcon("ℹ\uFE0F", utf8Space = " ", uft8MonospaceLength = 3, "(i)", Style.INFORMATION_SOURCE),
            Icon.SUCCESS to styleIcon("✔", utf8Space = " ", uft8MonospaceLength = 2, "(s)", Style.SUCCESS),

            // bullet points
            Icon.GROUPING_BULLET_POINT to "# ".noStyle(
                monospaceLength = 2,
                horizontalAlignment = HorizontalAlignment.CENTRE
            ),
            Icon.ROOT_BULLET_POINT to "◆ ".noStyle(
                monospaceLength = 2,
                horizontalAlignment = HorizontalAlignment.CENTRE
            ),
            Icon.FEATURE_BULLET_POINT to "• ".noStyle(
                monospaceLength = 2,
                horizontalAlignment = HorizontalAlignment.CENTRE
            ),
            Icon.LIST_BULLET_POINT to "• ".noStyle(
                monospaceLength = 2,
                horizontalAlignment = HorizontalAlignment.CENTRE
            ),
            Icon.PROOF_EXPLANATION_BULLET_POINT to "» ".noStyle(
                monospaceLength = 2,
                horizontalAlignment = HorizontalAlignment.CENTRE
            ),
        )
    }

    override fun style(icon: Icon): StyledString =
        icons[icon] ?: throw IllegalArgumentException("unsupported icon: $icon")
}
