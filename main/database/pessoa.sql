CREATE TABLE Pessoa IF NOT EXISTS(
    id int auto_increment PRIMARY KEY,
    nome varchar(255) NOT NULL,
    nascimento timestamp NOT NULL;

);

CREATE TABLE Endereco IF NOT EXISTS(
    id int auto_increment PRIMARY KEY,
    logradouro varchar(255) NOT NULL,
    cep varchar(255) NOT NULL,
    numero int NOT NULL,
    cidade varchar (255) NOT NULL,
    pessoa int NOT NULL
);