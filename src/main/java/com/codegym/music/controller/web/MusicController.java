package com.codegym.music.controller.web;

import com.codegym.music.model.Song;
import com.codegym.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String SongInfo(@PathVariable Long id, Model model){
        Song song = songService.findById(id).get();
        model.addAttribute("song",song);
        return "web/user/musicInfo";
    }
}
