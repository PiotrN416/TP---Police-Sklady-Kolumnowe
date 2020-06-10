package com.dq.police.model.entities;

import com.dq.police.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Task extends Entity {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String desc;

    @Getter
    @Setter
    private String cop;

    @Getter
    @Setter
    private String status;
}
