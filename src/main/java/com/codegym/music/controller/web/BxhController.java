package com.codegym.music.controller.web;

import com.codegym.music.model.Song;
import com.codegym.music.repository.SongRepository;
import com.codegym.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bxh")
public class BxhController {

    @Autowired
    private SongService songService;

    @GetMapping
    public String bxh(@RequestParam("category") Long id){
        Pageable pageable = PageRequest.of(0,5,Sort.by(Sort.Direction.DESC, "views"));
//        return songService.findAll(pageable);
        return "abcbc";
    }
}
