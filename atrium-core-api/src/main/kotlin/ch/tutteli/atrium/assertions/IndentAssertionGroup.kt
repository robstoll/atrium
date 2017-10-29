package ch.tutteli.atrium.assertions

/**
 * Represents an [IAssertionGroup] with an [IIndentAssertionGroupType], which means the [assertions] starting by the
 * index defined by [IIndentAssertionGroupType.indentIndex] shall be indented one extra level.
 *
 * @constructor Use the factory method [createWithIndentIndex] to create an instance of this class.
 * @param assertions The assertions of this group.
 * @param indentIndex The index from which on the assertions are indented an extra level.
 */
class IndentAssertionGroup private constructor(override val assertions: List<IAssertion>, indentIndex: Int)
    : EmptyNameAndSubjectAssertionGroup(IndentAssertionGroupType.createWithIndentIndex(indentIndex), assertions) {

    companion object {
        /**
         * Creates an [IAssertionGroup] with an [IIndentAssertionGroupType], which means the [assertions] starting by the
         * index defined by [startIndent] shall be indented one extra level.
         * @param assertions The assertions of this group.
         * @param startIndent The index from which on the assertions are indented an extra level.
         *
         * @return The newly created [IAssertionGroup].
         */
        fun createWithIndentIndex(assertions: List<IAssertion>, startIndent: Int): IAssertionGroup
            = IndentAssertionGroup(assertions, startIndent)

        /**
         * Creates an [IAssertionGroup] with an [IIndentAssertionGroupType] and
         * [IIndentAssertionGroupType.indentIndex] = 1, using the given [explanatoryAssertionGroup] as first
         * [IAssertion] and thus the remaining [assertions] will be indented by one extra level.
         *
         * @param explanatoryAssertionGroup The assertion group which kind of serves as title
         *        of the indented [assertions].
         * @param assertions The assertions which are indented.
         *
         * @return The newly created [IAssertionGroup].
         */
        fun createWithExplanatoryAssertionGroup(explanatoryAssertionGroup: ExplanatoryAssertionGroup, vararg assertions: IAssertion): IAssertionGroup
            = IndentAssertionGroup(listOf(explanatoryAssertionGroup, *assertions), 1)
    }
}
