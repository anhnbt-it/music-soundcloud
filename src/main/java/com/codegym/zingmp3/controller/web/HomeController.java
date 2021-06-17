package com.codegym.zingmp3.controller.web;

import com.codegym.zingmp3.model.Album;
import com.codegym.zingmp3.model.Genre;
import com.codegym.zingmp3.model.Song;
import com.codegym.zingmp3.model.Artist;
import com.codegym.zingmp3.service.AlbumService;
import com.codegym.zingmp3.service.CategoryService;
import com.codegym.zingmp3.service.SongService;
import com.codegym.zingmp3.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
    public Iterable<Genre> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("albums")
    public Iterable<Album> albums() {
        return albumService.findAll();
    }

    @ModelAttribute("singers")
    public Iterable<Artist> sings() {
        return singerService.findAll();
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
    public Iterable<Song> songs() {
        return songService.findAll();
    }

    @ModelAttribute("bxh")
    public Iterable<Song> bxhVn(){
        Pageable pageable = PageRequest.of(0,5,Sort.by(Sort.Direction.DESC, "views"));
        return songService.findAll(pageable);
    }
}
