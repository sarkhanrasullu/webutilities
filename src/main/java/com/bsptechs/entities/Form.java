/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author Anar Salami
 */
@Entity
@Table(name = "form")
@Data
public class Form implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "website")
    private String website;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formId", fetch = FetchType.LAZY)
    private List<FormData> formDataList;
    @OneToMany(mappedBy = "formId", fetch = FetchType.LAZY)
    private List<FormColumn> formColumnList;

    public Form(int id){
        this.id = id;
    }
    public Form(String website){this.website=website;}

    public Form() {
    }

    @Override
    public String toString() {
        return name;
    }
}
