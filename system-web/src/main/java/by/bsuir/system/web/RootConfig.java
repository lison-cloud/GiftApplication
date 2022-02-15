package by.bsuir.system.web;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "by.bsuir.system" },
         excludeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
                @Filter(type = FilterType.ANNOTATION, value = RestController.class)
})
@Import(DataSourceConfiguration.class)
public class RootConfig {

    @Bean
    public JdbcTemplate createJdbcTeamplate(DataSource dataSource) {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource);
        return template;
    }

}
