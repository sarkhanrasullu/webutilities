package com.bsptechs.dao.inter;

import com.bsptechs.entities.Form;
import com.bsptechs.entities.FormColumn;
import com.bsptechs.entities.FormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FormDataDao extends JpaRepository<FormData, Integer> {

    @Override
    List<FormData> findAll();

    List<FormData> findAllByFormColumnId(FormColumn formId);

    List<FormData> findAllByFormId(Form formId);

    @Modifying
    @Query("delete from FormData where uid=?1")
    void deleteAllByUid(String uid);



}
