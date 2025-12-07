# Infraestrutura AWS - Revenda Veículos API

## Pré-requisitos

1. **Terraform** instalado (versão 1.6+)
2. **AWS CLI** configurado com credenciais
3. Par de chaves SSH gerado

## Gerar chave SSH

```bash
# Gerar nova chave SSH
ssh-keygen -t rsa -b 4096 -f ~/.ssh/revenda-key

# Isso criará dois arquivos:
# ~/.ssh/revenda-key (privada - NUNCA compartilhar)
# ~/.ssh/revenda-key.pub (pública - usar no terraform.tfvars)
```

## Configurar terraform.tfvars

1. Copie o conteúdo de `~/.ssh/revenda-key.pub`
2. Cole no campo `public_key` do arquivo `terraform.tfvars`
3. Ajuste o `ssh_cidr` para seu IP público (opcional, mas recomendado)

## Deploy Manual (local)

```bash
# Entrar na pasta infra
cd infra

# Inicializar Terraform
terraform init

# Ver o plano de execução
terraform plan

# Aplicar as mudanças
terraform apply

# Após o apply, pegar o IP público
terraform output instance_public_ip
```

## Conectar à EC2 via SSH

```bash
# Usar a chave privada gerada
ssh -i ~/.ssh/revenda-key ec2-user@<IP_PUBLICO>

# Ver logs da aplicação
tail -f /opt/revenda/app.log
```

## GitHub Secrets necessários

Configure os seguintes secrets no repositório GitHub:

1. `AWS_ACCESS_KEY_ID` - Access Key da AWS
2. `AWS_SECRET_ACCESS_KEY` - Secret Key da AWS
3. `AWS_REGION` - Região (ex: us-east-1)
4. `EC2_SSH_KEY` - Conteúdo da chave PRIVADA (~/.ssh/revenda-key)

### Como configurar os secrets:

1. Vá em: **Settings → Secrets and variables → Actions**
2. Clique em **New repository secret**
3. Adicione cada secret com seu respectivo valor

**IMPORTANTE**: Para o `EC2_SSH_KEY`, copie TODO o conteúdo do arquivo `~/.ssh/revenda-key` (incluindo as linhas BEGIN e END).

## Custos estimados

- **EC2 t4g.micro**: ~$6/mês (elegível para Free Tier no primeiro ano)
- **Tráfego de rede**: Primeiros 100GB/mês grátis
- **IP Público**: Grátis enquanto a instância estiver rodando

## Destruir infraestrutura

```bash
cd infra
terraform destroy
```

## Endpoints da API

Após o deploy, a API estará disponível em:
- `http://<IP_PUBLICO>:8080/veiculos`
- `http://<IP_PUBLICO>:8080/vendas`
- `http://<IP_PUBLICO>:8080/h2-console`
- `http://<IP_PUBLICO>:8080/actuator/health`
