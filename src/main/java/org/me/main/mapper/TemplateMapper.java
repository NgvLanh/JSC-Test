package org.me.main.mapper;

import org.mapstruct.Mapper;
import org.me.main.dto.res.TemplateRes;
import org.me.main.model.EmailTemplate;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    TemplateRes toRes(EmailTemplate template);
}
