package kr.lul.inventory.business.service

import kr.lul.inventory.business.borderline.cmd.ReadNounParams
import kr.lul.inventory.business.service.params.AbstractCreateNounParams
import kr.lul.inventory.business.service.params.AbstractUpdateNounParams
import kr.lul.inventory.business.service.params.CreateCountableNounParams
import kr.lul.inventory.business.service.params.CreateIdentifiableNounParams
import kr.lul.inventory.business.service.params.CreateLimitedCountableNounParams
import kr.lul.inventory.business.service.params.CreateLimitedIdentifiableNounParams
import kr.lul.inventory.business.service.params.SearchNounParams
import kr.lul.inventory.business.service.params.UpdateCountableNounParams
import kr.lul.inventory.business.service.params.UpdateIdentifiableNounParams
import kr.lul.inventory.business.service.params.UpdateLimitedCountableNounParams
import kr.lul.inventory.business.service.params.UpdateLimitedIdentifiableNounParams
import kr.lul.inventory.data.dao.NounDao
import kr.lul.inventory.design.domain.AttributeValidationException
import kr.lul.inventory.design.domain.CountableNoun
import kr.lul.inventory.design.domain.IdentifiableNoun
import kr.lul.inventory.design.domain.InvalidAttributeException
import kr.lul.inventory.design.domain.LimitedCountableNoun
import kr.lul.inventory.design.domain.LimitedIdentifiableNoun
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_ID
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_KEY
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_MANAGER
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_TYPE
import kr.lul.inventory.design.factory.NounFactory
import kr.lul.inventory.design.util.Assertion.notNegative
import kr.lul.inventory.design.util.Assertion.positive
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
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

    private fun <N : Noun> validateAndRead(params: AbstractUpdateNounParams): N {
        val noun: N
        try {
            if (0 >= params.id)
                throw AttributeValidationException("noun id is not positive", ATTR_ID, params.id)

            noun = dao.read<N>(params.id)
                    ?: throw AttributeValidationException("noun does not exist", ATTR_ID, params.id)

            if (params.type != noun.type)
                throw AttributeValidationException("noun type does not match", ATTR_TYPE, params.type)

            if (params.manager != noun.manager)
                throw AttributeValidationException("not owner", ATTR_MANAGER, params.manager,
                        NotOwnerException("not owner", params.manager))
        } catch (e: Throwable) {
            log.warn(e.message)
            throw  e
        }

        return noun
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

    override fun <N : Noun> read(params: ReadNounParams): N? {
        if (log.isTraceEnabled) log.trace("args : params=$params")

        val noun = dao.read<N>(params.id)
        if (null != noun && params.manager != noun.manager)
            throw NotOwnerException("manager is not owner.", params.manager)

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }

    override fun search(params: SearchNounParams): Page<Noun> {
        if (log.isTraceEnabled) log.trace("args : params=$params")

        notNegative(params.page, "params.page")
        positive(params.size, "params.size")

        val list = dao.search(params.manager, params.page, params.size, params.sort)

        if (log.isTraceEnabled) log.trace("return : $list")
        return list
    }

    override fun update(params: UpdateIdentifiableNounParams): IdentifiableNoun {
        if (log.isTraceEnabled) log.trace("args : params=$params")

        val noun: IdentifiableNoun = validateAndRead(params)
        val updater = noun.updater(params.timestamp)
        updater.label = params.label
        updater.labelCode = params.labelCode
        updater.description = params.description

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }

    override fun update(params: UpdateCountableNounParams): CountableNoun {
        if (log.isTraceEnabled) log.trace("args : params=$params")

        val noun: CountableNoun = validateAndRead(params)
        val updater = noun.updater(params.timestamp)
        updater.label = params.label
        updater.labelCode = params.labelCode
        updater.description = params.description

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }

    override fun update(params: UpdateLimitedIdentifiableNounParams): LimitedIdentifiableNoun {
        if (log.isTraceEnabled) log.trace("args : params=$params")

        val noun: LimitedIdentifiableNoun = validateAndRead(params)
        val updater = noun.updater(params.timestamp)
        updater.label = params.label
        updater.labelCode = params.labelCode
        updater.limit = params.limit
        updater.description = params.description

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }

    override fun update(params: UpdateLimitedCountableNounParams): LimitedCountableNoun {
        if (log.isTraceEnabled) log.trace("args : params=$params")

        val noun: LimitedCountableNoun = validateAndRead(params)
        val updater = noun.updater(params.timestamp)
        updater.label = params.label
        updater.labelCode = params.labelCode
        updater.limit = params.limit
        updater.description = params.description

        if (log.isTraceEnabled) log.trace("return : $noun")
        return noun
    }
}
