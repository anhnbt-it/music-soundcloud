package com.codegym.music.controller.web;

import com.codegym.music.model.Song;
import com.codegym.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("music")
public class MusicController {

    @Autowired
    private SongService songService;

    @GetMapping("{id}")
    public String SongInfo(@PathVariable Long id, Pageable pageable, Model model){
        Song song = songService.findById(id).get();
        Iterable<Song> concerning_song = songService.findAllBy5BySingerId(song.getSinger().getId(),id);
        Page<Song> songs = songService.findAll(pageable);
        song.setViews(song.getViews()+1);
        songService.save(song);
//        System.out.println(song.getViews());
        model.addAttribute("song",song);
        model.addAttribute("concerning_songs", concerning_song);
        model.addAttribute("bxh",songs);
        return "web/user/musicInfo";
    }
}
