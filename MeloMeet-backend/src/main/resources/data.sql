INSERT INTO style (name)
VALUES
    ('Jazz'),
    ('Pop'),
    ('Hip Hop'),
    ('Rock'),
    ('Indie'),
    ('Magyar Alternatív'),
    ('Metál'),
    ('Electronic'),
    ('Folk'),
    ('Blues'),
    ('Punk'),
    ('Dance'),
    ('R&B'),
    ('Techno'),
    ('House'),
    ('Psychedelic Rock'),
    ('Classical');

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO venue (venue_id, address, city, is_open_air, name, postal_code)
VALUES (uuid_generate_v4(), 'your_address_value', 'your_city_value', true, 'your_name_value', 'your_postal_code_value');