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
    }

    object C {
        const val GROUP = "/"

        const val API_INDEX = ""
        const val API_SIGN_UP = "/signup"
    }
}
