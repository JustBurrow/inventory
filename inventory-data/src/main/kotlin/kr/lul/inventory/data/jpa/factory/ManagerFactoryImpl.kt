package kr.lul.inventory.data.jpa.factory

import kr.lul.inventory.data.jpa.entity.ManagerEntity
import kr.lul.inventory.design.factory.ManagerFactory
import org.springframework.stereotype.Component
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Component
class ManagerFactoryImpl : ManagerFactory {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.factory.ManagerFactory
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun instance(email: String, name: String, createdAt: Instant) = ManagerEntity(email, name, createdAt)
}
