INSERT INTO `restaurants` (`name`, `address_id`) VALUES
  ('VeggieRaw', 1),
  ('KFC', 2);


INSERT INTO `product` (`name`, `price`, `restaurant_id`) VALUES
  ('Tofu salad', 25, 1),
  ('Cesar salad', 30, 1),
  ('Crispy salad', 30, 1),
  ('Crispy strips', 30, 2);


INSERT INTO `addresses` (`city`, `number`, `street`) VALUES
  ('Sibiu', 12, 'Bulevardul Victoriei'),
  ('Sibiu', 32, 'Mihai Viteazu');