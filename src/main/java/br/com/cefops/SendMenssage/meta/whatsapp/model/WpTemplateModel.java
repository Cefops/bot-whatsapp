package br.com.cefops.SendMenssage.meta.whatsapp.model;

import lombok.Data;

import java.util.List;
@Data
public class WpTemplateModel {
    private String name;
    private WpLanguageModel language;
    private List<WpComponentModel> components;
}
