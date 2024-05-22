package com.akai.web.controller.system;

import com.akai.common.constant.UserConstants;
import com.akai.common.core.controller.BaseController;
import com.akai.common.core.domain.BaseResponse;
import com.akai.common.core.page.PageResult;
import com.akai.common.utils.SecurityUtils;
import com.akai.system.domain.SysPost;
import com.akai.system.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController {
    @Autowired
    private SysPostService postService;

    /**
     * 获取岗位列表
     */
    @GetMapping("/list")
    public PageResult list(SysPost post) {
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return getData(list);
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @GetMapping(value = "/{postId}")
    public BaseResponse getInfo(@PathVariable Long postId) {
        return BaseResponse.success(postService.selectPostById(postId));
    }

    /**
     * 新增岗位
     */
    @PostMapping
    public BaseResponse add(@RequestBody SysPost post) {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return BaseResponse.fail("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return BaseResponse.fail("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setCreateBy(SecurityUtils.getUserName());
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @PutMapping
    public BaseResponse edit(@RequestBody SysPost post) {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return BaseResponse.fail("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return BaseResponse.fail("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setUpdateBy(SecurityUtils.getUserName());
        return toAjax(postService.updatePost(post));
    }

    /**
     * 删除岗位
     */
    @DeleteMapping("/{postIds}")
    public BaseResponse remove(@PathVariable Long[] postIds) {
        return toAjax(postService.deletePostByIds(postIds));
    }

    /**
     * 获取岗位选择框列表
     */
    @GetMapping("/optionselect")
    public BaseResponse optionselect() {
        List<SysPost> posts = postService.selectPostAll();
        return BaseResponse.success(posts);
    }
}
