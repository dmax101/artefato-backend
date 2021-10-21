package br.inatel.icc.idp.artefato.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@ComponentScan("br.inatel.icc.idp.artefato")
@EnableNeo4jRepositories(basePackages = "br.inatel.icc.idp.artefato.repository")
public class Neo4jRepositoriesConfig {

}
