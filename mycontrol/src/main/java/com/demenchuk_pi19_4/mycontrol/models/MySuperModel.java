package com.demenchuk_pi19_4.mycontrol.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The type My super model.
 */
@Data
@NoArgsConstructor
public class MySuperModel {
    /**
     * The Created date time.
     */
    LocalDateTime createdDateTime = LocalDateTime.now();
    /**
     * The Changed date time.
     */
    LocalDateTime changedDateTime = LocalDateTime.now();
}
