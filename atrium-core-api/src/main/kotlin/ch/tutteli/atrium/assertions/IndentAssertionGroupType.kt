package ch.tutteli.atrium.assertions

/**
 * Represents the [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions], starting
 * from [indentIndex] should be displayed with an extra indent.
 */
interface IIndentAssertionGroupType : IAssertionGroupType {
    /**
     * Defines from which index on the [IAssertionGroup.assertions] should be indented.
     *
     * For instance, if [indentIndex] is 2 then the third (because the index starts from 0) and all subsequent assertions should be indented.
     */
    val indentIndex: Int
}

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s whose assertions should be displayed with an extra indent.
 * @constructor Use the factory method [createWithIndentIndex] to create an instance of this class.
 */
class IndentAssertionGroupType private constructor(override val indentIndex: Int) : IIndentAssertionGroupType {
    companion object {
        /**
         * Creates an [IndentAssertionGroupType] with the given [indentIndex].
         *
         * @param indentIndex Defines from which index on the [IAssertionGroup.assertions] should be indented.
         *
         * @return The newly created [IndentAssertionGroupType].
         */
        fun createWithIndentIndex(indentIndex: Int) = IndentAssertionGroupType(indentIndex)
    }
}
