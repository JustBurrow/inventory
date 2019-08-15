package kr.lul.inventory.web.manager.mapping

/**
 * @author justburrow
 * @since 2019-08-15
 */
object ErrorMvc {
    object M {
        const val ERROR = "error"
        const val CAUSE = "cause"
    }

    object V {
        const val PREFIX = "layout/error"

        const val STATUS_404 = "$PREFIX/404"
        const val STATUS_500 = "$PREFIX/500"
    }
}
