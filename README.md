
<h1 align="center" style="font-weight: bold;">CRUD de Alimentos Font-end Back-end <img src="https://github.com/user-attachments/assets/7f6e7cde-b544-43d5-b3e8-a164a25a3797" width=30px></h1> 

<p align="center">
 <a href="#tech">Tecnologias</a> • 
 <a href="#started">Primeiros Passos</a> 
</p>


<h2>
  Descrição
</h2>

<p align="left">
    Programa feito para a disciplina de POO, o qual consiste de um sistema CRUD que usa conceito básicos e fundamentais da orientação objeto (herança, polimorfismo, encapsulamento, interfaces...). A conexão com o banco de   
    dados foi feita no Oracle Express 21c usando o drive JDBC respectivo.
</p>


<div align="center">
  <img src="https://github.com/user-attachments/assets/dda5c061-e86c-40d8-b75b-bf74f03c2f8e" alt="Imagem demonstrativa do site">
</div>

<h2 id="tech">💻 Tecnologias</h2>

- JAVA
- SQL / ORACLE
- BATCHFILE

- <h2 id="started">🚀 Primeiros Passos</h2>

<h3>Pré-Requisitos</h3>

 - [ORACLE 21c](https://www.oracle.com/br/database/technologies/xe-downloads.html)

<h3>Estrutura do Banco</h3>

- Tabela

```SQL
CREATE TABLE ALIMENTOS (
    alm_id                 int constraint ck_alm_01 not null,                           
    alm_nome               VARCHAR2(100) constraint ck_alm_02 not null,                  
    alm_categoria          VARCHAR2(50) constraint ck_alm_03 not null,                  
    alm_quantidade_estoque NUMBER(10, 2),                           
    alm_preco              NUMBER(10, 2),                           
    alm_data_validade      DATE,                                    
    alm_data_fabricacao    DATE,                                    
    alm_peso_por_unidade   NUMBER(10, 2),                           
    alm_marca              VARCHAR2(50),                           
    alm_pais_origem        VARCHAR2(50),                            
    alm_codigo_barras      VARCHAR2(20),                           
    PRIMARY KEY (alm_id)                                  
);
```
- Sequence

```SQL
create sequence alm_sq nocache;
```

- Trigger
  
```SQL
create trigger alm_tg_sq
before insert on alimentos
for each row
begin 
    :new.alm_id := alm_sq.nextval;
    
end;
/
```

Com o Oracle instalado e a estrutura do banco criada, você pode dar 2 cliques no arquivo "compilar.bat" para compilar o programa
e logo após "rodar.bat" para iniciá-lo

<h3>Clonagem</h3>

Use o comando abaixo para clonar este projeto

```bash
git clone https://github.com/joao72neto/CRUD-Alimentos
```
