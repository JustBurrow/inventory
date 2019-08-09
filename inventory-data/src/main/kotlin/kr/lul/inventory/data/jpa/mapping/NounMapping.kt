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
    const val COL_NOUN_TYPE = "noun_type"
    const val COL_NOUN_KEY = "noun_key"
    const val COL_LABEL = "label"
    const val COL_LABEL_CODE = "label_code"
    const val COL_DESCRIPTION = "description"
    const val COL_CREATED_AT = "created_utc"
    const val COL_UPDATED_AT = "updated_utc"

    const val FK_NOUN_PK_MANAGER = "FK_NOUN_PK_MANAGER"
    const val FK_NOUN_PK_MANAGER_COLUMNS = "$COL_MANAGER ASC"

    const val FK_NOUN_PK_NOUN_TYPE = "FK_NOUN_PK_NOUN_TYPE"
    const val FK_NOUN_PK_NOUN_TYPE_COLUMNS = "$COL_NOUN_TYPE ASC"

    const val UQ_NOUN_KEY = "UQ_NOUN_KEY"
    val UQ_NOUN_KEY_COLUMNS = listOf(UQ_NOUN_KEY)

    const val IDX_NOUN_LABEL = "IDX_NOUN_LABEL"
    const val IDX_NOUN_LABEL_COLUMNS = "$COL_LABEL ASC"

    const val IDX_NOUN_LABEL_CODE = "IDX_NOUN_LABEL_CODE"
    const val IDX_NOUN_LABEL_CODE_COLUMNS = "$COL_LABEL_CODE ASC"
}
