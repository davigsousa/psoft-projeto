package com.psoft.tccmatch.processors.PropostaTCCProcessor;

import com.psoft.tccmatch.DTO.PropostaTCCDTO;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.model.User;

import java.util.List;

public interface PropostaTCCProcessor {
    PropostaTCC criar(PropostaTCC propostaTCC, User user);
    List<PropostaTCC> getAll(User user);
}
