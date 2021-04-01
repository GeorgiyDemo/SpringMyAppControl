package com.demenchuk_pi19_4.mycontrol.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MySuperModel {
    LocalDateTime createdDateTime = LocalDateTime.now();
    LocalDateTime changedDateTime = LocalDateTime.now();
}
