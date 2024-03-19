/* PROJETO - Gestão Ti (Administrar e Gerir: Funcional/Operacional, Sistemas, Equipamentos)
** BANCO DE DADOS - TaBeLas : endereco, fabricante, fornecedor, localizacao, categoria, produto, modelo
*                             especificacao, especifica_modelo, 
* Incluir MAC dos usuários que fazem acessos wi-fi
*/

create database gestaoti;
use gestaoti;
/* create database gestaoti_teste;
/* use gestaoti_teste;

/* ---- USUÁRIO ---- */
create table usuario (
	id int not null auto_increment,
    nome varchar(45) not null,
    login varchar(45) not null,
    senha varchar(45),
    ativo boolean default true,
    primary key(id)
);

insert into usuario values (null, 'Everson Cebolinha', 'cbola', md5('abc123'), true);
select * from usuario;

/* ---- ENDEREÇO ---- */
create table endereco (
	id int not null auto_increment,
    logradouro enum ('Rua','Avenida','Travessa','Loteamento','Estrada','Vila','Rodovia','Quadra'),
	nome varchar(45),
	numero int(10) unsigned,
    complemento varchar(100),
    bairro varchar(45),
    cidade varchar(45),
    estado enum ('AC','AL','AM','AP','BA','CE','DF','ES','GO','MA','MG','MS','MT','PA','PB','PE','PI','PR','RJ','RN','RO','RR','RS','SC','SE','SP','TO'),
    CEP varchar(8),
    ponto_referencia text,
    info text,
    primary key(id)
);

/* alter table endereco add complemento varchar(100) after numero; */

insert into endereco (logradouro, nome, numero,bairro,cidade,estado,cep)
values ('Avenida', 'Evaldo Costa', 100, 'Sol e Mar', 'Macaé', 'RJ', 27940410),
       ('Avenida', 'Nossa Sra. da Ajuda',80,'Ajuda de Baixo','Macaé','RJ','27971390');
   
insert into endereco (logradouro, nome, numero,bairro,cidade,estado,cep)
values('Avenida', 'Av.das Américas', 700, 'Barra da Tijuca', 'Rio de Janeiro', 'RJ', 26640100);     
  
insert into endereco (logradouro, nome, numero, complemento, bairro, cidade, estado, cep)
values ('Avenida', 'Av da Emancipação', 500, 'Parte B', 'Parque dos Pinheiros', 'Hortolândia', 'SP', 13184654); /* DeLL, info da NF
    
    
/* ---- EMPRESA ---- */
CREATE TABLE empresa (
	id int not null auto_increment,
	cnpj varchar(14) unique,
    ie varchar(10) unique,
    razao varchar(45) not null,
    nome varchar(45) not null,
    telefone varchar(11),
    celular varchar(11),
    whatsapp varchar(11),
    email varchar(32),
    site varchar(54),
    login varchar(32),
    senha varchar(24),
    codigo_cliente varchar(24),
    tipo_empresa enum('Filial','Fabricante','Fornecedor'),
    logotipo blob,
    info text,
    ativo boolean default true,
    id_end int unique,
    primary key(id),
    foreign key(id_end) references endereco(id)
    );    
   
/* Inserts Empresa tipo Filial */
insert into  empresa (cnpj, ie, razao, nome, telefone, tipo_empresa, id_end)
		values ('08472686000125', '78253665','Campos Pavani de Macae Com Alim Ltda', 'JPavani Matriz', '2237370460',  'Filial', 1),
				('08472686000206', '78524960','Campos Pavani de Macae Com Alim Ltda', 'JPavani Filial', '2227622369' 'Filial', 2);
    
/* Inserts Empresa tipo Fabricante */ 
insert into empresa (nome, razao, cnpj, ie, telefone, site, login, senha, info, id_end )
values ('CISCO', 'Cisco System, Inc.', '00028666000581', null, '08008914972', 'www.cisco.com', 'jpavaniTI', 'JPav2015', 'e-mail: jpavani-informatica@uol.com.br - SMBS: 0800 892 1019', 3 );

        
/* --- LOCALIZAÇÃO --- */
create table localizacao (
	id int not null auto_increment,
    place varchar(55),
	filial int,
    ativo boolean default true,
    primary key (id),
    foreign key (filial) references empresa (id)
);

/* create table localizacao (
	id int not null auto_increment,
   place enum('Acougue','Acougue_Producao','Almoxarifado','Auditorio','CFTV','Compras','Camara_Fria','Contabilidade','Contas_Pagar','Corredor','CPD','Diretoria','Dormitorio','Entrega','Estacionamento','Estoque','Estoque_Avaria',' Estoque_CargaDescarga','Estoque_Preparo','Estoque_SegAndar','Fechamento','Caixa,''Guarita','Laje','Manutencao','Padaria','Padaria_Producao','PDV','Recepcao','Refeitorio','RH','Sala_Maquinas','Salao','Telefonia','Tesouraria','Ti','Vestirio','Web'),
	filial int,
    primary key (id),
    foreign key (filial) references empresa (id)
); */

insert into localizacao (place, filial) values ('Acougue',1),('Acougue',2),
	('Acougue_Producao',1),('Acougue_Producao',2),
	('Almoxarifado',1),('Almoxarifado',2),
	('Auditorio',1),
	('CFTV',1),('CFTV',2),
	('Compras',1),
	('Camara_Fria',1),('Camara_Fria',2),
	('Contabilidade',1),
	('Contas_Pagar',1),
	('Corredor',1),('Corredor',2),
	('Fechamento_Caixa',1),('Fechamento_Caixa',2),
	('CPD',1),('CPD',2),
	('Diretoria',1),
	('Estacionamento',1),('Estacionamento',2),
	('Estoque',1),('Estoque',2),
	('Estoque_Avaria',1),('Estoque_Avaria',2),
	('Estoque_CargaDescarga',1),('Estoque_CargaDescarga',2),
	('Estoque_Preparo',1),('Estoque_Preparo',2),
	('Estoque_SegAndar',1),('Estoque_SegAndar',2),
	('Guarita',1),('Guarita',2),
	('Laje',1),('Laje',2),
	('Manutencao',1),('Manutencao',2),
	('Padaria',1),('Padaria',2),
	('Padaria_Producao',1),('Padaria_Producao',2),
	('PDV',1),('PDV',2),
	('Recepcao',1),('Recepcao',2),
	('Refeitorio',1),('Refeitorio',2),
	('RH',1),('RH',2),
	('Sala_Maquinas',1),('Sala_Maquinas',2),
	('Salao',1),('Salao',2),
	('Telefonia',1),
	('Tesouraria',1),('Tesouraria',2),
	('Ti',1),('Ti',2),
	('Vestiario',1),('Vestiario',2),
	('Web',1);	

/* ---- FABRICANTE ---- */
create table fabricante (
	id int not null primary key,
    contato varchar(54),
    contato_fone varchar(11),
    contato_ramal varchar(8),
    contato_email varchar(32),
    suporte_fone varchar(11),
    suporte_email varchar(32),
    sac_fone varchar(11),
    sac_email varchar(32),
    logotipo blob,
    foreign key (id) references empresa(id)
);


/* alter table fabricante_heranca add foreign key (id) references empresa (id); */

/* --- FORNECEDOR --- */
create table fornecedor (
	id int not null primary key,
    venda_fone varchar(11),
    venda_email varchar(32),
	representante varchar(32),
    representante_fone varchar(11),
    representante_ramal varchar(8),
    representante_email varchar(32),
    foreign key (id) references empresa(id)
);

/* Criar campo para inserir fotos da Logo dos fabricantes e fornecedores */

insert into empresa (nome, razao, cnpj, ie, telefone, site, login, senha, codigo_cliente, tipo_empresa, id_end )
values ('DeLL', 'DeLL Computadores do Brasil Ltda', '72381189001001', '748241245113', '08009703384', 'www.dell.com.br', 'jpavani-informatica@uol.com.br', 'avani11Jp','101762319','Fabricante', 4 );

/* ---- CATEGORIA ---- */
create table categoria(
    id int unsigned not null auto_increment,
    nome varchar(50) not null unique,
    obs varchar(255),
    ativo boolean default 1,
    primary key(id)    
);

insert into categoria (id,nome,obs) values(null,'Network', 'Equipamentos de rede, em geral são configuráveis e respctivos complemntos'),
    (null,'Automação', 'Equipamentos para atomação comercial'),
    (null,'Print and Scan','Impressoras e Scanners'),
    (null,'Processamento de Dados','Servidores, Computadores, Laptops, Thinclient'),
    (null,'Communication','Telefone, Smartphone, pabx, rádio comunicador'),
    (null,'Safety','Câmeras,DVR,Alarmes,sensor de presença'),
    (null,'Suprimentos','Tinta, cartucho, tonner, fita, bobina, parafuso'),
    (null,'Periféricos','Equipamentos para auxilar/complementar como: webcam, teclado, mouse, suportes, peças e acessórios'),
    (null,'Conectividade','Cabeamento em geral (áudio, vídeo, rede), adaptadores, conversores, Plugs, RJ45'),
    (null,'Hardware','Tudo ligado a montagem dos PCs,laptop,servidores'),
    (null,'Storage','HDs Externo, pendrive, cartão memória, exceto hds internos que são hardware'),
    (null,'Energy','NoBreak,Bateria,Estabilizador'),
    (null,'Áudio e Vídeo','Monitor,TVs,Caixa de som,Equalizador'),
	(null,'Service','Contratados para prestação de serviço'),
	(null,'Dados', 'Sistemas, softwares, programas, aplicativos, etc.'),
    (null,'Ferramentas','Alicates, crimpadores, chave fenda, philips, Testadores, etc.');


/* ---- PRODUTO --- */
create table produto(
    id int not null auto_increment,
    nome varchar(50) not null unique,
    produto_tipo enum('Equipamento', 'Software', 'Sistema', 'Servico', 'UsoConsumo'),
    obs varchar(255),
    ativo boolean default 1,
    id_categoria int unsigned not null,
    primary key(id),
    foreign key(id_categoria) references categoria (id)
);
/* O campo produto_tipo foi criado com intenção de facilitar e agrupar busca e separar produtos na tabela Despesa
detalhando cada um: 
Equipamento - tudo que tem ns ou qualquer identificação
Material - produtos duráveis que não tenha identificação, ex.: do grupo conectividade, ferramentas
UsoConsumo - produtos q acabam ex.: fitas, suprimentos, pilhas 
Sistema - softwares que tenha prestação de serviço e/ou mensalidade ou necessita de renovação ex.: nasajon, DAM, Tangerino
Software - de aquisição unica, adquirida a chave ex.: CorelDraw, windows ferramentas,
*/

insert into produto values(null,'Switch',null,1,1),
    (null,'Roteador',null,1,1),
    (null,'Access Point',null,1,1),
    (null,'Antena RF Ativa','Faz a transmissão do sinal',1,1),
    (null,'Antena RF Passiva','Antena (arredondada) para direcionar a transmissão',1,1);

insert into produto(nome,id_categoria) values ('Balança',2),('Balança checkout',2),('Catraca',2),('Coletor',2),('Gaveta',2),
	('Impressora não-fiscal',2),('Leitor Biométrico',2),('Leitor Boleto',2),('Leitor Cód.Barras Fixo',2),('Leitor Cód.Barras Manual',2),
    ('Leitor Cód.Barras wireless',2),('PinPad',2),('REP',2);
    
insert into produto(nome,id_categoria) values('Impressora Eqtiqueta',3),('Impressora Matricial',3),
	('Impressora Multifuncional',3),('Impressora Senha',3),('Scanner',3);
    
insert into produto(nome,id_categoria) values('Computador',4),('Laptop',4),('Servidor',4),('Thinclient',4);
        

/* ---- MODELO ---- */
CREATE TABLE modelo (
	id int not null auto_increment,
    nome varchar(100) not null unique,
    codigo varchar(45) unique,
    manual varchar(100),
    datasheet varchar(100),
    obs varchar(255),
    foto blob,
    id_produto int not null,
    id_fabricante int not null,
    primary key (id),
    foreign key (id_equipamento) references equipamento(id),
    foreign key (id_fabricante) references fabricante (id)   
    );
    
insert into modelo (nome,codigo,id_produto,id_fabricante) values('Catalyst 2960-X-24TD-L','WS-C2960X-24TD-L',1,1);
insert into modelo (nome,codigo,id_produto,id_fabricante) values('Catalyst 2960-X-48TD-L','WS-C2960X-48TD-L',1,1);
insert into modelo (nome,codigo,id_produto,id_fabricante) values('Catalyst 2960-XR-48LPD-I','WS-C2960XR-48LPD-I',1,1);
insert into modelo (nome,codigo,id_produto,id_fabricante) values('Catalyst 3560 serires PoE-48','WS-C3560-48PS-S',1,1);

select c.nome, p.nome, f.nome, m.id, m.nome, m.codigo, e.nome, e.obs
FROM categoria c
INNER JOIN produto p ON c.id = p.id_categoria
INNER JOIN modelo m ON p.id = m.id_produto
INNER JOIN fabricante f ON f.id = m.id_fabricante
INNER JOIN especifica_modelo em ON em.id_modelo = m.id
INNER JOIN especificacao e ON e.id = em.id_especifica;

/* ---- ESPECIFÍCAÇÕES ---- */
create table especificacao (
	id int not null auto_increment,
    nome varchar(100) not null unique,
    obs varchar(255),
    primary key(id)
    );
    
insert into especificacao values (null, 'Layer 3', 'Trabalha na camada 3 (transporte)'),
 (null, '48 Portas Gbps', null);
 
insert into especificacao values (null, 'Layer 2', null), (null, '24 Portas Gbps', null);
    

/* ---- ESPECIFÍCAÇÕES DO MODELO ---- */
create table datasheet (
	id_modelo int not null,
    id_especifica int not null,
    primary key (id_modelo, id_especifica)
);

insert into datasheet values(3,1),(3,2),(4,1),(4,2),(1,2),(2,2);
insert into datasheet values(3,3);
/* --- PEDIDO --- */
create table pedido (
    id int unsigned auto_increment,
    codigo varchar(45),
    data_pedido timestamp not null,
    status_pedido enum('Aberto', 'Pendente', 'Finalizado', 'Cancelado'),
    frete double(10,2) unsigned not null,
    forma_pagamento enum('Boleto', 'Transferência', 'Crédito MercadoPago','Cartão','Dinheiro','Pix'), 
    data_pgto timestamp,
    obs_pedido text,
    obs_pgto text,
    id_fornecedor int unsigned not null,
    primary key(id),
    foreign key (id_fornecedor) references fornecedor(id)
    );
    
/* Obs1.: Todo PEDIDO concluído (status_pedido = Finalizado) é uma despesa no sistema, mas não na tabela despesa;
   Obs2.: Item não durável NãO será um item como: material Escritório (durex, fita);
   
 */
    
alter table pedido modify column status_pedido enum('Aberto', 'Pendente', 'Concluido', 'Cancelado');
alter table pedido add column frete double(10,2) unsigned not null after status_pedido;
alter table pedido drop column despesa_fixa;
    
/* --- ITEM_PEDIDO --- */
create table pedido_item (
    id int unsigned auto_increment,
    qtd int unsigned not null default 1,
    valor decimal(8,2) not null,
    data_pedido date,
    id_modelo int not null,
    id_pedido int unsigned not null,
    primary key(id),
    foreign key (id_modelo) references modelo(id),
    foreign key (id_pedido) references pedido(id)
);
alter table item_pedido modify column id_pedido int unsigned not null;
alter table item_pedido add foreign key (id_pedido) references pedido(id);
alter table item_pedido rename pedido_item;

/* --- DESPESAS --- 
Compra, Fixa ou Serviços
Compra (Aquisição de equipamentos, peças, software, etc.)
Fixa (Sistemas, internet, telefonia, etc)
Serviço (CFTV, Passar Cabo, etc.)
*/
create table despesa(
	id int auto_increment,
    fixa boolean default 1,
    nome varchar(100),
    tipo_despesa enum('Compra','Fixa','Serviço'),
    frequencia enum('Mensal','Anual','Variada','Unica'),
    vencimento date not null,
    id_fornecedor int unsigned not null,
    primary key(id),
    foreign key(id_fornecedor) references fornecedor(id)
);
/* O nome dessa despesa pode ser alguma especificação do q se trata, uma obs, ou descricao */

create table despesa_item(
	id int primary key auto_increment,
    nome varchar(100),
    qtd int unsigned default 1,
    valor double(8,2) not null,
    descricao text,
    id_modelo int,
    id_despesa int not null,
    foreign key (id_modelo) references modelo(id),
    foreign key (id_despesa) references despesa(id)
);

/* --- NOTA FISCAL --- */
create table notafiscal(
	id int not null auto_increment,
    tipo enum('NFe','NFSe','NFel','NFCe','Recibo','Comprovante') not null,
    numero varchar(15),
    data_nota timestamp,
    valor double(10,2) unsigned,
    id_pedido int unsigned,
    id_despesa int unsigned,
    primary key(id),
    foreign key(id_pedido) references pedido(id),
    foreign key(id_despesa) references despesa(id)
);

/* --- ITEM --- 
Tudo que é palpável e/ou que tem identificação (n/s, chave, tag) Ex.: Teclado, Monitor, Windows, Officce, etc. 
Obs.: garantia, quantidade de mêses
Obs1.: Pode existir Itens sem pedido, será criado tela de entrada no sistema com opção Entrada sem pedido, Ex. Tonner, tintas
Obs2.: O item com situação pendente é o qual após confirmação do pedido falta dar entrada como ns, codigos, etc. que ficará Habilitado após entrada
*/
create table item (
	id int unsigned not null auto_increment,
    ns varchar(45),
    chave varchar(45),
    tag varchar(45),
    cod char(13),
    ativo boolean default 1,
    tipo enum('Equipamento','Software'),
    situacao enum('Pendente', 'Habilitado', 'Doado', 'Descartado', 'Danificado'),
    garantia int,
    obs varchar(255),
    id_itempedido int unsigned unique,
    id_nf int,
    id_local int unique ,
    primary key (id),    
    foreign key (id_itempedido) references pedido_item(id),
    foreign key (id_nf) references notafiscal(id),
    foreign key (id_local) references localizacao (id)    
);

/* O campo cod criado se não tem (N/S, Chave e Tag), através de um algoritimo assim:
    JP + 3 letras iniciais do produto + Sequência numérica milhar
    Ex.: Memória -> JPMEM0001
    */

show create table item;

/* -- COMPUTADOR/SERVIDOR/LAPTOP -- se o item for um Equipamento e produto pertencente à Categoria Processamento de Dados deve preencher essa tabela 
   Obs.: Se PC MONTADO (ex.: DeLL) cadastrar os itens da categoria Hardware, ex.: memória, processador, placas, etc. após confirmar a pedido vai criar novos (processador, memória, HD/SSD, Placa-Mãe, Gabinete) itens na tabela ordinateur_peca
   Obs.: Se PC NãO_MONTADO (ex.: peças adquiridas separadamente) associar os itens do cadastrados após entrada com o item Gabinete, que vai ser o item cadastrado na tabela ordinateur
   Obs.: codigo vai seguir a regra do cod tabela item : JPCOM0001, Servidor JPSER0002 
   Obs.: nome -> é o nome da rede ex.: TESadmin, RHger */
create table ordinateur(
	id int auto_increment not null,
	codigo char(13),
    nome varchar(45),
    info varchar(500),
    ativo boolean,
    id_item int unique not null,
    primary key (id),
    foreign key (id_item) references item (id)
	);    
/* Se PC MONTADO - associar id_modelo Se Não id_item */
create table ordinateur_peca(
	id_ordinateur int unsigned unique,
    id_item int unsigned,
    id_modelo int,
    ns varchar(45),
    primary key (id_item),
    foreign key (id_comphard) references componente_hardware
);

/* --- COMPONENTE --- */
/* Componente são itens que compõe o computador como: teclado, mouse, monitor, webcam, etc. */
create table componente(
	id_ordinateur int unsigned not null,
    id_item int unsigned not null,
    primary key (id_ordinateur, id_item)
);

/* --- CONSERTO --- */
create table conserto (
	id int not null auto_increment,
    codigo varchar(45),
    data_envio timestamp not null,
    data_recebido timestamp,
    status_conserto enum ('Aberto', 'Pendente', 'Concluido', 'Cancelado'),
    valor double(5,2),
    obs text,
    id_fornecedor int unsigned,
    id_nf int,
    primary key (id),
    foreign key (id_fornecedor) references fornecedor(id),
    foreign key (id_nf) references notafiscal(id)
);

/* --- ITEM_CONSERTO --- */
create table conserto_item(
	id int not null auto_increment,
    defeito varchar(255) not null,
    id_item int unsigned not null,
    id_conserto int not null,
    primary key(id),
    foreign key (id_item) references item(id),
    foreign key (id_conserto) references conserto(id)
);


/* --- INATIVO --- */
create table inativo (
	id int unsigned not null auto_increment,
    data_inativo date not null,
    motivo enum('Defeito','Perda','Roubo','Obsoleto','Doação'),
    obs varchar(255),
    id_item int unsigned not null,
    primary key (id),
    foreign key (id_item) references item (id)
);

/* --- USUARIO --- */

/* --- COLABORADOR --- */
create table colaborador(
	id int auto_increment not null,
    nome varchar(20) not null,
    sobrenome varchar(40),
    setor enum('Ti', 'Web'),
    atribuicao varchar(255),
    primary key (id)
);

/* --- SERVICO --- * Atribuição das atividades /

/* --- PROJETO --- */

/* --- FERIADO --- */
create table feriado (
	id int auto_increment not null,
    nome varchar(50),
    id_colaborador int,
    foreign key (id_colaborador) references colaborador
);

/* --- MANUTENCAO --- *  Limpeza e manutenção preventiva dos equipamentos /







/* CREATE TABLE fabricante (
	id int not null auto_increment,
    nome varchar(100) unique not null,
    razao_social varchar(100) unique not null,
    cnpj varchar(14) unique not null,
    ie varchar(14), 
    representante varchar(30),
    fone_contato varchar(11),
    ramal_contato varchar(10),
    fone_suporte varchar(11),
    fone_sac varchar(13),
    email varchar(30),
    site varchar(100),
    login varchar(45),
    senha varchar(15),
    codigo_cliente varchar(15),
    info varchar (500),
    ativo boolean default 1,
    id_end int unique,
    primary key (id),
    foreign key (id_end) references endereco(id)
);

alter table fabricante add column email varchar(30) after fone_sac;
alter table fabricante add column email_cadastrador varchar(30) after email;
alter table fabricante change email_cadastrador email_cadastrado varchar(30);
alter table fabricante add column codigo_cliente varchar(15) after email_cadastrado; 


CREATE TABLE fornecedor (
	id int unsigned not null auto_increment,
    nome varchar(45) unique not null,
    razao_social varchar(45) unique not null,
    cnpj varchar(14) unique not null,
    ie varchar(14), 
    fone varchar(11),
    fone_venda varchar(11),
    fone_sac varchar(13),
    wattsapp varchar (11),
    representante varchar (255),
    representante_fone varchar(11),
    representante_ramal varchar(11),
    representante_email varchar(30),
    site varchar(100),
	codigo_cliente varchar(15),
    email_cadastrado varchar(30),
    login varchar(45),
    senha varchar(15),
    info varchar (500),
    ativo boolean default 1,
    id_end int,
    primary key (id),
    foreign key (id_end) references endereco(id)
);

alter table fornecedor add column fornece boolean default 0 after info; /* esse campo é para informar se o fornecedor também é um fabricante. Ex.: DeLL */
