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
    }

    object V {
        const val PREFIX = "layout/noun"

        const val CREATE_FORM = "$PREFIX/create"
        const val DETAIL = "$PREFIX/detail"
        const val LIST = "$PREFIX/list"
    }

    object C {
        const val GROUP = "/nouns"

        const val API_LIST = ""

        const val API_CREATE_FORM = "/create"
        const val API_CREATE = ""
        const val API_DETAIL = "/{id:[1-9]\\d*}"

        const val FULL_API_CREATE_FORM = "$GROUP$API_CREATE_FORM"
        const val FULL_API_CREATE = GROUP
        const val FULL_API_DETAIL = "$GROUP$API_DETAIL"
        const val FULL_API_LIST = GROUP
    }
}
