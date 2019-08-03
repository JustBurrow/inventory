package kr.lul.inventory.data.jpa.mapping

/**
 * @author justburrow
 * @since 2019-07-07
 */
object NounMapping {
    const val ENTITY_NAME = "Noun"
    const val TABLE_NAME = "mgr_noun"

    const val COL_ID = "id"
    const val COL_MANAGER = "manager"
    const val COL_TYPE = "type"
    const val COL_KEY = "noun_key"
    const val COL_LABEL = "label"
    const val COL_LABEL_CODE = "label_code"
    const val COL_CREATED_AT = "created_utc"
    const val COL_UPDATED_AT = "updated_utc"

    const val FK_NOUN_PK_MANAGER = "FK_NOUN_PK_MANAGER"
    const val FK_NOUN_PK_MANAGER_COLUMNS = "$COL_MANAGER ASC"

    const val FK_NOUN_PK_NOUN_TYPE = "FK_NOUN_PK_NOUN_TYPE"
    const val FK_NOUN_PK_NOUN_TYPE_COLUMNS = "$COL_TYPE ASC"
}
