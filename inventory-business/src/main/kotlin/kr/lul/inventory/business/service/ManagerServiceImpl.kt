package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.CreateManagerParams
import kr.lul.inventory.business.service.params.SearchCredentialParams
import kr.lul.inventory.data.dao.ManagerDao
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.ManagerCredential
import kr.lul.inventory.design.factory.ManagerCredentialFactory
import kr.lul.inventory.design.factory.ManagerFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant

@Service
internal class ManagerServiceImpl : ManagerService {
    private val log = LoggerFactory.getLogger(ManagerServiceImpl::class.java)!!

    @Autowired
    private lateinit var managerDao: ManagerDao

    @Autowired
    private lateinit var managerFactory: ManagerFactory
    @Autowired
    private lateinit var managerCredentialFactory: ManagerCredentialFactory
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.service.ManagerService
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(params: CreateManagerParams): Manager {
        if (log.isTraceEnabled())
            log.trace("args : params={}", params)

        var manager = managerFactory.instance(params.email, params.name, Instant.now())
        manager = managerDao.create(manager)
        listOf(params.email, params.name).forEach {
            val credential = managerCredentialFactory.instance(manager, it,
                    passwordEncoder.encode(params.secret), manager.getCreatedAt())
            managerDao.create(credential)
        }

        if (log.isTraceEnabled)
            log.trace("return : {}", manager)
        return manager
    }

    override fun search(params: SearchCredentialParams): ManagerCredential? {
        if (log.isTraceEnabled)
            log.trace("args : params={}", params)

        val credential = if (ManagerCredential.isValidPublicKey(params.query))
            managerDao.readCredential(params.query)
        else
            null

        if (log.isTraceEnabled)
            log.trace("return : {}", credential)
        return credential
    }
}
