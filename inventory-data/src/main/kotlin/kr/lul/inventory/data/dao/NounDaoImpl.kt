package kr.lul.inventory.data.dao

import kr.lul.inventory.data.jpa.entity.NounEntity
import kr.lul.inventory.data.jpa.repository.NounRepository
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.util.Assertion.`is`
import kr.lul.inventory.design.util.Assertion.zero
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
internal class NounDaoImpl : NounDao {
    private val log = LoggerFactory.getLogger(NounDaoImpl::class.java)!!

    @Autowired
    private lateinit var nounRepository: NounRepository

    @PostConstruct
    fun postConstruct() {
        log.info("ready")
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.data.dao.NounDao
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(noun: Noun): Noun {
        if (log.isTraceEnabled) log.trace("args : noun={}", noun)

        zero(noun.getId(), Noun.ATTR_ID)
        `is`(noun, NounEntity::class, "noun")

        val created = nounRepository.save(noun as NounEntity)

        if (log.isTraceEnabled) log.trace("return : {}", created)
        return created
    }
}
