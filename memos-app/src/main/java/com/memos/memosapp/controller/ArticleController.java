package com.memos.memosapp.controller;

import com.memos.memosapp.common.Result;
import com.memos.memosapp.entity.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/test")
    public Result<String> test() {
        return Result.success("文章模块正常");
    }

//    @PostMapping("/{title}" + "/{content}")
//    public String testtitle(@PathVariable String title, @PathVariable String content) {
//        return title + content + "发布成功";
//    }


    @PostMapping("/test")
    public Result<Article> receiveArticle(@RequestBody Article article) {
        // 假装处理了一下，给 id 赋个值
        article.setId(1L);
        Result<Article> result = Result.success(article);
        result.setMessage("发布成功");
        return result;
    }
}
