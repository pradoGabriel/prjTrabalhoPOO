INSERT INTO Dependencia (Nome, Descricao) 
VALUES ('Churrasqueira', '�rea com churrasqueira e freezer para carnes e bebidas.'),
('Piscinas', '�rea com piscinas aquecidas e n�o aquecidas, para adultos e crian�as.'),
('Quadra Poliesportiva', 'Quadras para pr�ticas de diversos esportes'),
('Sala de Jogos','Sal�o de jogos com fliperamas para descontra��o e divers�o')

INSERT INTO Plano (Nome, Valor, Descricao)
VALUES ('Basic', 'R$99', 'Plano b�sico com direito � acesso a piscinas e quadra poliesportiva'),
('Standard', 'R$159', 'Plano standard com direito � acesso a todas dep�ndencias do clube')

INSERT INTO Endereco (Logradouro, Cep, Numero, Complemento, Uf) 
VALUES('Av. �guia de Haia', '03694000', '2983', 'Pr�ximo ao terminal', 'SP'),
('Av. Paulista', '17280000', '1500', 'Pr�ximo ao metro', 'SP')

INSERT INTO Pagamento (NomeTitular, CpfTitular, NumCartao, Cvv, ValidadeCartao)
VALUES ('Fulando de Tal','12345678910', '01241254365487521', '451', '04/29'),
('Ciclano','12347665110', '02241888365487521', '200', '07/29')

INSERT INTO Usuario (Nome, Senha, Cpf, IsAdmin, IdEndereco, IdPagamento, IdPlano)
VALUES ('admin', 'admin123', '00000000000', 1, 1, 1, 2),
('Jo�o', 'senhaforte', '12345678912', 0, 2, 2, 1)

INSERT INTO PlanoDependencia (TempoPermanencia, IdPlano, IdDependencia)
VALUES ('4 Horas', 1, 2), ('1 Hora', 1, 3), ('4 Horas',2, 1), ('4 Horas',2, 2), ('4 Horas',2, 3), ('4 Horas',2, 4)

INSERT INTO Reserva (DataReserva, IdDependencia, IdUsuario)
VALUES ('24/06/2021', 2, 2), ('30/06/2021', 3, 2)