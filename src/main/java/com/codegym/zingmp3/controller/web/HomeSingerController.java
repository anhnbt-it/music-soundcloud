package com.codegym.zingmp3.controller.web;

import com.codegym.zingmp3.model.Artist;
import com.codegym.zingmp3.model.Song;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("singer")
public class HomeSingerController {
    @Autowired
    SingerService singerService;

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;

    @ModelAttribute("albums")
    public Iterable<Album> albums() {
        return albumService.findAll();
    }

    @ModelAttribute("singers")
    public Iterable<Artist> sings() {
        return singerService.findAll();
    }


    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Artist singers = singerService.findById(id).get();
        Pageable pageable = PageRequest.of(0,5, Sort.by(Sort.Direction.DESC, "views"));
        Iterable<Album> albums = albumService.findAll();
        Page<Song> songs = songService.findAll(pageable);
        model.addAttribute("singer", singers);
        model.addAttribute("albums",albums);
        model.addAttribute("songs",songService.findAllBySingerId(id));
        model.addAttribute("bxh",songs);
        return "web/singers/listofsongs";
    }
}
