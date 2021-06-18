package com.codegym.zingmp3.controller.web;

import com.codegym.zingmp3.model.Album;
import com.codegym.zingmp3.model.Artist;
import com.codegym.zingmp3.model.Genre;
import com.codegym.zingmp3.model.Song;
import com.codegym.zingmp3.service.AlbumService;
import com.codegym.zingmp3.service.CategoryService;
import com.codegym.zingmp3.service.SingerService;
import com.codegym.zingmp3.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hot")
public class HotSongController {
    @Autowired
    private SongService songService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SingerService singerService;

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

    @GetMapping("music")
    public String list(Model model){
        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "views"));
        Page<Song> hotSongs = songService.findAll(pageable);
        Pageable pageable1 = PageRequest.of(0,5, Sort.by(Sort.Direction.DESC, "views"));
        Page<Song> songs = songService.findAll(pageable1);
        model.addAttribute("hot",hotSongs);
        model.addAttribute("bxh",songs);
        return "web/music/hotSong";


    }
}
