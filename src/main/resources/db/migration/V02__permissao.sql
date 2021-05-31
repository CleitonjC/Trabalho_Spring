CREATE TABLE permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO permissao (codigo, descricao) VALUES 
(1, 'ROLE_CADASTRAR_CATEGORIA');

INSERT INTO permissao (codigo, descricao) VALUES 
(2, 'ROLE_PESQUISAR_CATEGORIA');


INSERT INTO permissao (codigo, descricao) VALUES 
(3, 'ROLE_CADASTRAR_PESSOA');

INSERT INTO permissao (codigo, descricao) VALUES 
(4, 'ROLE_REMOVER_PESSOA');

INSERT INTO permissao (codigo, descricao) VALUES 
(5, 'ROLE_PESQUISAR_PESSOA');


INSERT INTO permissao (codigo, descricao) VALUES 
(6, 'ROLE_CADASTRAR_LANCAMENTO');

INSERT INTO permissao (codigo, descricao) VALUES 
(7, 'ROLE_REMOVER_LANCAMENTO');

INSERT INTO permissao (codigo, descricao) VALUES 
(8, 'ROLE_PESQUISAR_LANCAMENTO');

