package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.theming.text.*

class MarkdownTextIconStyler : TextIconStyler {
    private val icons = run {

        fun styleIcon(fallbackAsciiString: String, monospaceLength: Int) =
            "$fallbackAsciiString ".noStyle(monospaceLength + 1, horizontalAlignment = HorizontalAlignment.CENTRE)


        mapOf(
            // \uFE0F means animate emoji according to terminal
            //TODO 1.3.0 animated emojis look nicer but they don't have a mono width which makes alignment sometimes
            // a bit ugly - keep or use only the text variation
            Icon.BULB to styleIcon("💡\uFE0F", 1),
            Icon.BANGBANG to styleIcon("❗❗", 3),
            Icon.DEBUG_INFO to styleIcon("🔎\uFE0F", 2),
            Icon.EMPTY_STRING to StyledString.EMPTY_STRING,
            Icon.FAILING_GROUP to styleIcon("🚩\uFE0F", 2),
            Icon.FAILURE to styleIcon("🚫\uFE0F", 2),
            Icon.FEATURE to styleIcon("▶", 1),
            Icon.INFORMATION_SOURCE to styleIcon("ℹ\uFE0F", 1),
            Icon.SUCCESS to styleIcon("✔", 1),

            // bullet points
            Icon.GROUPING_BULLET_POINT to styleIcon("#", 1),
            Icon.ROOT_BULLET_POINT to styleIcon("◆", 1),
            Icon.LIST_BULLET_POINT to styleIcon("•", 1),
            Icon.PROOF_EXPLANATION_BULLET_POINT to styleIcon("»", 1),
        )
    }

    override fun style(icon: Icon): StyledString =
        icons[icon] ?: throw IllegalArgumentException("unsupported icon: $icon")
}
