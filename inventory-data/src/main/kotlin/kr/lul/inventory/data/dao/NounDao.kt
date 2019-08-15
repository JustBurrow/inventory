package kr.lul.inventory.data.dao

import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Noun
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort

/**
 * @author justburrow
 * @since 2019-08-04
 */
interface NounDao {
    fun <N : Noun> create(noun: N): N

    fun <N : Noun> read(id: Int): N?

    fun exists(key: String): Boolean

    fun search(manager: Manager, page: Int, size: Int, sort: Sort): Page<Noun>
}
