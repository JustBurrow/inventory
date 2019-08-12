package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.web.manager.controller.argument.CurrentManager
import kr.lul.inventory.web.manager.mapping.IndexMvc.V
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
internal class IndexControllerImpl : IndexController {
    private val log = LoggerFactory.getLogger(IndexControllerImpl::class.java)!!

    private fun anonymous(model: Model): String = V.INDEX_ANONYMOUS

    private fun dashboard(manager: CurrentManager, model: Model): String = V.INDEX_MANAGER

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.controller.IndexController
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun index(manager: CurrentManager?, model: Model): String {
        if (log.isTraceEnabled)
            log.trace("args : manager=$manager, model=$model")

        val template = if (null == manager)
            anonymous(model)
        else
            dashboard(manager, model)

        if (log.isTraceEnabled)
            log.trace("result : template='$template', model=$model")
        return template
    }
}
