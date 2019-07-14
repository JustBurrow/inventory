package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.CreateNounCmd
import kr.lul.inventory.business.converter.NounConverter
import kr.lul.inventory.business.service.NounService
import kr.lul.inventory.business.service.params.CreateNounParams
import kr.lul.inventory.dto.NounDetailDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
internal class NounBorderlineImpl : NounBorderline {
    private val log = LoggerFactory.getLogger(NounBorderlineImpl::class.java)!!

    @Autowired
    private lateinit var nounService: NounService
    @Autowired
    private lateinit var nounConverter: NounConverter

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.borderline.NounBorderline
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(cmd: CreateNounCmd): NounDetailDto {
        if (log.isTraceEnabled) log.trace("args : cmd={}", cmd)

        val noun = nounService.create(CreateNounParams(cmd.key, cmd.label, cmd.labelCode))
        val dto = nounConverter.convert(noun, NounDetailDto::class)

        if (log.isTraceEnabled) log.trace("return : {}", dto)
        return dto
    }
}
