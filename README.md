📄 Descrição
Cadastro de Motoristas é um aplicativo Android desenvolvido em Kotlin que permite o cadastro de motoristas e seus veículos, com persistência local utilizando Room e sincronização de dados com um WebService via protocolo SOAP.

O app armazena os dados offline e realiza o envio ao servidor assim que possível, garantindo integridade e consistência nas informações.

🛠️ Tecnologias utilizadas
Kotlin – Linguagem principal do projeto
Java - Linguagem usada para o desenvolvimento do web service, seguido neste link -> [SOAP WEB SERVICE](https://github.com/robitooS/soap-web-service)

Android Room – Persistência local com banco de dados SQLite

KSOAP2 – Comunicação com WebServices SOAP

Coroutines – Execução assíncrona de tarefas
