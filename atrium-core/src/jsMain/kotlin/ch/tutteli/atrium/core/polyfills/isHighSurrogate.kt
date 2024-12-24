package ch.tutteli.atrium.core.polyfills

const val SURROGATE_RANGE_START = '\uD800'
const val SURROGATE_RANGE_END_INCLUSIVE = '\uDBFF'

actual fun Char.isHighSurrogate(): Boolean =
    this >= SURROGATE_RANGE_START && this <= SURROGATE_RANGE_END_INCLUSIVE;

