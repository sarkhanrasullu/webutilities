/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.beans;

import lombok.Data;

/**
 * @author Anar Salami
 */
@Data
public class SignFormDto {

    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;

}
