package kr.lul.inventory.design.factory

import kr.lul.inventory.design.domain.Manager
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerFactory {
    fun instance(email: String, name: String, createdAt: Instant): Manager
}
