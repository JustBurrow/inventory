package kr.lul.inventory.data.dao

import kr.lul.inventory.design.domain.Noun

/**
 * @author justburrow
 * @since 2019-08-04
 */
interface NounDao {
    fun <N : Noun> create(noun: N): N

    fun <N : Noun> read(id: Int): N?

    fun exists(key: String): Boolean
}
