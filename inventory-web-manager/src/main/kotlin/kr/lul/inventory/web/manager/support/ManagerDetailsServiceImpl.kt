package kr.lul.inventory.web.manager.support

import kr.lul.inventory.business.service.ManagerService
import kr.lul.inventory.business.service.params.SearchCredentialParams
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.time.ZoneId

@Service
internal class ManagerDetailsServiceImpl : ManagerDetailsService {
    private val log = LoggerFactory.getLogger(ManagerDetailsServiceImpl::class.java)!!

    @Autowired
    private lateinit var managerService: ManagerService

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.support.ManagerDetailsService
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): ManagerDetails {
        if (log.isTraceEnabled)
            log.trace("args : username={}", username)
        if (null == username)
            throw IllegalArgumentException("username is null.")

        val credential = managerService.search(SearchCredentialParams(username))
                ?: run {
                    val msg = "username '$username' does not exist."
                    log.warn(msg)
                    throw UsernameNotFoundException(msg)
                }

        val manager = credential.getManager()
        if (log.isTraceEnabled)
            log.trace("manager={}", manager)

        val details = ManagerDetails(
                manager.getId(),
                manager.getEmail(),
                manager.getName(),
                manager.getCreatedAt().atZone(ZoneId.systemDefault()),
                manager.getUpdatedAt().atZone(ZoneId.systemDefault()),
                credential.getSecretHash()
        )

        if (log.isTraceEnabled)
            log.trace("return : {}", details)
        return details
    }
}
