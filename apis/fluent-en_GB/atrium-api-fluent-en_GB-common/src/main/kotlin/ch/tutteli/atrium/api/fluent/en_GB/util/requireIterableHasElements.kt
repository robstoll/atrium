package ch.tutteli.atrium.api.fluent.en_GB.util

fun requireIterableHasElement(iterable: Iterable<*>) =
    require(iterable.iterator().hasNext()) { "Iterable without elements is not allowed." }
