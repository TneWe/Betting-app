-- -- TEAM
-- INSERT INTO team (ime) VALUES
--   ('USA'),
--   ('France'),
--   ('Brazil'),
--   ('Italy'),
--   ('Canada'),
--   ('Hajduk');

-- -- OFFER
-- INSERT INTO offer (host_id, guest_id, host_odds, guest_odds, draw_odds) VALUES
--   (1, 2, 1.2, 2.6, 4),
--   (4, 3, 1.2, 2.6, 4),
--   (2, 4, 1.2, 2.6, 5),
--   (1, 5, 1.2, 2.6, 4),
--   (5, 3, 1.2, 2.6, 4);

-- -- BET SLIP
-- INSERT INTO bet_slip (wager) VALUES
--   (10),
--   (20),
--   (30);

-- -- BET
-- INSERT INTO bet (bet_slip_id, offer_id, outcome) VALUES
--   (1, 1, 'HOME_WIN'),
--   (1, 2, 'AWAY_WIN'),
--   (1, 3, 'DRAW');


-- TEAM
INSERT INTO team (ime) VALUES
  ('USA'),
  ('France'),
  ('Brazil'),
  ('Italy'),
  ('Canada'),
  ('Hajduk');

-- OFFER
INSERT INTO offer (host_id, guest_id, host_odds, guest_odds, draw_odds, outcome) VALUES
  (1, 2, 1.25, 2.8, 2.1, 'HOME_WIN'),
  (4, 3, 1.15, 2.6, 4, 'AWAY_WIN'),
  (2, 4, 1.8, 2.4, 2.5, 'DRAW'),
  (3, 6, 2.2, 1.6, 3, null),
  (2, 3, 2.1, 1.6, 3.2, null),
  (4, 2, 1.8, 2.6, 4.5, null),
  (1, 6, 1.1, 3.6, 1.9, null),
  (5, 1, 1.8, 1.9, 2.7, null);

-- BET SLIP
INSERT INTO bet_slip (wager) VALUES
  (10),
  (20),
  (30),
  (40);

-- BET
INSERT INTO bet (bet_slip_id, offer_id, outcome) VALUES
  (1, 1, 'HOME_WIN'),
  (1, 2, 'AWAY_WIN'),
  (1, 3, 'DRAW'),
  (2, 1, 'HOME_WIN'),
  (2, 2, 'DRAW'),
  (2, 3, 'HOME_WIN'),
  (3, 3, 'HOME_WIN'),
  (3, 4, 'AWAY_WIN'),
  (3, 5, 'DRAW'),
  (4, 6, 'HOME_WIN'),
  (4, 7, 'AWAY_WIN'),
  (4, 8, 'DRAW');
