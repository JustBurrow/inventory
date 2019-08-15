package kr.lul.inventory.design.domain

/**
 * @author justburrow
 * @since 2019-07-15
 */
enum class NounType(
        val label: String,
        val labelCode: String,
        val description: String
) {
    IDENTIFIABLE("IDENTIFIABLE", "inventory.labelcode.nountype.identifiable",
            "You can identify user's item each other."),
    COUNTABLE("COUNTABLE", "inventory.labelcode.nountype.countable",
            "You can not identify user's item each other."),
    LIMITED_IDENTIFIABLE("LIMITED IDENTIFIABLE", "inventory.labelcode.nountype.limited-identifiable",
            "You can identify user's item each other and user can own limited number of items."),
    LIMITED_COUNTABLE("LIMITED COUNTABLE", "inventory.labelcode.nountype.limited-countable",
            "You can not identify user's item each other and user can own limited number of items.")
}
