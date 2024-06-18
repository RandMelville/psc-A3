## Sistema de Cálculo de Média de Notas
Este sistema foi desenvolvido para permitir que alunos calculem suas médias a partir de suas notas em diferentes avaliações.


### Funcionalidades Principais:
- O sistema conta com uma área de login,para que o usuário possa acessar somente suas próprias notas, garantindo privacidade aos usuários.


- Desenvolvemos um **cálculo de média ponderada**, onde o sistema sabe lidar com diferentes pesos atribuídos às notas. Por exemplo, se uma prova vale 50% da nota final e o aluno tira 50 nela, isso corresponde a 100% daquela parte da média.


- O sistema de cálculo de média com percentuais de peso diferente é um pouco mais complexo, visto que se um aluno tira 50 em uma prova que vale 50%, ele terá atingido o total, ou seja, os 100%. Com isso tivemos que adaptar o sistema para que a média seja favorável nessa situação.


- Escrita a nota, com todas as informações, o usuário clica no botão **"Adicionar nota"**, que lança a nota automaticamente no banco de dados, e inclui a nota em uma tabela.


- Com a inserção de **ao menos** duas notas o aluno consegue realizar a média. Com nosso sistema não existem limites de notas para realizar o cálculo de média.

- As notas ficam armazenadas no banco de dados para que o usuário possa visualizadas novamente em outro momento.

- O usuário também tem a opção de deletar sua nota, e a mesma será apagada no banco de dados também.

Você pode conferir nosso repositório original, que conta com todos os repositórios e todo o processo clicando [aqui](https://github.com/brayan-duwe/A3-calculadora-de-notas).