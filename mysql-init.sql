-- Optional: initial content if you want to import manually
INSERT INTO site_texts (key_name, value) VALUES ('HOME_HERO_TITLE', 'Streetwear that lasts.')
ON DUPLICATE KEY UPDATE value=VALUES(value);
