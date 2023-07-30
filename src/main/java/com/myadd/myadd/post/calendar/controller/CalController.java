package com.myadd.myadd.post.calendar.controller;

import com.myadd.myadd.post.calendar.service.CalService;
import com.myadd.myadd.post.crud.dto.PostBackDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CalController {
    private final CalService calService;

    ///posts/calendar/2023-07-29?userId=1
    @GetMapping("/posts/calendar/{createdAt}")
    @ResponseBody
    public List<PostBackDto> postListCreatedAt(@RequestParam(required = false) Long userId,
                                               @PathVariable String createdAt, Model model) {
        List<PostBackDto> postBackDtoList = calService.findByCreatedAt(userId, createdAt);
        model.addAttribute("postList", postBackDtoList);

        return postBackDtoList;

    }
}