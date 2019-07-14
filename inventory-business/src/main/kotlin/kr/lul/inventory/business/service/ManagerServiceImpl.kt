package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.CreateManagerParams
import kr.lul.inventory.data.dao.ManagerDao
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.factory.ManagerFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
internal class ManagerServiceImpl : ManagerService {
    private val log = LoggerFactory.getLogger(ManagerServiceImpl::class.java)!!

    @Autowired
    private lateinit var managerDao: ManagerDao
    @Autowired
    private lateinit var managerFactory: ManagerFactory

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.service.ManagerService
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(params: CreateManagerParams): Manager {
        if (log.isTraceEnabled())
            log.trace("args : params={}", params)

        var manager = managerFactory.instance(params.email, params.name, Instant.now())
        manager = managerDao.create(manager)

        if (log.isTraceEnabled)
            log.trace("return : {}", manager)
        return manager
    }
}
