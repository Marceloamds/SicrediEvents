# Eventos Sicredi

# Tecnologias Utilizadas
- Kotlin: Android é Kotlin First a algum tempo. Comparado ao java, propõe melhor legibiliade e menos boilerplate no código. Essencial para qualquer desenvolvedor android
 
- MVVM: Arquitetura mais popular em Android hoje. Com ela, temos alto desacoplamento e muito mais facilidade na manutenção do código. Por mais que o MVP seja fácil de aprender, o MVVM gera menos código e menos dependências.

- Clean Architecture: Arquitetura para promover ainda mais separação de responsabilidades. Por mais que mais classes e pacotes sejam gerados, ela deixa a navegação mais fácil deixa o app mais testável

- Retrofit e Gson: Tecnologia padrão para chamadas HTTP. Faz com que o trabalho das chamadas muito mais simples e fácil

- DataBinding: Alternativa ao findViewById(). Deixa a Activity bem ligada ao XML, o que previne erros, boilerplates e facilita muito nos testes.

- Glide: Biblioteca mais popular para carregamento de imagens. O uso dela ao invés do Picasso é de preferência pessoal.

- Koin: Permite a injeção de dependência no projeto. A vantagem são códigos simples, elegante, muito legíveis e sem boilerplate, usando o máximo potencial do Kotlin. A desvantagem, comparado ao Dagger, é que alguns erros acontecem em tempo de execução, o que pode levar a crashes em aplicativos com muitos fluxos. Outra alternativa viável seria o Hilt, que não foi usado por falta de familiaridade

- Coroutines: Usado para criação de Threads. Coroutines propõe um código sequencial mesmo ao criar uma outra thread. Comparado ao RXJava, é mais elegante, simples e sem boilerplate, feito para usar com o Kotlin. A desvantagem é que o coroutines não possui tanta flexibilidade e leque de opções igual o RxJava

- JUnit 4: Biblioteca mais utilizada para rodar testes. JUnit 5 não foi utilizado por falta de familiriaridade mas seria altamente viável

- MockK para Unit Tests: Usada para facilitar os mocks nos testes. Feito para usar com kotlin e deixa o código bem elegante e sem boilerplates

- Robelectric: Usado para ser possível rodar testes de view em testes unitários. Perfeito para testar o Binding

- Espresso: Biblioteca padrão para testes de UI. Faz com que seja possível abrir o emulador e testar automaticamente as ações de um usuário

- Mockito para Testes de UI: Tem um pouco mais de boilerplate que o MockK, mas este não pode ser utilizado em testes de UI por conta de uma API mínima de 21.
