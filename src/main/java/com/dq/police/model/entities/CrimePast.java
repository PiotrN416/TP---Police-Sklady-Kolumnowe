package com.dq.police.model.entities;

import com.dq.police.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CrimePast extends Entity {

    @Getter
    @Setter
    private String person;

    @Getter
    @Setter
    private String desc;
}
