package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.*
import kr.lul.inventory.data.dao.NounDao
import kr.lul.inventory.design.domain.*
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_KEY
import kr.lul.inventory.design.factory.NounFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
internal class NounServiceImpl : NounService {
    private val log = LoggerFactory.getLogger(NounServiceImpl::class.java)!!

    @Autowired
    private lateinit var factory: NounFactory
    @Autowired
    private lateinit var dao: NounDao

    private fun validate(params: AbstractCreateNounParams) {
        if (dao.exists(params.key))
            throw InvalidAttributeException("used $ATTR_KEY", ATTR_KEY, params.key)
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.service.NounService
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(params: CreateIdentifiableNounParams): IdentifiableNoun {
        if (log.isTraceEnabled)
            log.trace("args : params=$params")
        validate(params)

        var noun = factory.identifiable(params.manager, params.key, params.label, params.labelCode,
                params.description, params.createAt)
        noun = dao.create(noun)

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }

    override fun create(params: CreateCountableNounParams): CountableNoun {
        if (log.isTraceEnabled) log.trace("args : params=$params")
        validate(params)

        var noun = factory.countable(params.manager, params.key, params.label, params.labelCode,
                params.description, params.createAt)
        noun = dao.create(noun)

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }

    override fun create(params: CreateLimitedIdentifiableNounParams): LimitedIdentifiableNoun {
        if (log.isTraceEnabled) log.trace("args : params=$params")
        validate(params)

        var noun = factory.limitedIdentifiable(params.manager, params.key, params.label,
                params.labelCode, params.limit, params.description, params.createAt)
        noun = dao.create(noun)

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }

    override fun create(params: CreateLimitedCountableNounParams): LimitedCountableNoun {
        if (log.isTraceEnabled) log.trace("args : params=$params")

        validate(params)

        var noun = factory.limitedCountable(params.manager, params.key, params.label,
                params.labelCode, params.limit, params.description, params.createAt)
        noun = dao.create(noun)

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }

    override fun <N : Noun> read(id: Int): N? {
        if (log.isTraceEnabled) log.trace("args : id=$id")

        val noun = dao.read<N>(id)

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }
}
