package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.web.manager.mapping.IndexMvc.V
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
internal class IndexControllerImpl : IndexController {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.controller.IndexController
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun index(model: Model): String = V.INDEX
}
