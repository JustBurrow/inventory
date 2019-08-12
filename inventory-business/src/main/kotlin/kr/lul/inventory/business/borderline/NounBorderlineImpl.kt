package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.*
import kr.lul.inventory.business.converter.NounConverter
import kr.lul.inventory.business.service.ManagerService
import kr.lul.inventory.business.service.NounService
import kr.lul.inventory.business.service.params.CreateCountableNounParams
import kr.lul.inventory.business.service.params.CreateIdentifiableNounParams
import kr.lul.inventory.business.service.params.CreateLimitedCountableNounParams
import kr.lul.inventory.business.service.params.CreateLimitedIdentifiableNounParams
import kr.lul.inventory.design.domain.InvalidAttributeException
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.domain.NounType
import kr.lul.inventory.design.util.Assertion.positive
import kr.lul.inventory.design.util.TimeProvider
import kr.lul.inventory.dto.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
internal class NounBorderlineImpl : NounBorderline {
    private val log = LoggerFactory.getLogger(NounBorderlineImpl::class.java)!!

    @Autowired
    private lateinit var nounService: NounService
    @Autowired
    private lateinit var nounConverter: NounConverter

    @Autowired
    private lateinit var managerService: ManagerService
    @Autowired
    private lateinit var timeProvider: TimeProvider

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.borderline.NounBorderline
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(cmd: CreateCountableNounCmd): CountableNounDetailDto {
        if (log.isTraceEnabled) log.trace("args : cmd=$cmd")

        positive(cmd.manager, "cmd.manager")

        val manager = managerService.read(cmd.manager)
                ?: throw InvalidAttributeException("manager does not exist", "cmd.manager", cmd.manager)

        val params = CreateCountableNounParams(cmd.contextId, manager, cmd.key, cmd.label, cmd.labelCode,
                cmd.description, timeProvider.instant)
        val noun = nounService.create(params)
        val dto = nounConverter.convert(noun, CountableNounDetailDto::class)

        if (log.isTraceEnabled) log.trace("return : $dto")
        return dto
    }

    override fun create(cmd: CreateIdentifiableNounCmd): IdentifiableNounDetailDto {
        if (log.isTraceEnabled) log.trace("args : cmd=$cmd")
        positive(cmd.manager, "cmd.manager")

        val manager = managerService.read(cmd.manager)
                ?: throw InvalidAttributeException("manager does not exist", "cmd.manager", cmd.manager)

        val params = CreateIdentifiableNounParams(cmd.contextId, manager, cmd.key, cmd.label, cmd.labelCode,
                cmd.description, timeProvider.instant)
        val noun = nounService.create(params)
        val dto = nounConverter.convert(noun, IdentifiableNounDetailDto::class)

        if (log.isTraceEnabled) log.trace("return : $dto")
        return dto
    }

    override fun create(cmd: CreateLimitedCountableNounCmd): LimitedCountableNounDetailDto {
        if (log.isTraceEnabled) log.trace("args : cmd=$cmd")
        positive(cmd.manager, "cmd.manager")

        val manager = managerService.read(cmd.manager)
                ?: throw InvalidAttributeException("manager does not exist", "cmd.manager", cmd.manager)
        val params = CreateLimitedCountableNounParams(cmd.contextId, manager, cmd.key, cmd.label, cmd.labelCode,
                cmd.limit, cmd.description, timeProvider.instant)
        val noun = nounService.create(params)
        val dto = nounConverter.convert(noun, LimitedCountableNounDetailDto::class)

        if (log.isTraceEnabled) log.trace("return : $dto")
        return dto
    }

    override fun create(cmd: CreateLimitedIdentifiableNounCmd): LimitedIdentifiableNounDetailDto {
        if (log.isTraceEnabled) log.trace("args : cmd=$cmd")
        positive(cmd.manager, "cmd.manager")

        val manager = managerService.read(cmd.manager)
                ?: throw InvalidAttributeException("manager does not exist", "cmd.manager", cmd.manager)
        val params = CreateLimitedIdentifiableNounParams(cmd.contextId, manager, cmd.key, cmd.label, cmd.labelCode,
                cmd.limit, cmd.description, timeProvider.instant)
        val noun = nounService.create(params)
        val dto = nounConverter.convert(noun, LimitedIdentifiableNounDetailDto::class)

        if (log.isTraceEnabled) log.trace("return : $dto")
        return dto
    }

    @Suppress("UNCHECKED_CAST")
    override fun <N : NounDetailDto> read(cmd: ReadNounCmd): N? {
        if (log.isTraceEnabled) log.trace("args : cmd=$cmd")

        val noun = nounService.read<Noun>(cmd.id)
        val dto = when (noun?.type) {
            NounType.IDENTIFIABLE -> nounConverter.convert(noun, IdentifiableNounDetailDto::class) as N
            NounType.COUNTABLE -> nounConverter.convert(noun, CountableNounDetailDto::class) as N
            NounType.LIMITED_IDENTIFIABLE -> nounConverter.convert(noun, LimitedIdentifiableNounDetailDto::class) as N
            NounType.LIMITED_COUNTABLE -> nounConverter.convert(noun, LimitedCountableNounDetailDto::class) as N
            else -> null
        }

        if (log.isTraceEnabled) log.trace("return : $dto")
        return dto
    }
}
