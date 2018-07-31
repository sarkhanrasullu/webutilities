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
@Table(name = "form_column")
@Data
public class FormColumn implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formColumnId", fetch = FetchType.EAGER)
    private List<FormData> formDataList;
    @JoinColumn(name = "form_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Form formId;
    @JoinColumn(name = "form_website", referencedColumnName = "website")
    @ManyToOne(fetch = FetchType.EAGER)
    private Form formWebsite;

    public FormColumn(){

    }

    public FormColumn(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "FormColumn{" +
                "id=" + id +
                '}';
    }
}
