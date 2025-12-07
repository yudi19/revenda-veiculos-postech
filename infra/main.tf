terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.aws_region
}

# Data source para pegar a AMI mais recente do Amazon Linux 2023 ARM64
data "aws_ami" "amazon_linux_2023_arm64" {
  most_recent = true
  owners      = ["amazon"]

  filter {
    name   = "name"
    values = ["al2023-ami-*-kernel-6.1-arm64"]
  }

  filter {
    name   = "architecture"
    values = ["arm64"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

# Key Pair para acesso SSH
resource "aws_key_pair" "revenda_github_key" {
  key_name   = "revenda-github-key"
  public_key = var.public_key
}

# Security Group
resource "aws_security_group" "revenda_api_sg" {
  name        = "revenda-api-sg"
  description = "Security group para API Revenda Veiculos"

  # SSH - Acesso restrito
  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = [var.ssh_cidr]
  }

  # API - Porta 8080 liberada para todos
  ingress {
    description = "API Port 8080"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Egress - Liberar todo tráfego de saída
  egress {
    description = "Allow all outbound"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "revenda-api-sg"
  }
}

# EC2 Instance
resource "aws_instance" "revenda_api" {
  ami           = data.aws_ami.amazon_linux_2023_arm64.id
  instance_type = var.instance_type
  key_name      = aws_key_pair.revenda_github_key.key_name

  vpc_security_group_ids = [aws_security_group.revenda_api_sg.id]

  # User data para configurar a instância
  user_data = <<-EOF
              #!/bin/bash
              set -e
              
              # Atualizar o sistema
              yum update -y
              
              # Instalar Java 21 (Amazon Corretto)
              yum install -y java-21-amazon-corretto-headless
              
              # Criar diretório da aplicação
              mkdir -p /opt/revenda
              chown ec2-user:ec2-user /opt/revenda
              chmod 755 /opt/revenda
              
              # Criar arquivo de log
              touch /opt/revenda/app.log
              chown ec2-user:ec2-user /opt/revenda/app.log
              EOF

  tags = {
    Name = "revenda-api-instance"
  }

  # Garantir que a instância tenha IP público
  associate_public_ip_address = true
}
