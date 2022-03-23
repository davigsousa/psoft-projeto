insert into laboratorio (ID, NOME)
values(1, 'SPLAB');

insert into laboratorio (ID, NOME)
values(2, 'CODEX');

insert into laboratorio (ID, NOME)
values(3, 'EMBEDDED');

/* A senha é 123456 */
insert into Admin_User (ID, EMAIL, SENHA)
values(1, 'admin@email.com', '$2a$10$nnYH.BGaHeidn7EufsgKg.MXJ2zQiUKIVdiR89SRNwHg9u0FmsGyy');

/* A senha é 123456 */
insert into aluno (ID, NOME, MATRICULA, EMAIL, SENHA, PERIODO_DE_CONCLUSAO)
values(4, 'Maria', '2345646', 'maria@gmail.com', '$2a$10$nnYH.BGaHeidn7EufsgKg.MXJ2zQiUKIVdiR89SRNwHg9u0FmsGyy', '2025.2');

insert into Area_Estudo (ID, ASSUNTO)
values(5, 'Projeto de Software');