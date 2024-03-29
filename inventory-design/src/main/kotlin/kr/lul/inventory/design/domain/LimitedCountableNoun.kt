package kr.lul.inventory.design.domain

/**
 * @author justburrow
 * @since 2019-07-15
 */
interface LimitedCountableNoun : LimitedNoun, CountableNoun {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.domain.CountableNoun
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun getType(): NounType = NounType.LIMITED_COUNTABLE
//    fun getLimit(): Int
}
