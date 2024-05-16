package com.akai.community.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

// 小区对象
public class HjyCommunityVo implements Serializable {
    // 小区 id
    @TableId
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long communityId;

    // 小区名称
    private String communityName;

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
