package kr.lul.inventory.data.jpa.mapping

/**
 * @author justburrow
 * @since 2019-07-14
 */
object ManagerMapping {
    const val ENTITY = "Manager"
    const val TABLE = "mgr_manager"

    const val COL_ID = "id"
    const val COL_EMAIL = "email"
    const val COL_NAME = "name"
    const val COL_CREATED_AT = "created_utc"
    const val COL_UPDATED_AT = "updated_utc"

    const val UQ_MANAGER_EMAIL = "UQ_MANAGER_EMAIL"
    val UQ_MANAGER_EMAIL_COLUMNS = arrayOf(COL_EMAIL)

    const val UQ_MANAGER_NAME = "UQ_MANAGER_NAME"
    val UQ_MANAGER_NAME_COLUMNS = arrayOf(COL_NAME)
}
