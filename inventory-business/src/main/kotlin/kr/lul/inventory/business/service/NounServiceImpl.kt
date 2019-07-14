package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.CreateNounParams
import kr.lul.inventory.data.dao.NounDao
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.factory.NounFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author justburrow
 * @since 2019-07-08
 */
@Service
internal class NounServiceImpl : NounService {
    private val log = LoggerFactory.getLogger(NounServiceImpl::class.java)!!

    @Autowired
    private lateinit var nounFactory: NounFactory
    @Autowired
    private lateinit var nounDao: NounDao

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.service.NounService
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(params: CreateNounParams): Noun {
        if (log.isTraceEnabled) log.trace("args : params={}", params)

        var noun = nounFactory.getInstance(params.key, params.label, params.labelCode)
        noun = nounDao.create(noun)

        if (log.isTraceEnabled) log.trace("return : {}", noun)
        return noun
    }
}
