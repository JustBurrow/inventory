package kr.lul.inventory.data.jpa.factory

import kr.lul.inventory.data.jpa.entity.ManagerCredentialEntity
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.factory.ManagerCredentialFactory
import org.springframework.stereotype.Component
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Component
class ManagerCredentialFactoryImpl : ManagerCredentialFactory {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.factory.ManagerCredentialFactory
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun instance(manager: Manager, publicKey: String, secretHash: String, createdAt: Instant) =
            ManagerCredentialEntity(manager, publicKey, secretHash, createdAt)
}
