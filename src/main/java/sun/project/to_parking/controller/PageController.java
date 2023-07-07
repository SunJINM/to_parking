package sun.project.to_parking.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page")
@Slf4j
public class PageController {
    /**
     * @description 管理员登录页面
     */
    @ApiOperation("管理员登录页面")
    @GetMapping("/toLogin")
    public ModelAndView toLogin(){
        log.info("进入登录页面！");
        ModelAndView modelAndView=new ModelAndView("toLogin");
        return modelAndView;
    }
    /**
     * @description 菜单页面
     */
    @ApiOperation("views管理页面")
    @GetMapping("/views/{page}")
    public ModelAndView views(@PathVariable("page") String page){
        ModelAndView modelAndView=new ModelAndView("views/"+page);
        log.info("进入页面！{}",modelAndView);
        return modelAndView;
    }
    /**
     * @description pages管理页面
     */
    @ApiOperation("pages管理页面")
    @GetMapping("/views/pages/{page}")
    public ModelAndView pages(@PathVariable("page") String page){
        ModelAndView modelAndView=new ModelAndView("views/pages/"+page);
        log.info("进入页面！{}",modelAndView);
        return modelAndView;
    }
}
