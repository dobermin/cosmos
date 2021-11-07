package com.dobermin.cosmos.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ConfigDataSource {

	@Bean
	public DataSource getDataSource () {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		dataSourceBuilder.url("postgres://gxvkomiynmfhca:d93943d1be209d294af78f483558938d3f455fddde45e7a588f579dbc99e8670@ec2-18-202-1-222.eu-west-1.compute.amazonaws.com:5432/ddk793ra60c2e5");
		dataSourceBuilder.username("gxvkomiynmfhca");
		dataSourceBuilder.password("d93943d1be209d294af78f483558938d3f455fddde45e7a588f579dbc99e8670");
		return dataSourceBuilder.build();
	}
}
