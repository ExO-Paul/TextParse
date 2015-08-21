/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.controller.bean;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pavel
 */
public class TextControllerRequest {

    private Map<TextParamName, Object> params;

    public TextControllerRequest() {
        params = new HashMap<TextParamName, Object>();
    }

    public TextControllerRequest(Map<TextParamName, Object> map) {
        params = map;
    }

    public Map<TextParamName, Object> getParams() {
        return params;
    }

    public void setParams(Map<TextParamName, Object> params) {
        this.params = params;
    }

    public void addParams(Map<TextParamName, Object> params) {
        this.params.putAll(params);
    }

    public Object getParam(TextParamName paramName) {
        params.get(paramName);
        return params.get(paramName);
    }

public void addParam(TextParamName paramName, Object parameter) {
        this.params.put(paramName, parameter);
    }
    
    
}
