INSERT INTO users (id, name, password, role)
VALUES
    (1, 'admin', '$2a$04$qRyxX6kH0B9B3pX69K5Ue.6RkZp1A6uLgKxXmNlZp1A6uLgKxXmNl', 'USER'),
    (2, 'nartemt', '$2a$04$qRyxX6kH0B9B3pX69K5Ue.6RkZp1A6uLgKxXmNlZp1A6uLgKxXmNl', 'USER')
    ON CONFLICT (id) DO NOTHING;

INSERT INTO musical_equipment (id, brand, model, price, description, image_url, stock, equipment_type)
VALUES
    (1, 'Marshall', 'JCM800', 1500.00, 'Classic tube amplifier', 'https://res.cloudinary.com/ducqfcyoj/image/upload/v1775988934/Marshall_JVM205H5-270x270_y0iv9y.webp', 5, 'AMPLIFIER'),
    (2, 'Fender', 'Stratocaster', 1200.00, 'Legendary electric guitar', 'https://res.cloudinary.com/ducqfcyoj/image/upload/v1778788058/fenderstrat_bh4a5n.png', 3, 'GUITAR'),
    (3, 'BOSS', 'DS-1', 80.00, 'Classic distortion pedal', 'https://res.cloudinary.com/ducqfcyoj/image/upload/v1776015236/boss-mt-2-metal-zone-pedal-_1_GIT0000573-000_nsdm7n.jpg', 10, 'PEDAL')
    ON CONFLICT (id) DO NOTHING;

INSERT INTO amplifiers (id, amplifier_type, warmth_score, output_power)
VALUES
    (1, 'TUBE', 8, 50)
    ON CONFLICT (id) DO NOTHING;

INSERT INTO guitars (id, body_shape, body_wood, pickup_config, scale_length, tuning, base_treble_response, output_power)
VALUES
    (2, 'STRATOCASTER', 'Alder', 'SSS', 25.5, 'E_STANDARD', 7, 1)
    ON CONFLICT (id) DO NOTHING;

INSERT INTO pedals (id, effect_type, gain_level, base_mid_response)
VALUES
    (3, 'DISTORTION', 6, 5)
    ON CONFLICT (id) DO NOTHING;

INSERT INTO sound_presets (id, req_pickup, freq_low, freq_mid, freq_high, req_tuning, min_gain_required, required_output_power, warmth_target)
VALUES
    (1, 'HH', 8, 2, 9, 'D_STANDARD', 8, 50, 2),
    (2, 'HH', 9, 1, 9, 'D_STANDARD', 9, 100, 1)
    ON CONFLICT (id) DO NOTHING;

INSERT INTO albums (id, title, artist, cover_url, genre, preset_id, pro_tip, target_brand, target_shape, is_lo_fi)
VALUES
    (1, 'Cowboys from Hell', 'Pantera', 'https://res.cloudinary.com/ducqfcyoj/image/upload/v1774809348/pic3_vk5rd3.jpg', 'THRASH_METAL', 1, 'Scoop your mids on your amp EQ for that classic Dimebag thrash tone.', 'Dean', 'EXPLORER', false),
    (2, 'Vulgar Display of Power', 'Pantera', 'https://res.cloudinary.com/ducqfcyoj/image/upload/v1774809345/pic9_sdsthw.jpg', 'GROOVE_METAL', 2, 'Use a high-output humbucker and crank up the solid-state gain.', 'Dean', 'EXPLORER', false)
    ON CONFLICT (id) DO NOTHING;

SELECT setval(pg_get_serial_sequence('sound_presets', 'id'), COALESCE(MAX(id), 1)) FROM sound_presets;
SELECT setval(pg_get_serial_sequence('albums', 'id'), COALESCE(MAX(id), 1)) FROM albums;
SELECT setval(pg_get_serial_sequence('users', 'id'), COALESCE(MAX(id), 1)) FROM users;
SELECT setval(pg_get_serial_sequence('musical_equipment', 'id'), COALESCE(MAX(id), 1)) FROM musical_equipment;
