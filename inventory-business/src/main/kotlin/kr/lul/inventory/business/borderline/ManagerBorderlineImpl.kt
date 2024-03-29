package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.CreateManagerCmd
import kr.lul.inventory.business.converter.ManagerConverter
import kr.lul.inventory.business.service.ManagerService
import kr.lul.inventory.business.service.params.CreateManagerParams
import kr.lul.inventory.design.util.TimeProvider
import kr.lul.inventory.dto.ManagerDetailDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
internal class ManagerBorderlineImpl : ManagerBorderline {
    private val log = LoggerFactory.getLogger(ManagerBorderlineImpl::class.java)!!

    @Autowired
    private lateinit var managerService: ManagerService
    @Autowired
    private lateinit var managerConverter: ManagerConverter
    @Autowired
    private lateinit var timeProvider: TimeProvider

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.borderline.ManagerBorderline
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(cmd: CreateManagerCmd): ManagerDetailDto {
        if (log.isTraceEnabled)
            log.trace("args : cmd={}", cmd)

        val manager = managerService.create(CreateManagerParams(
                cmd.email,
                cmd.name,
                cmd.password,
                timeProvider.instant))
        val dto = managerConverter.convert(manager, ManagerDetailDto::class)

        if (log.isTraceEnabled)
            log.trace("return : {}", dto)
        return dto
    }
}
