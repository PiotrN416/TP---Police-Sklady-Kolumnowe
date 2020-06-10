package com.dq.police.model;

import com.dq.police.helpers.IdsService;
import lombok.Getter;
import lombok.Setter;

public abstract class Entity {

    @Getter
    @Setter
    private Integer id;

    public Entity() {
        this.id = IdsService.getNext();
    }
}
