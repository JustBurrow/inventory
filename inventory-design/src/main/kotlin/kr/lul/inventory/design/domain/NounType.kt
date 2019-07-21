package kr.lul.inventory.design.domain

/**
 * @author justburrow
 * @since 2019-07-15
 */
enum class NounType(
        val description: String
) {
    IDENTIFIABLE("You can identify user's item each other."),
    COUNTABLE("You can not identify user's item each other."),
    LIMITED_IDENTIFIABLE("You can identify user's item each other and user can own limited number of items."),
    LIMITED_COUNTABLE("You can not identify user's item each other and user can own limited number of items.")
}
