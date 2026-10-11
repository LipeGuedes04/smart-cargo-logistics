# 🏢 Sistema de Controle de Carga e Embarque - Smart Logística

## 🎯 O que o projeto faz?

O sistema simula o gerenciamento de expedição e cubagem de carga para um galpão de logística. O usuário define o **peso máximo suportado** pelo caminhão e estipula uma meta inicial de listagem de produtos. Para cada item, o operador informa o **nome da mercadoria** e seu **peso em KG**. O programa realiza checagens compulsórias de segurança em tempo real (barrando valores nulos ou negativos) e implementa um **teste de barreira cumulativo**: se o peso da nova mercadoria somado à carga atual estourar o limite do veículo, a entrada é rejeitada. No fim, um extrato completo exibe os itens aceitos com alinhamento tabular estruturado.

### 🔄 Fluxo da Aplicação
```text
[ Limite do Caminhão ] ➔ [ Definição de Meta ] ➔ [ Dados da Carga ] ➔ [ Teste de Barreira Cumulativo ] ➔ [ Relatório e Cubagem ]
```

---

## 🎨 Design do Código (Como as regras conversam)

Este projeto foi construído utilizando uma estrutura de **Listas Dinâmicas Cruzadas (ArrayLists)** que operam em paralelo dentro de um laço contado (`for`), monitoradas por um filtro condicional de segurança imediata. Veja a arquitetura limpa do fluxo lógico:

```text
       ┌────────────────────────┐
       │ Limite de Carga (Max)  │ (Define a barreira física do veículo)
       └───────────┬────────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │   Quantidade de Itens  │ (Estipula o tamanho do loop de leitura)
       └───────────┬────────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │   Loop de Captura      │ ◄───┐ (Iterador 'i' coleta dados do terminal)
       └───────────┬────────────┘     │
                   │                  │
                   ▼                  │ (Próxima rodada)
       ┌────────────────────────┐     │
       │  Validação de Peso > 0 │     │
       └───────────┬────────────┘     │
                   │                  │
                   ▼                  │
       ┌────────────────────────┐     │
       │ Teste: pesoTotal + X   │ ────┘ (Adiciona na lista apenas se respeitar o limite)
       └───────────┬────────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │  Relatório Tabular     │ (Laço independente varre as listas gerando o extrato)
       └────────────────────────┘
```

### 📋 Mapeamento de Variáveis e Estruturas

| Estrutura / Variável | O que ela faz no código? |
| :--- | :--- |
| 🚀 `main.java` | Classe executável que gerencia a entrada via console, as regras fiscais de cubagem e o fechamento do manifesto de carga. |
| 📑 `Mercadoria` | `ArrayList<String>` dinâmico que armazena os nomes apenas dos produtos validados e aceitos pelo sistema. |
| 📊 `Peso` | `ArrayList<Double>` dinâmico que salva os pesos das mercadorias, mantendo paridade de índice com a lista de nomes. |
| ⚖️ `pesoMaximo` | Ponto flutuante (double) capturado no início que determina o teto de tolerância contra sobrecarga do caminhão. |
| 📦 `pesoProduto` | Recebe o peso temporário do item corrente para realizar a simulação matemática antes do embarque definitivo. |
| 📈 `pesoTotal` | Acumulador global que guarda a soma em tempo real das cargas que já estão fisicamente dentro do caminhão. |

---

## 🧠 Conceitos de Programação Praticados

* **Alocação em Listas Dinâmicas Paralelas:** Uso de dois objetos `ArrayList` independentes para associar dados textuais e numéricos compartilhando o mesmo índice iterativo (`i`).
* **Algoritmo de Simulação Prévia (Teste de Barreira):** Mecânica lógica que antecipa o estouro de uma variável de controle (`pesoTotal + pesoProduto`) sem alterar seu valor real caso a condição falhe.
* **Formatação de Colunas com printf:** Uso do modificador de largura de campo fixo e alinhamento à esquerda (`%-20s`) para tabelar as saídas de texto diretamente no terminal.

---

## 💻 Exemplo Prático de Uso

Imagine a seguinte simulação de carregamento contendo um item aceito e uma rejeição por excesso de peso rodando no terminal:

```text
=== SISTEMA DE CONTROLE DE MERCADORIAS ===
Qual peso maximo suportado pelo caminhão? (em KG) 
500.00
Quantos produtos será inserido no caminhão? 
2
-------------------------------------
Mercadoria 1 // Nome:
Painéis Solares
Mercadoria 1 // Peso:
350.00
✅ Mercadoria aceita e colocada no caminhão!
-------------------------------------
Mercadoria 2 // Nome:
Baterias Industriais
Mercadoria 2 // Peso:
200.00
⚠️ Operação Negada! O caminhão não suporta este peso extra.

======= RELATÓRIO =========
- Item 1: Painéis Solares      | 350.00 KG
-----------------------------------------------
> Total de itens carregados: 1
> Peso Total Final Acumulado: 350.00 / 500.00 KG
> Espaço Disponível Restante: 150.00 KG
===============================================
```
