package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.business.borderline.ManagerBorderline
import kr.lul.inventory.business.borderline.cmd.CreateManagerCmd
import kr.lul.inventory.web.manager.controller.request.CreateManagerReq
import kr.lul.inventory.web.manager.mapping.ManagerMvc.M
import kr.lul.inventory.web.manager.mapping.ManagerMvc.V
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import javax.validation.Valid

@Controller
internal class ManagerControllerImpl : ManagerController {
    private val log = LoggerFactory.getLogger(ManagerControllerImpl::class.java)!!

    @Autowired
    private lateinit var managerBorderline: ManagerBorderline

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.controller.ManagerController
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun createForm(model: Model): String {
        model.addAttribute(M.CREATE_MANAGER_REQ, CreateManagerReq())
        return V.CREATE_FORM
    }

    override fun create(@ModelAttribute(M.CREATE_MANAGER_REQ) @Valid req: CreateManagerReq, result: BindingResult,
                        model: Model): String {
        if (log.isTraceEnabled)
            log.trace("args : req={}, result={}, model={}", req, result, model)

        managerBorderline.create(CreateManagerCmd(
                req.email!!,
                req.name!!,
                req.secret!!
        ))

        val template = "redirect:/"
        if (log.isTraceEnabled) log.trace("result : model={}, template='$template'")
        return template
    }
}
