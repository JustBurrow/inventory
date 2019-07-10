package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.business.borderline.ItemBorderline
import kr.lul.inventory.business.borderline.cmd.CreateItemCmd
import kr.lul.inventory.web.manager.controller.request.CreateItemReq
import kr.lul.inventory.web.manager.mapping.ItemMvc.C
import kr.lul.inventory.web.manager.mapping.ItemMvc.M
import kr.lul.inventory.web.manager.mapping.ItemMvc.V
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import javax.validation.Valid

@Controller
internal class ItemControllerImpl : ItemController {
    private val log = LoggerFactory.getLogger(ItemControllerImpl::class.java)!!

    @Autowired
    private lateinit var itemBorderline: ItemBorderline

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.controller.ItemController
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun createForm(model: Model): String {
        if (log.isTraceEnabled) log.trace("args : model={}", model)

        model.addAttribute(M.CREATE_REQ, CreateItemReq(null, null, null))

        return V.CREATE_FORM
    }

    override fun create(@ModelAttribute(M.CREATE_REQ) @Valid req: CreateItemReq, binding: BindingResult,
                        model: Model): String {
        if (log.isTraceEnabled) log.trace("args : req={}, binding={}, model={}", req, binding, model)

        val item = itemBorderline.create(CreateItemCmd(req.key!!, req.label!!, req.labelCode!!))

        if (log.isTraceEnabled) log.trace("result : item={}", item)
        return "redirect:${C.GROUP}${C.API_CREATE_FORM}"
    }
}
