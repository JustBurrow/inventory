package kr.lul.inventory.design.factory

import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.ManagerCredential
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerCredentialFactory {
    fun instance(manager: Manager, publicKey: String, secretHash: String, createdAt: Instant): ManagerCredential
}
