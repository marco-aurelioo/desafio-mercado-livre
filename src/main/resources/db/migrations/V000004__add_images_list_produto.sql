CREATE TABLE IF NOT EXISTS produto_images (
  id INT NOT NULL,
  images varchar(255) DEFAULT NULL,
  FOREIGN KEY (id) REFERENCES produtos (id)
);