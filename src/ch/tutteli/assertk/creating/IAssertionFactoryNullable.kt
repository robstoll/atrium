package ch.tutteli.assertk.creating

interface IAssertionFactoryNullable<out T : Any?> : IAssertionFactoryBase<T> {
    fun isNull()
}
