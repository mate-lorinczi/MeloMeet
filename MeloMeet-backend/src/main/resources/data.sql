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
VALUES ('68f886e0-ecf1-4ba5-b2e0-1435aec5cf98', 'your_address_value', 'your_city_value', true, 'your_name_value', 'your_postal_code_value');

INSERT INTO performer (performer_id, name)
VALUES
    ('ce717f51-55fd-4190-b0b9-0a5ab0d3e7b0', 'Radiohead'),
    ('0aa0091e-ba02-40d1-b99c-670c6ed3ae02', 'Black Country, New Road');

INSERT INTO users (user_id,date_of_registration, email, is_admin, is_banned, password, username)
VALUES
    ('3d470422-1ccb-4a1a-a84a-03a0f640fd08', current_timestamp, 'admin@admin.com', true, false, 'admin', 'admin'),
    ('599ea3ef-8369-4037-81a4-e69ff914fd3c', current_timestamp, 'asd@asd.com', false, false, 'asd', 'asd');

INSERT INTO concert_event (event_id, end_date_and_time, start_date_and_time, created_by_user_id, venue_venue_id)
VALUES
    ('6f665966-59b5-491a-a73f-e224a8e25e06', current_timestamp, current_timestamp, '3d470422-1ccb-4a1a-a84a-03a0f640fd08', '68f886e0-ecf1-4ba5-b2e0-1435aec5cf98'),
    ('56f246fd-e3b3-42bb-bc63-823793a19ff1', current_timestamp, current_timestamp, '599ea3ef-8369-4037-81a4-e69ff914fd3c', '68f886e0-ecf1-4ba5-b2e0-1435aec5cf98');

Insert INTO concert_event_performers (concert_event_event_id, performers_performer_id)
Values
    ('56f246fd-e3b3-42bb-bc63-823793a19ff1', 'ce717f51-55fd-4190-b0b9-0a5ab0d3e7b0'),
    ('56f246fd-e3b3-42bb-bc63-823793a19ff1', '0aa0091e-ba02-40d1-b99c-670c6ed3ae02'),
    ('6f665966-59b5-491a-a73f-e224a8e25e06', '0aa0091e-ba02-40d1-b99c-670c6ed3ae02');

INSERT INTO concert_event_styles (concert_event_event_id, styles_style_id)
VALUES
    ('6f665966-59b5-491a-a73f-e224a8e25e06', 5),
    ('56f246fd-e3b3-42bb-bc63-823793a19ff1', 5);