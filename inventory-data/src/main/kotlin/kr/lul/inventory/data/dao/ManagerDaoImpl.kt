package kr.lul.inventory.data.dao

import kr.lul.inventory.data.jpa.entity.ManagerCredentialEntity
import kr.lul.inventory.data.jpa.entity.ManagerEntity
import kr.lul.inventory.data.jpa.repository.ManagerCredentialRepository
import kr.lul.inventory.data.jpa.repository.ManagerRepository
import kr.lul.inventory.design.domain.AttributeValidationException
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_EMAIL
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_NAME
import kr.lul.inventory.design.domain.ManagerCredential
import kr.lul.inventory.design.util.Assertion.`is`
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
internal class ManagerDaoImpl : ManagerDao {
    private val log = LoggerFactory.getLogger(ManagerDaoImpl::class.java)!!

    @Autowired
    private lateinit var managerRepository: ManagerRepository
    @Autowired
    private lateinit var managerCredentialRepository: ManagerCredentialRepository

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.data.dao.ManagerDao
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(manager: Manager): Manager {
        if (log.isTraceEnabled)
            log.trace("args : manager={}", manager)

        `is`(manager, ManagerEntity::class, "manager")

        if (managerRepository.existsByEmail(manager.getEmail()))
            throw AttributeValidationException(ATTR_EMAIL, manager.getEmail(), "used $ATTR_EMAIL.")
        else if (managerRepository.existsByName(manager.getName()))
            throw AttributeValidationException(ATTR_NAME, manager.getName(), "used $ATTR_NAME.")


        val saved = managerRepository.save(manager as ManagerEntity)

        if (log.isTraceEnabled)
            log.trace("return : {}", saved)
        return saved
    }

    override fun create(credential: ManagerCredential): ManagerCredential {
        if (log.isTraceEnabled)
            log.trace("args : credential={}", credential)
        `is`(credential, ManagerCredentialEntity::class, "manager")

        val saved = managerCredentialRepository.save(credential as ManagerCredentialEntity)

        if (log.isTraceEnabled)
            log.trace("return : {}", saved)
        return saved
    }

    override fun readCredential(publicKey: String): ManagerCredential? {
        if (log.isTraceEnabled)
            log.trace("args : publicKey='{}'", publicKey)

        val credential = managerCredentialRepository.findOneByPublicKey(publicKey)

        if (log.isTraceEnabled)
            log.trace("return : {}", credential)
        return credential
    }
}
