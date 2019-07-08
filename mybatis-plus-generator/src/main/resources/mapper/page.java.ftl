package ${cfg.PagePackage};

import java.sql.Timestamp;
import ${package.Entity}.${entity};
import java.util.Date;
<#if entityLombokModel>
import lombok.Data;
</#if>

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
</#if>
public class ${entity}PageForm extends PageForm<${entity}>{

    private String keyword;

}
