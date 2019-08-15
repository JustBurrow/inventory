package kr.lul.inventory.web.manager.mapping

/**
 * @author justburrow
 * @since 2019-07-10
 */
object NounMvc {
    object M {
        const val CREATE_NOUN_REQ = "createNounReq"
        const val NOUN_ID = "id"
        const val NOUN = "noun"
        const val LIST = "list"
        const val UPDATE_NOUN_REQ = "updateNounReq"
    }

    object V {
        const val PREFIX = "layout/noun"

        const val CREATE_FORM = "$PREFIX/create"
        const val DETAIL = "$PREFIX/detail"
        const val LIST = "$PREFIX/list"
        const val UPDATE_FORM = "$PREFIX/update"
    }

    object C {
        const val GROUP = "/nouns"

        const val API_CREATE_FORM = "/create"
        const val API_CREATE = ""
        const val API_DETAIL = "/{id:\\d+}"
        const val API_LIST = ""
        const val API_UPDATE_FORM = "/{id:\\d+}/edit"
        const val API_UPDATE = "/{id:\\d+}"

        const val FULL_API_CREATE_FORM = "$GROUP$API_CREATE_FORM"
        const val FULL_API_CREATE = GROUP
        const val FULL_API_DETAIL = "$GROUP$API_DETAIL"
        const val FULL_API_LIST = GROUP
        const val FULL_API_UPDATE_FORM = "$GROUP$API_UPDATE_FORM"
        const val FULL_API_UPDATE = "$GROUP$API_UPDATE"
    }
}
