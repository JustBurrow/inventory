package kr.lul.inventory.data.dao

import kr.lul.inventory.data.jpa.entity.AbstractNounEntity
import kr.lul.inventory.data.jpa.repository.NounRepository
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Noun
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
internal class NounDaoImpl : NounDao {
    private val log = LoggerFactory.getLogger(NounDaoImpl::class.java)!!

    @Autowired
    private lateinit var nounRepository: NounRepository

    @Suppress("UNCHECKED_CAST")
    override fun <N : Noun> create(noun: N): N {
        if (log.isTraceEnabled)
            log.trace("args : noun=$noun")

        val saved = nounRepository.saveAndFlush(noun as AbstractNounEntity) as N

        if (log.isTraceEnabled) log.trace("return : $saved")
        return saved
    }

    @Suppress("UNCHECKED_CAST")
    override fun <N : Noun> read(id: Int): N? {
        if (log.isTraceEnabled) log.trace("args : id=$id")

        val noun = nounRepository.findByIdOrNull(id) as N?

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }

    override fun exists(key: String): Boolean {
        if (log.isTraceEnabled)
            log.trace("args : key='$key'")

        val exists = nounRepository.existsByKey(key)

        if (log.isTraceEnabled) log.trace("return : $exists")
        return exists
    }

    override fun search(manager: Manager, page: Int, size: Int, sort: Sort): Page<Noun> {
        if (log.isTraceEnabled) log.trace("args : manager=${manager.simpleString}, page=$page, size=$size, sort=$sort")

        val page = PageRequest.of(page, size, sort)
        val list = nounRepository.findAllByManager(manager, page) as Page<Noun>

        if (log.isTraceEnabled) log.trace("return : $list")
        return list
    }
}
