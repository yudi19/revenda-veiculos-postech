# Arquitetura Hexagonal (Ports & Adapters)

Estrutura proposta do projeto:

- `domain/` — entidades, VOs, regras de negócio, ports
- `application/` — casos de uso, DTOs, serviços de aplicação
- `adapters/in/` — entradas (REST, CLI, eventos)
- `adapters/out/` — saídas (persistência, integrações externas)
- `infrastructure/` — configurações, segurança, observabilidade, mapeadores

Use esta estrutura para manter o domínio independente de frameworks.
