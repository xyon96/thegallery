package com.indigorobot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@EnableAutoConfiguration
public class TheGalleryController {

    @Value("${image.storage.path}")
    private String imageStoragePath;
    @Value("${mysql.host}")
    private String mysqlHost;
    @Value("${mysql.port}")
    private String mysqlPort;
    @Value("${mysql.db}")
    private String mysqlDb;
    @Value("${mysql.imagemeta.table}")
    private String imageMetaTable;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String mainPage(@RequestParam(value="name", required=false, defaultValue="world") String name, Model model) {
        model.addAttribute("name", name);
        return "mainpage";
    }

    @RequestMapping(path = "/galleries", method = RequestMethod.GET)
    public String galleriesPage() {
        return "galleries";
    }

}
