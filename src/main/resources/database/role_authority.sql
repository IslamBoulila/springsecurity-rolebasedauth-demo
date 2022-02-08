

create table authorities (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB;

create table role_authority (role_id bigint not null, authority_id bigint not null, primary key (role_id, authority_id)

, CONSTRAINT `roles_authorities_role_fk`
        FOREIGN KEY `role_fk` (`role_id`) REFERENCES `roles` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `roles_authorities_authority_fk`
        FOREIGN KEY `authority_fk` (`authority_id`) REFERENCES `authorities` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE

) engine=InnoDB;
