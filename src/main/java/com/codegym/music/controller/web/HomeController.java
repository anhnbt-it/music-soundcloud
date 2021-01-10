package com.codegym.music.controller.web;

import com.codegym.music.model.Album;
import com.codegym.music.model.Category;
import com.codegym.music.model.Song;
import com.codegym.music.model.Singer;
import com.codegym.music.service.AlbumService;
import com.codegym.music.service.CategoryService;
import com.codegym.music.service.SongService;
import com.codegym.music.service.SingerService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SongService songService;

    @Autowired
    private SingerService singerService;

    @Autowired
    private AlbumService albumService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("albums")
    public Iterable<Album> albums() {
        return albumService.findAll();
    }

    @GetMapping("/")
    public String index() {
        return "web/home";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "errors/403";
    }

    @ModelAttribute("songs")
    public List<Song> songs(){
        return songService.findAll();
    }

    @ModelAttribute("singers")
    public Iterable<Singer> sings() {
        return singerService.findAll();
    }

}
