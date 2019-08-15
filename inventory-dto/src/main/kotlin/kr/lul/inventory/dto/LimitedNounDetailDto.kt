package kr.lul.inventory.dto

/**
 * @author justburrow
 * @since 2019-08-15
 */
interface LimitedNounDetailDto : NounDetailDto {
    val limit: Int
}
