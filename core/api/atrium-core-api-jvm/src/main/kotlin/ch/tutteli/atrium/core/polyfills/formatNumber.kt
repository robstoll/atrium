package ch.tutteli.atrium.core.polyfills

import java.text.DecimalFormat

actual fun formatNumber(number: Number): String {
    val df = DecimalFormat("###,##0.0")
    df.maximumFractionDigits = 340
    return df.format(number)
}
