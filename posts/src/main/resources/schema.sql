create table if not exists users
(
    id       serial primary key,
    nickname varchar   not null,
    created  timestamp not null
);

create table if not exists posts
(
    id          serial primary key,
    description varchar                       not null,
    created     timestamp                     not null,
    author_id   integer references users (id) not null
);
