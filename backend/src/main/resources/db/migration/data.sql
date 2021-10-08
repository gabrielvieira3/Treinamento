INSERT INTO tb_pessoa (id, age, comida_favorita, name, sobre_mim) VALUES (1 ,12, 'Arroz', 'Gabriel', 'Gosto muito de cozinhar');
INSERT INTO tb_pessoa (id, age, comida_favorita, name, sobre_mim) VALUES (2, 15, 'Feijao', 'Maria', 'Gosto muito de dancar');
INSERT INTO tb_pessoa (id, age, comida_favorita, name, sobre_mim) VALUES (3, 21, 'Cebola', 'Rayane', 'Gosto muito de jogar bola');
INSERT INTO tb_pessoa (id, age, comida_favorita, name, sobre_mim) VALUES (6 ,12, 'Carne', 'Ana', 'Gosto muito de ler');
INSERT INTO tb_pessoa (id, age, comida_favorita, name, sobre_mim) VALUES (7 ,22, 'Bolo', 'Daniela', 'Gosto muito de direito');

INSERT INTO tb_category VALUES (1,'Salgado');
INSERT INTO tb_category VALUES (2,'Doce');
INSERT INTO tb_category VALUES (3,'Vegano');
INSERT INTO tb_category VALUES (4,'Salada');
INSERT INTO tb_category VALUES (5,'Almoço');
INSERT INTO tb_category VALUES (6,'Jantar');
INSERT INTO tb_category VALUES (7,'Sobremesa');
INSERT INTO tb_category VALUES (8,'Fit');

INSERT INTO tb_ingredientes VALUES (1, 'Calabresa', 2, 1);
INSERT INTO tb_ingredientes VALUES (2, 'Arroz', 2, 1);
INSERT INTO tb_ingredientes VALUES (3, 'Lasanha', 2, 2);
INSERT INTO tb_ingredientes VALUES (4, 'Presunto', 3, 2);
INSERT INTO tb_ingredientes VALUES (5, 'Queijo', 4, 2);
INSERT INTO tb_ingredientes VALUES (6, 'Feijao Preto', 2, 3);

INSERT INTO tb_receitas VALUES (1, 'Arroz com calabresa', TRUE, 'Arroz', 3, 'quinze minutos');
INSERT INTO tb_receitas VALUES (2, 'Lasanha presunto e queijo', FALSE, 'Lasanha', 2, 'uma hora');
INSERT INTO tb_receitas VALUES (3, 'Feijoada', TRUE, 'Feijao', 5, 'trinta minutos');
INSERT INTO tb_receitas VALUES (6, 'Macarrao com carne', TRUE, 'Macarrao', 10, 'quarenta minutos');

INSERT INTO tb_usuarios VALUES (1, 'gabriel@youxgroup.com.br', '123456', 1);
INSERT INTO tb_usuarios VALUES (2, 'asdasd@youxgroup.com.br', '1234567', 2);
INSERT INTO tb_usuarios VALUES (3, 'fernandes@youxgroup.com.br', '12345678', 3);
INSERT INTO tb_usuarios VALUES (6, 'cebola@youxgroup.com.br', '123456', 6);
INSERT INTO tb_usuarios VALUES (7, 'vinagre@youxgroup.com.br', '12345678', 7);