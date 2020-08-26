package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 内容管理
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-26 15:11
 **/
@Controller
@RequestMapping(value = "content")
public class ContentController {
    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = null;
        //id不为空，则从数据库获取
        if(id != null){
            tbContent = tbContentService.getById(id);
        }

        else {
            tbContent = new TbContent();
        }

        return tbContent;
    }

    /**
     * 跳转到内容列表页
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return "content_list";
    }

    /**
     * 跳转表单页
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }

    /**
     * 保存信息
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbContentService.save(tbContent);

        //保存成功
        if(baseResult.getStatus() == 200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }

        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }

    /**
     * 删除信息
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if(StringUtils.isNoneBlank(ids)){
            String[] idArray = ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除内容成功");
        }
        else {
            baseResult = BaseResult.fail("删除内容失败");
        }
        return baseResult;
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strDraw == null ? 0 : Integer.parseInt(strStart);
        int length = strDraw == null ? 10 : Integer.parseInt(strLength);

        //封装 Datetables 需要的结果
        PageInfo<TbContent> pageInfo = tbContentService.page(start, length, draw, tbContent);

        return pageInfo;
    }

    /**
     * 显示详情
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbContent tbContent){
        return "content_detail";
    }

    /**
     * 通过 ID 删除单条用户数据
     * @param tbContent
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.GET)
    public BaseResult del(TbContent tbContent){
        Long id = tbContent.getId();
        BaseResult baseResult = null;
        if(id != null){
            tbContentService.delete(id);
            baseResult = BaseResult.success("删除内容成功");
        }
        else {
            baseResult = BaseResult.fail("删除内容失败");
        }
        return baseResult;
    }
}
