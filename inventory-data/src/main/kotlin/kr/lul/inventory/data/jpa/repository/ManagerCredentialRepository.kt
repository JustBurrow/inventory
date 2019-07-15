package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.jpa.entity.ManagerCredentialEntity
import kr.lul.inventory.design.domain.ManagerCredential
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerCredentialRepository : JpaRepository<ManagerCredentialEntity, Long> {
    fun findOneByPublicKey(publicKey: String): ManagerCredential?
}
