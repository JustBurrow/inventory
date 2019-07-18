package kr.lul.inventory.data.jpa.mapping

/**
 * @author justburrow
 * @since 2019-07-15
 */
object CountableNounMapping {
    const val ENTITY_NAME = "CountableNoun"
    const val DISCRIMINATOR_VALUE = "1"
    const val TABLE_NAME = NounMapping.TABLE_NAME

    const val COL_ID = NounMapping.COL_ID
    const val COL_MANAGER = NounMapping.COL_MANAGER
    const val COL_TYPE = NounMapping.COL_TYPE
    const val COL_KEY = NounMapping.COL_KEY
    const val COL_LABEL = NounMapping.COL_LABEL
    const val COL_LABEL_CODE = NounMapping.COL_LABEL_CODE
}
