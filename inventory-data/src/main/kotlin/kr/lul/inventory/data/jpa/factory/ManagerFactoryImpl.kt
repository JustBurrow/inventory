package kr.lul.inventory.data.jpa.factory

import kr.lul.inventory.data.jpa.entity.ManagerEntity
import kr.lul.inventory.design.factory.ManagerFactory
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-14
 */
class ManagerFactoryImpl : ManagerFactory {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.factory.ManagerFactory
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun getInstance(name: String, email: String, createdAt: Instant) = ManagerEntity(name, email, createdAt)
}
