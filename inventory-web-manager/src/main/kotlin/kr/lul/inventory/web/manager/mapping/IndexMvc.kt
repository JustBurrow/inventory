package kr.lul.inventory.web.manager.mapping

/**
 * @author justburrow
 * @since 2019-07-14
 */
object IndexMvc {
    object M {
        const val CREATE_MANAGER_REQ = ManagerMvc.M.CREATE_MANAGER_REQ
    }

    object V {
        const val PREFIX = "layout/_"

        const val INDEX = "$PREFIX/index"
        const val INDEX_ANONYMOUS = "$INDEX-anonymous"
        const val INDEX_MANAGER = "$INDEX-manager"
        const val SIGN_IN = "$PREFIX/signin"
    }

    object C {
        const val GROUP = ""

        const val API_INDEX = "/"
        const val API_SIGN_UP = "/signup"
        const val API_SIGN_IN = "/signin"
        const val API_SIGN_OUT = "/signout"

        const val FULL_API_INDEX = "$GROUP$API_INDEX"
        const val FULL_API_SIGN_UP = "$GROUP$API_SIGN_UP"
        const val FULL_API_SIGN_IN = "$GROUP$API_SIGN_IN"
        const val FULL_API_SIGN_OUT = "$GROUP$API_SIGN_OUT"
    }
}
