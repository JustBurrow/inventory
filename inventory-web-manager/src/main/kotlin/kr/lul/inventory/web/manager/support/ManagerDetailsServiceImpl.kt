package kr.lul.inventory.web.manager.support

import kr.lul.inventory.business.service.ManagerService
import kr.lul.inventory.business.service.params.SearchCredentialParams
import kr.lul.inventory.design.util.TimeProvider
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
internal class ManagerDetailsServiceImpl : ManagerDetailsService {
    private val log = LoggerFactory.getLogger(ManagerDetailsServiceImpl::class.java)!!

    @Autowired
    private lateinit var managerService: ManagerService
    @Autowired
    private lateinit var timeProvider: TimeProvider

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.support.ManagerDetailsService
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): ManagerDetails {
        if (log.isTraceEnabled)
            log.trace("args : username={}", username)
        username ?: throw UsernameNotFoundException("username is null.")

        val credential = managerService.search(SearchCredentialParams(username))
                ?: run {
                    val msg = "username '$username' does not exist."
                    log.warn(msg)
                    throw UsernameNotFoundException(msg)
                }

        val manager = credential.manager
        if (log.isTraceEnabled)
            log.trace("manager={}", manager)

        val details = ManagerDetails(manager.id,
                manager.email,
                manager.name,
                timeProvider.toZoneDateTime(manager.createdAt),
                timeProvider.toZoneDateTime(manager.updatedAt),
                credential.secretHash)

        if (log.isTraceEnabled)
            log.trace("return : {}", details)
        return details
    }
}
