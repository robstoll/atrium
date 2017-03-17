package ch.tutteli.assertk.creating

interface IAssertionPlantNullable<out T : Any?> : IAssertionPlantWithCommonFields<T> {
    fun isNull()
}
