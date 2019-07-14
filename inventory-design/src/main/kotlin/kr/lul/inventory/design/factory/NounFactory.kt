package kr.lul.inventory.design.factory

import kr.lul.inventory.design.domain.Noun

/**
 * @author justburrow
 * @since 2019-07-08
 */
interface NounFactory {
    fun getInstance(key: String, label: String, labelCode: String): Noun
}
