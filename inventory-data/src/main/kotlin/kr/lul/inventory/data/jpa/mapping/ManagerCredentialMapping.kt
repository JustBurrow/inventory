package kr.lul.inventory.data.jpa.mapping

/**
 * @author justburrow
 * @since 2019-07-14
 */
object ManagerCredentialMapping {
    const val ENTITY = "ManagerCredential"
    const val TABLE = "mgr_manager_credential"

    const val COL_ID = "id"
    const val COL_MANAGER = "manager"
    const val COL_PUBLIC_KEY = "public_key"
    const val COL_SECRET_HASH = "secret_hash"
    const val COL_CREATED_AT = "created_utc"

    const val FK_MANAGER_CREDENTIAL_PK_MANAGER = "FK_MANAGER_CREDENTIAL_PK_MANAGER"
    const val FK_MANAGER_CREDENTIAL_PK_MANAGER_COLUMNS = "$COL_MANAGER ASC"

    const val UQ_MANAGER_CREDENTIAL_PUBLIC_KEY = "UQ_MANAGER_CREDENTIAL_PUBLIC_KEY"
    val UQ_MANAGER_CREDENTIAL_PUBLIC_KEY_COLUMNS = listOf(COL_PUBLIC_KEY)
}
