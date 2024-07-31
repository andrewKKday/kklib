package com.kkday.svc.kklib.controller.data;

import lombok.Data;

@Data
public class Mail {
    private String subject;
    private String langCode;
    private String mailContent;
}
