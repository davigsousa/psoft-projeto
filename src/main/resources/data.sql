insert into laboratorio (ID, NOME)
values(1, 'SPLAB');

insert into laboratorio (ID, NOME)
values(2, 'CODEX');

insert into laboratorio (ID, NOME)
values(3, 'EMBEDDED');

/* A senha é 123456 */
insert into Admin_User (ID, EMAIL, SENHA)
values(1, 'admin@email.com', '$2a$10$nnYH.BGaHeidn7EufsgKg.MXJ2zQiUKIVdiR89SRNwHg9u0FmsGyy');

insert into Aluno (ID, NOME, EMAIL, MATRICULA, SENHA, PERIODO_DE_CONCLUSAO)
values(1, 'Luiz', 'luiz@mail.com', '119210052', '$2a$10$nnYH.BGaHeidn7EufsgKg.MXJ2zQiUKIVdiR89SRNwHg9u0FmsGyy', '2024.2');

insert into Professor (ID, NOME, EMAIL, SENHA, MAX_ORIENTANDOS)
values(1, 'Luiz Prof', 'luiz@prof.com', '$2a$10$nnYH.BGaHeidn7EufsgKg.MXJ2zQiUKIVdiR89SRNwHg9u0FmsGyy', 2);

/* A senha é 123456 */
insert into aluno (ID, NOME, MATRICULA, EMAIL, SENHA, PERIODO_DE_CONCLUSAO)
values(4, 'Maria', '2345646', 'maria@gmail.com', '$2a$10$nnYH.BGaHeidn7EufsgKg.MXJ2zQiUKIVdiR89SRNwHg9u0FmsGyy', '2025.2');

insert into Area_Estudo (ID, ASSUNTO)
values(5, 'Projeto de Software');