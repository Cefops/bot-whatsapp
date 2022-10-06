package br.com.cefops.SendMenssage.meta.whatsapp.model;

import lombok.Data;

import java.util.List;

@Data
public class WpComponentModel {
    private String type;
    private List<WpParameterModel> parameters;

}
